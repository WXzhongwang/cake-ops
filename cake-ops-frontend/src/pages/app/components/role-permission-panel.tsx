import React, { useState, useEffect, useMemo, useCallback } from "react";
import { Tree, Table, Checkbox, Button, List, Modal, message, Tag } from "antd";
import { MenuTreeDTO } from "@/models/user";
import { PermissionDTO } from "@/models/permission";
import { MenuDTO } from "@/models/menu";
import { set } from "lodash";

interface RolePermissionTabProps {
  fullMenuTree: MenuTreeDTO[];
  rolePermissions: PermissionDTO[];
  roleMenus: MenuDTO[];
  onFormSubmit: (addedKeys: React.Key[], removedKeys: React.Key[]) => void;
}

const RolePermissionTab: React.FC<RolePermissionTabProps> = ({
  fullMenuTree,
  rolePermissions,
  roleMenus,
  onFormSubmit,
}) => {
  const [selectedMenuKey, setSelectedMenuKey] = useState<React.Key | null>(
    null
  );
  const [filteredPermissions, setFilteredPermissions] = useState<
    PermissionDTO[]
  >([]);
  const [menuKeys, setMenuKeys] = useState<React.Key[]>([]);
  const [checkedKeys, setCheckedKeys] = useState<React.Key[]>([]);
  const [initialCheckedKeys, setInitialCheckedKeys] = useState<React.Key[]>([]);
  const [addedKeys, setAddedKeys] = useState<React.Key[]>([]);
  const [removedKeys, setRemovedKeys] = useState<React.Key[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);

  // 提取用户有权限的菜单树
  const filterUserAccessibleMenus = useCallback(
    (menuTree: MenuTreeDTO[], menuIds: React.Key[]): MenuTreeDTO[] => {
      const traverse = (items: MenuTreeDTO[]): MenuTreeDTO[] => {
        return items
          .filter((item) => menuIds.includes(item.menuId))
          .map((item) => ({
            ...item,
            children: item.children ? traverse(item.children) : undefined,
          }))
          .filter((item) => item.children || menuIds.includes(item.menuId));
      };
      return traverse(menuTree);
    },
    []
  );

  // 提取用户有权限的权限点
  const filterUserAccessiblePermissions = useCallback(
    (
      permissions: PermissionDTO[],
      permissionIds: React.Key[]
    ): PermissionDTO[] => {
      return permissions.filter((perm) =>
        permissionIds.includes(perm.permissionId)
      );
    },
    []
  );

  // 提取角色已绑定的菜单键和权限键
  const extractCheckedMenuKeys = useCallback(
    (menus: MenuDTO[]): React.Key[] => menus.map((item) => item.menuId),
    []
  );
  const extractCheckedPermissionKeys = useCallback(
    (permissions: PermissionDTO[]): React.Key[] =>
      permissions.map((perm) => perm.permissionId),
    []
  );

  // 提取某个菜单下的权限点（递归）
  const extractPermissions = useCallback(
    (menuTree: MenuTreeDTO[], selectedMenuKey: React.Key): PermissionDTO[] => {
      let permissions: PermissionDTO[] = [];
      const traverse = (items: MenuTreeDTO[]) => {
        for (const item of items) {
          if (item.menuId === selectedMenuKey && item.permissions) {
            permissions = [...permissions, ...(item.permissions || [])];
            break; // 找到目标菜单后停止继续搜索
          }
          if (item.children) {
            traverse(item.children);
          }
        }
      };
      traverse(menuTree);
      return permissions;
    },
    []
  );

  // 初始化数据
  useEffect(() => {
    const initialMenuKeys = extractCheckedMenuKeys(roleMenus);
    setMenuKeys(initialMenuKeys);

    const initialPermissionKeys = extractCheckedPermissionKeys(rolePermissions);

    const filteredMenuTree = filterUserAccessibleMenus(
      fullMenuTree,
      initialMenuKeys
    );
    const allPermissions = extractAllPermissions(filteredMenuTree);

    const filteredPermissions = filterUserAccessiblePermissions(
      allPermissions,
      initialPermissionKeys
    );

    setFilteredPermissions(
      initialMenuKeys.length > 0 ? filteredPermissions : []
    );
    setCheckedKeys(initialPermissionKeys);
    setInitialCheckedKeys(initialPermissionKeys);
  }, [
    fullMenuTree,
    roleMenus,
    rolePermissions,
    filterUserAccessibleMenus,
    filterUserAccessiblePermissions,
    extractCheckedMenuKeys,
    extractCheckedPermissionKeys,
  ]);

  // 提取所有权限（递归）
  const extractAllPermissions = useCallback(
    (menuTree: MenuTreeDTO[]): PermissionDTO[] => {
      let allPermissions: PermissionDTO[] = [];
      const traverse = (items: MenuTreeDTO[]) => {
        items.forEach((item) => {
          if (item.permissions) {
            allPermissions = [...allPermissions, ...(item.permissions || [])];
          }
          if (item.children) {
            traverse(item.children);
          }
        });
      };
      traverse(menuTree);
      return allPermissions;
    },
    []
  );

  // 更新选中菜单时的权限列表
  useEffect(() => {
    if (selectedMenuKey) {
      const selectedPermissions = extractPermissions(
        fullMenuTree,
        selectedMenuKey
      );
      const filteredSelectedPermissions = filterUserAccessiblePermissions(
        selectedPermissions,
        checkedKeys
      );
      setFilteredPermissions(filteredSelectedPermissions);
    } else {
      const filteredMenuTree = filterUserAccessibleMenus(
        fullMenuTree,
        menuKeys
      );
      const allPermissions = extractAllPermissions(filteredMenuTree);
      setFilteredPermissions(allPermissions);
    }
  }, [
    selectedMenuKey,
    fullMenuTree,
    checkedKeys,
    extractAllPermissions,
    filterUserAccessiblePermissions,
    extractPermissions,
  ]);

  // 处理权限勾选逻辑
  const handleCheck = useCallback(
    (checked: React.Key[], info: any) => {
      const newAddedKeys = checked.filter(
        (key) => !initialCheckedKeys.includes(key)
      );
      const newRemovedKeys = initialCheckedKeys.filter(
        (key) => !checked.includes(key)
      );

      setCheckedKeys(checked);
      setAddedKeys(newAddedKeys);
      setRemovedKeys(newRemovedKeys);
    },
    [initialCheckedKeys]
  );

  // 转换树形数据
  const convertToTreeData = useCallback((menuTree: MenuTreeDTO[]): any[] => {
    const traverse = (items: MenuTreeDTO[]): any[] => {
      return items.map((menu) => ({
        title: menu.name,
        key: menu.menuId,
        value: menu.menuId,
        children: menu.children ? traverse(menu.children) : undefined,
      }));
    };
    return traverse(menuTree);
  }, []);

  const treeData = useMemo(() => {
    const initialMenuKeys = extractCheckedMenuKeys(roleMenus);
    const filteredMenuTree = filterUserAccessibleMenus(
      fullMenuTree,
      initialMenuKeys
    );
    return convertToTreeData(filteredMenuTree);
  }, [
    fullMenuTree,
    roleMenus,
    filterUserAccessibleMenus,
    convertToTreeData,
    extractCheckedMenuKeys,
  ]);

  // 表格列定义
  const columns = useMemo(() => {
    return [
      {
        title: "权限名称",
        dataIndex: "resourceName",
        key: "resourceName",
      },
      {
        title: "权限类型",
        dataIndex: "resourceType",
        key: "resourceType",
        render: (text: string) => {
          if (text === "query") {
            return <Tag color="blue">查询</Tag>;
          } else if (text === "operation") {
            return <Tag color="green">操作</Tag>;
          }
          return text;
        },
      },
      {
        title: "资源路径",
        dataIndex: "resourcePath",
        key: "resourcePath",
      },
      {
        title: "操作",
        dataIndex: "action",
        key: "action",
        render: (_: any, record: PermissionDTO) => (
          <Checkbox
            checked={checkedKeys.includes(record.permissionId)}
            onChange={(e) =>
              handleCheck(
                e.target.checked
                  ? [...checkedKeys, record.permissionId]
                  : checkedKeys.filter((key) => key !== record.permissionId),
                {}
              )
            }
          >
            {checkedKeys.includes(record.permissionId) ? "已开启" : "未开启"}
          </Checkbox>
        ),
      },
    ];
  }, [checkedKeys, handleCheck]);

  // 显示模态框
  const showModal = useCallback(() => {
    setIsModalVisible(true);
  }, []);

  // 确认提交
  const handleOk = useCallback(() => {
    onFormSubmit(addedKeys, removedKeys);
    setAddedKeys([]);
    setRemovedKeys([]);
    setIsModalVisible(false);
    // message.success("权限更新成功");
  }, [addedKeys, removedKeys, onFormSubmit]);

  // 取消提交
  const handleCancel = useCallback(() => {
    setIsModalVisible(false);
  }, []);

  // 菜单选择事件
  const onSelect = useCallback((selectedKeys: React.Key[]) => {
    setSelectedMenuKey(selectedKeys[0]);
  }, []);

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100%" }}>
      <div style={{ display: "flex", height: "70%" }}>
        <div style={{ width: "30%", borderRight: "1px solid #e8e8e8" }}>
          <Tree
            treeData={treeData}
            onSelect={onSelect}
            defaultExpandAll
            showIcon
            titleRender={(nodeData) => (
              <div style={{ cursor: "pointer" }}>{nodeData.title}</div>
            )}
          />
        </div>
        <div style={{ width: "70%", padding: 16 }}>
          <Table
            dataSource={filteredPermissions}
            columns={columns}
            rowKey="permissionId"
            pagination={false}
          />
        </div>
      </div>
      <div
        style={{ height: "30%", padding: 16, borderTop: "1px solid #e8e8e8" }}
      >
        <Button type="primary" onClick={showModal} style={{ marginTop: 16 }}>
          提交
        </Button>
      </div>
      <Modal
        title="确认权限变更"
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        width={800}
      >
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <div style={{ width: "45%" }}>
            <h4>新增权限</h4>
            <List
              size="small"
              bordered
              dataSource={addedKeys}
              renderItem={(item) => (
                <List.Item>
                  {
                    filteredPermissions.find(
                      (perm) => perm.permissionId === item
                    )?.resourceName
                  }
                </List.Item>
              )}
            />
          </div>
          <div style={{ width: "45%" }}>
            <h4>删除权限</h4>
            <List
              size="small"
              bordered
              dataSource={removedKeys}
              renderItem={(item) => (
                <List.Item>
                  {
                    filteredPermissions.find(
                      (perm) => perm.permissionId === item
                    )?.resourceName
                  }
                </List.Item>
              )}
            />
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default RolePermissionTab;
