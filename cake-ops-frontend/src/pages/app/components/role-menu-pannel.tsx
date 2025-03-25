// src/pages/app/components/role-menu-pannel.tsx

import React, { useState, useEffect } from "react";
import { Tree, Button, message } from "antd";
import { MenuTreeDTO } from "@/models/user";
import { MenuDTO } from "@/models/menu";
import { RoleTreeDTO } from "@/models/role";

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
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]);

  useEffect(() => {
    const initialCheckedKeys = extractCheckedKeys(roleMenus);
    console.log("initialCheckedKeys", initialCheckedKeys);
    setCheckedKeys(initialCheckedKeys);
    // 确保在角色未绑定菜单时，expandedKeys 为空数组
    setExpandedKeys(
      initialCheckedKeys.length > 0
        ? initialCheckedKeys
        : extractFullExpandMenuKeys(fullMenuTree)
    );
  }, [roleMenus]);

  const extractFullExpandMenuKeys = (roles: MenuTreeDTO[]): React.Key[] => {
    const keys: React.Key[] = [];
    roles.forEach((roleItem) => {
      keys.push(roleItem.menuId);
      if (roleItem.children) {
        keys.push(...extractFullExpandMenuKeys(roleItem.children));
      }
    });
    return keys;
  };

  const extractCheckedKeys = (menus: MenuDTO[]): React.Key[] => {
    const keys: React.Key[] = [];
    menus.forEach((item) => {
      keys.push(item.menuId);
    });
    return keys;
  };

  const onCheck = (
    checked: React.Key[] | { checked: React.Key[]; halfChecked: React.Key[] },
    info: any
  ) => {
    if (Array.isArray(checked)) {
      setCheckedKeys(checked);
    } else {
      setCheckedKeys(checked.checked);
    }
  };

  const handleFormSubmit = () => {
    onFormSubmit(checkedKeys);
  };

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
          checkStrictly={false}
          treeData={convertToTreeData(fullMenuTree)}
          checkedKeys={checkedKeys}
          onCheck={onCheck}
          expandedKeys={expandedKeys}
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
          提交
        </Button>
      </div>
    </div>
  );
};

export default RoleMenuTab;
