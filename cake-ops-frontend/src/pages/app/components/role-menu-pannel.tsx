import React, { useState, useEffect } from "react";
import { Tree, Button } from "antd";
import { MenuTreeDTO } from "@/models/user";
import { MenuDTO } from "@/models/menu";

interface RoleMenuTabProps {
  fullMenuTree: MenuTreeDTO[];
  roleMenus: MenuDTO[];
  onFormSubmit: (checkedKeys: React.Key[]) => void;
}

const RoleMenuTab: React.FC<RoleMenuTabProps> = ({
  fullMenuTree,
  roleMenus,
  onFormSubmit,
}) => {
  const [checkedKeys, setCheckedKeys] = useState<React.Key[]>([]);

  // 初始化 checkedKeys
  useEffect(() => {
    const initialCheckedKeys = extractCheckedKeys(roleMenus);
    setCheckedKeys(initialCheckedKeys);
  }, [roleMenus]);

  // 提取角色已绑定的菜单键
  const extractCheckedKeys = (menus: MenuDTO[]): React.Key[] => {
    return menus.map((item) => item.menuId);
  };

  // 处理选中/取消选中的逻辑
  const onCheck = (
    checked: React.Key[] | { checked: React.Key[]; halfChecked: React.Key[] },
    info: any
  ) => {
    // 获取当前选中的键
    let newCheckedKeys: React.Key[] = [];
    if (Array.isArray(checked)) {
      newCheckedKeys = checked;
    } else {
      newCheckedKeys = checked.checked;
    }

    // 确保选中子节点时，所有父节点也被选中
    const finalCheckedKeys = ensureParentChecked(new Set(newCheckedKeys));

    // 处理取消选中节点的情况
    const removedKeys = new Set<React.Key>();
    checkedKeys.forEach((key) => {
      if (!finalCheckedKeys.has(key)) {
        removedKeys.add(key);
        removeChildrenKeys(fullMenuTree, key, removedKeys); // 移除子节点及其子树
      }
    });

    // 如果父节点被取消，则移除所有子节点
    newCheckedKeys.forEach((key) => {
      const node = findMenuItem(fullMenuTree, key);
      if (node && node.children) {
        node.children.forEach((child) => {
          if (!finalCheckedKeys.has(child.menuId)) {
            removedKeys.add(child.menuId);
            removeChildrenKeys(fullMenuTree, child.menuId, removedKeys);
          }
        });
      }
    });

    // 最终的选中状态
    removedKeys.forEach((key) => finalCheckedKeys.delete(key));
    setCheckedKeys(Array.from(finalCheckedKeys));
  };

  // 确保选中子节点时，所有父节点也被选中
  const ensureParentChecked = (keys: Set<React.Key>): Set<React.Key> => {
    const result = new Set(keys);
    keys.forEach((key) => {
      let currentKey = key;
      while (true) {
        const node = findMenuItem(fullMenuTree, currentKey);
        if (!node || !node.parentId || node.parentId === "0") break;

        console.log("ADD PARENT:", node.parentId);
        result.add(node.parentId); // 添加父节点
        currentKey = node.parentId;
      }
    });
    return result;
  };

  // 移除子节点及其子树
  const removeChildrenKeys = (
    items: MenuTreeDTO[],
    key: React.Key,
    removedKeys: Set<React.Key>
  ) => {
    const node = findMenuItem(items, key);
    if (node && node.children) {
      node.children.forEach((child) => {
        removedKeys.add(child.menuId);
        removeChildrenKeys(items, child.menuId, removedKeys); // 递归移除子节点
      });
    }
  };

  // 查找指定节点
  const findMenuItem = (
    items: MenuTreeDTO[],
    key: React.Key
  ): MenuTreeDTO | null => {
    for (const item of items) {
      if (item.menuId === key) {
        return item;
      }
      if (item.children) {
        const found = findMenuItem(item.children, key);
        if (found) {
          return found;
        }
      }
    }
    return null;
  };

  // 提交表单
  const handleFormSubmit = () => {
    console.log("checked submitted", checkedKeys);
    onFormSubmit(checkedKeys);
  };

  // 转换树形数据
  const convertToTreeData = (fullMenuTree: MenuTreeDTO[]): any[] => {
    return fullMenuTree.map((menu) => {
      return {
        title: menu.name,
        key: menu.menuId,
        value: menu.menuId,
        children: menu.children ? convertToTreeData(menu.children) : undefined,
      };
    });
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100%" }}>
      <div style={{ flex: 1 }}>
        <Tree
          checkable
          checkStrictly={true}
          treeData={convertToTreeData(fullMenuTree)}
          checkedKeys={checkedKeys}
          onCheck={onCheck}
          defaultExpandAll
          showIcon
          titleRender={(nodeData) => (
            <div style={{ cursor: "pointer" }}>{nodeData.title}</div>
          )}
        />
      </div>
      <div style={{ padding: 16, borderTop: "1px solid #e8e8e8" }}>
        <Button
          type="primary"
          onClick={handleFormSubmit}
          style={{ marginTop: 16 }}
        >
          更新菜单
        </Button>
      </div>
    </div>
  );
};

export default RoleMenuTab;
