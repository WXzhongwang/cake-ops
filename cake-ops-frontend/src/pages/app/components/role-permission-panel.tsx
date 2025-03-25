// src/pages/app/components/role-permission-panel.tsx

import React, { useState, useEffect, useMemo, useCallback } from "react";
import { Tree, Table, Checkbox, Button, List, Modal, message } from "antd";
import { MenuTreeDTO } from "@/models/user";
import { PermissionDTO } from "@/models/permission";
import { MenuDTO } from "@/models/menu";

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
  const [expandedMenuKeys, setExpandedMenuKeys] = useState<React.Key[]>([]);
  const [selectedMenuKey, setSelectedMenuKey] = useState<React.Key | null>(
    null
  );
  const [permissions, setPermissions] = useState<PermissionDTO[]>([]);
  const [checkedKeys, setCheckedKeys] = useState<React.Key[]>([]);
  const [initialCheckedKeys, setInitialCheckedKeys] = useState<React.Key[]>([]);
  const [addedKeys, setAddedKeys] = useState<React.Key[]>([]);
  const [removedKeys, setRemovedKeys] = useState<React.Key[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);

  const extractPermissions = useMemo(() => {
    return (menuTree: MenuTreeDTO[], key: React.Key): PermissionDTO[] => {
      let foundPermissions: PermissionDTO[] = [];

      const traverse = (items: MenuTreeDTO[]) => {
        items.forEach((item) => {
          if (item.menuId === key && item.permissions) {
            foundPermissions = [
              ...foundPermissions,
              ...(item.permissions || []),
            ];
          }
          if (item.children) {
            traverse(item.children);
          }
        });
      };

      traverse(menuTree);
      return foundPermissions;
    };
  }, []);

  const extractAllPermissions = useMemo(() => {
    return (menuTree: MenuTreeDTO[]): PermissionDTO[] => {
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
    };
  }, []);

  const extractCheckedMenuKeys = (menus: MenuDTO[]): React.Key[] => {
    const keys: React.Key[] = [];
    menus.forEach((item) => {
      keys.push(item.menuId);
    });
    return keys;
  };

  const extractCheckedPermissionKeys = useMemo(() => {
    return (rolePermissions: PermissionDTO[]): React.Key[] => {
      const keys: React.Key[] = [];
      rolePermissions.forEach((perm) => {
        keys.push(perm.permissionId);
      });
      return keys;
    };
  }, [rolePermissions]);

  useEffect(() => {
    const allPermissions = extractAllPermissions(fullMenuTree);
    setPermissions(allPermissions);

    // 设置菜单expandKeys
  }, [fullMenuTree, extractAllPermissions]);

  useEffect(() => {
    if (selectedMenuKey) {
      const selectedPermissions = extractPermissions(
        fullMenuTree,
        selectedMenuKey
      );
      setPermissions(selectedPermissions);
    } else {
      const allPermissions = extractAllPermissions(fullMenuTree);
      setPermissions(allPermissions);
    }
  }, [
    selectedMenuKey,
    fullMenuTree,
    extractPermissions,
    extractAllPermissions,
  ]);

  useEffect(() => {
    const initialKeys = extractCheckedPermissionKeys(rolePermissions);
    setCheckedKeys(initialKeys);
    setInitialCheckedKeys(initialKeys);
  }, [rolePermissions, extractCheckedPermissionKeys]);

  useEffect(() => {
    const initialCheckedMenuKeys = extractCheckedMenuKeys(roleMenus);
    setExpandedMenuKeys(
      initialCheckedMenuKeys.length > 0
        ? initialCheckedMenuKeys
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

  const onSelect = useCallback((selectedKeys: React.Key[], info: any) => {
    const selectedKey = selectedKeys[0];
    setSelectedMenuKey(selectedKey);
    setExpandedMenuKeys((prevKeys) => {
      if (prevKeys.includes(selectedKey)) {
        return prevKeys.filter((key) => key !== selectedKey);
      } else {
        return [...prevKeys, selectedKey];
      }
    });
  }, []);

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

  const showModal = useCallback(() => {
    setIsModalVisible(true);
  }, []);

  const handleOk = useCallback(() => {
    onFormSubmit(addedKeys, removedKeys);
    setAddedKeys([]);
    setRemovedKeys([]);
    setIsModalVisible(false);
    message.success("权限更新成功");
  }, [addedKeys, removedKeys, onFormSubmit]);

  const handleCancel = useCallback(() => {
    setIsModalVisible(false);
  }, []);

  const convertToTreeData = useMemo(() => {
    return (fullMenuTree: MenuTreeDTO[]): any[] => {
      const traverse = (items: MenuTreeDTO[]): any[] => {
        return items.map((menu) => {
          return {
            title: menu.name,
            key: menu.menuId,
            value: menu.menuId,
            children: menu.children ? traverse(menu.children) : undefined,
          };
        });
      };
      return traverse(fullMenuTree);
    };
  }, []);

  const treeData = useMemo(() => {
    return convertToTreeData(fullMenuTree);
  }, [fullMenuTree, convertToTreeData]);

  const columns = useMemo(() => {
    return [
      {
        title: "权限名称",
        dataIndex: "resourceName",
        key: "resourceName",
      },
      {
        title: "操作",
        dataIndex: "action",
        key: "action",
        render: (_: any, record: PermissionDTO) => (
          <Checkbox
            checked={checkedKeys.includes(record.permissionId)}
            onChange={(e) => handleCheck([record.permissionId], {})}
          >
            {checkedKeys.includes(record.permissionId) ? "已开启" : "未开启"}
          </Checkbox>
        ),
      },
    ];
  }, [checkedKeys, handleCheck]);

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100%" }}>
      <div style={{ display: "flex", height: "70%" }}>
        <div style={{ width: "30%", borderRight: "1px solid #e8e8e8" }}>
          <Tree
            treeData={treeData}
            onSelect={onSelect}
            expandedKeys={expandedMenuKeys}
            defaultExpandAll
            showIcon
            titleRender={(nodeData) => (
              <div style={{ cursor: "pointer" }}>{nodeData.title}</div>
            )}
          />
        </div>
        <div style={{ width: "70%", padding: 16 }}>
          <Table
            dataSource={permissions}
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
                    permissions.find((perm) => perm.permissionId === item)
                      ?.resourceName
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
                    permissions.find((perm) => perm.permissionId === item)
                      ?.resourceName
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
