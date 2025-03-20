// src/pages/app/role-list.tsx
import React, { useEffect, useMemo, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Button,
  Checkbox,
  Divider,
  Drawer,
  Form,
  Input,
  Layout,
  message,
  Radio,
  Select,
  Space,
  Spin,
  Table,
  Tabs,
  Tag,
  Tree,
  TreeSelect,
} from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import { AppDTO } from "@/models/app";
import { PlusOutlined } from "@ant-design/icons";
import { PermissionDTO } from "@/models/permission";
import { RoleTreeDTO } from "@/models/role";
import { CheckboxChangeEvent } from "antd/es/checkbox";

interface RoleTreeProps {
  dispatch: Dispatch;
}

const RolePage: React.FC<RoleTreeProps> = React.memo(({ dispatch }) => {
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();
  const [chooseRoleForm] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | undefined>(
    undefined
  );
  const [appRoleTree, setAppRoleTree] = useState<RoleTreeDTO[]>([]);
  const [selectedRoleItem, setSelectedRoleItem] = useState<RoleTreeDTO | null>(
    null
  );
  const [treeData, setTreeData] = useState<RoleTreeDTO[]>([]);
  const [appList, setAppList] = useState<AppDTO[]>([]);

  const [isDrawerVisible, setIsDrawerVisible] = useState(false);
  const [parentRoleId, setParentRoleId] = useState<string | undefined>(
    undefined
  );
  const [addForm] = Form.useForm();
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]);
  const [fullMenuTree, setFullMenuTree] = useState<MenuTreeDTO[]>([]);
  const [roleMenuTree, setRoleMenuTree] = useState<MenuTreeDTO[]>([]);
  const [mergedMenuTree, setMergedMenuTree] = useState<MenuTreeDTO[]>([]);

  useEffect(() => {
    fetchAppList();
  }, []);
  const fetchAppList = () => {
    dispatch({
      type: "app/listApp",
      payload: {},
      callback: (res: AppDTO[]) => {
        setAppList(res);
        setLoading(false);
      },
    });
  };

  useEffect(() => {
    if (appList.length > 0 && !selectedAppCode) {
      setSelectedAppCode(appList[0].id);
    }
  }, [appList]);

  useEffect(() => {
    if (fullMenuTree.length > 0 && roleMenuTree.length > 0) {
      const mergedTree = fullMenuTree.map((menu) => {
        return mergeMenuWithRoleMenu(menu, roleMenuTree);
      });
      setMergedMenuTree(mergedTree);
    }
  }, [fullMenuTree, roleMenuTree]);

  const mergeMenuWithRoleMenu = (
    menu: MenuTreeDTO,
    roleMenus: MenuTreeDTO[]
  ): MenuTreeDTO => {
    const roleMenu = roleMenus.find((rm) => rm.menuId === menu.menuId);
    const isChecked = !!roleMenu;
    const children = menu.children
      ? menu.children.map((child) => mergeMenuWithRoleMenu(child, roleMenus))
      : [];
    const permissions = menu.permissions.map((perm) => {
      const rolePerm = roleMenu?.permissions.find(
        (rp) => rp.permissionId === perm.permissionId
      );
      return {
        ...perm,
        checked: isChecked && !!rolePerm,
      };
    });

    return {
      ...menu,
      checked: isChecked,
      children: children,
      halfChecked: children.some((child) => child.checked || child.halfChecked),
      permissions: permissions,
    };
  };

  const convertToTreeData = (roleTree: RoleTreeDTO[]): any[] => {
    return roleTree.map((roleItem) => {
      return {
        title: roleItem.roleName,
        key: roleItem.roleId,
        value: roleItem.roleId, // 确保每个节点都有 value
        children: roleItem.children
          ? convertToTreeData(roleItem.children)
          : undefined,
      };
    });
  };

  const handleAppChange = (appCode: string) => {
    setSelectedAppCode(appCode);
    setSelectedRoleItem(null);
    if (appCode === undefined || appCode === "") {
      return;
    }
    fetchRoleTree(appCode);
    fetchRoleMenuPermissionTree(appCode, "");
  };
  const fetchRoleTree = (appCode: string | undefined) => {
    if (appCode === undefined || appCode === "") {
      return;
    }
    dispatch({
      type: "role/fetchRoleTree",
      payload: {
        appCode: appCode,
      },
      callback: (res: RoleTreeDTO[]) => {
        // 处理返回的数据，添加一个虚拟的顶级节点
        const virtualRoot: RoleTreeDTO = {
          roleId: "virtual-root",
          roleName: "根节点",
          roleDesc: "根节点",
          roleKey: "virtual-root",
          children: res,
          parentId: "0",
          status: "0",
          isDeleted: false,
          gmtCreate: new Date(),
          gmtModified: new Date(),
        };
        const updatedRoleTree = [virtualRoot];
        setAppRoleTree(updatedRoleTree);
        const convertedTreeData = convertToTreeData(updatedRoleTree);
        setTreeData(convertedTreeData);
        setExpandedKeys(["virtual-root"]); // 清空展开的节点
      },
    });
  };

  const findRoleItem = (
    items: RoleTreeDTO[],
    key: React.Key
  ): RoleTreeDTO | null => {
    for (const item of items) {
      if (item.roleId === key) {
        return item;
      }
      if (item.children) {
        const found = findRoleItem(item.children, key);
        if (found) {
          return found;
        }
      }
    }
    return null;
  };

  const onTreeSelect = (selectedKeys: React.Key[], info: any) => {
    const selectedKey = selectedKeys[0];
    console.log("Selected key:", selectedKey);
    const roleItem = findRoleItem(appRoleTree, selectedKey);

    // 检查是否是虚拟根节点
    if (roleItem && roleItem.roleId === "virtual-root") {
      setSelectedRoleItem(null); // 不设置 selectedMenuItem
      chooseRoleForm.resetFields(); // 清空表单
      setRoleMenuTree([]); // 清空角色菜单树
    } else {
      setSelectedRoleItem(roleItem);
      chooseRoleForm.setFieldsValue({
        ...roleItem,
      });
      if (roleItem && selectedAppCode) {
        // 通过选中节点获取上级节点的全量菜单和权限点
        fetchParentMenuPermissionTree(selectedAppCode, roleItem.parentId);
        // 通过当前节点的全量菜单和权限点
        fetchRoleMenuPermissionTree(selectedAppCode, roleItem.roleId);
      }
    }
  };

  const fetchRoleMenuPermissionTree = (
    appCode: string | undefined,
    roleId: string | undefined
  ) => {
    dispatch({
      type: "role/fetchRoleMenuPermissionTree",
      payload: { appCode, roleId },
      callback: (res: MenuTreeDTO[]) => {
        setRoleMenuTree(res);
      },
    });
  };

  const fetchParentMenuPermissionTree = (
    appCode: string | undefined,
    parentRoleId: string | undefined
  ) => {
    dispatch({
      type: "role/fetchRoleMenuPermissionTree",
      payload: { appCode, roleId: parentRoleId },
      callback: (res: MenuTreeDTO[]) => {
        setFullMenuTree(res);
      },
    });
  };

  // 处理表单提交
  const handleFormSubmit = (values: any) => {
    dispatch({
      type: "role/updateRole",
      payload: {
        ...values,
        appCode: selectedAppCode,
      },
      callback: (res: any) => {
        // 处理响应
        message.success("更新成功");
        fetchRoleTree(selectedAppCode);
      },
    });
  };

  const deleteNode = (roleId: string) => {
    if (!roleId) return;
    dispatch({
      type: "role/deleteRole",
      payload: {
        roleId: roleId,
      },
      callback: (res: any) => {
        // 处理响应
        message.success("删除成功");
        fetchRoleTree(selectedAppCode);
      },
    });
  };

  // 定义新增按钮的渲染函数
  const renderTitle = (nodeData: any) => {
    return (
      <div
        style={{
          width: "90%",
          display: "inline-flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <span>{nodeData.title}</span>
        <PlusOutlined
          onClick={(e) => {
            e.stopPropagation();
            showDrawer(nodeData.key);
          }}
        />
      </div>
    );
  };

  const showDrawer = (parentId?: string) => {
    setIsDrawerVisible(true);
    setParentRoleId(parentId);
    addForm.setFieldsValue({ parentId });
  };

  const onClose = () => {
    setIsDrawerVisible(false);
    addForm.resetFields();
  };

  const onAddFormSubmit = (values: any) => {
    console.log("Received values of form: ", values);
    let finalParentRoleId = values.parentId;
    if (finalParentRoleId === "virtual-root") {
      finalParentRoleId = null;
    }
    dispatch({
      type: "role/createRole",
      payload: {
        ...values,
        appCode: selectedAppCode,
        parentId: finalParentRoleId,
      },
      callback: (res: any) => {
        // 处理响应
        message.success("添加成功");
        fetchRoleTree(selectedAppCode);
        onClose();
      },
    });
  };

  const onCheck = (
    checked: React.Key[] | { checked: React.Key[]; halfChecked: React.Key[] },
    info: any
  ) => {
    const allCheckedKeys = Array.isArray(checked) ? checked : checked.checked;
    const updatedTree = updateCheckedState(mergedMenuTree, allCheckedKeys);
    setMergedMenuTree(updatedTree);
  };

  const renderMenuTree = (menus: MenuTreeDTO[]): any[] => {
    return menus.map((menu) => ({
      key: menu.menuId,
      title: (
        <div>
          {menu.name}
          <Checkbox
            checked={menu.checked}
            onChange={(e) => handleMenuCheck(e, menu.menuId)}
          />
        </div>
      ),
      children: menu.children ? renderMenuTree(menu.children) : [],
      permissions: menu.permissions?.map((perm) => ({
        key: perm.permissionId,
        title: (
          <div>
            {perm.resourceName}
            <Checkbox
              checked={perm.checked}
              onChange={(e) => handlePermissionCheck(e, perm.permissionId)}
            />
          </div>
        ),
      })),
    }));
  };

  // 新增处理菜单勾选事件
  const handleMenuCheck = (e: CheckboxChangeEvent, menuId: string) => {
    const updatedTree = updateCheckedState(
      mergedMenuTree,
      [menuId],
      false,
      menuId,
      e.target.checked
    );
    setMergedMenuTree(updatedTree);
  };

  // 新增处理权限勾选事件
  const handlePermissionCheck = (
    e: CheckboxChangeEvent,
    permissionId: string
  ) => {
    const updatedTree = updateCheckedState(
      mergedMenuTree,
      [],
      false,
      permissionId,
      e.target.checked
    );
    setMergedMenuTree(updatedTree);
  };

  // 更新updateCheckedState以支持独立更新菜单和权限
  const updateCheckedState = (
    menus: MenuTreeDTO[],
    checkedMenuIds: React.Key[] = [],
    checkAllMenus: boolean = false,
    checkedPermissionId?: string,
    checkPermission?: boolean
  ): MenuTreeDTO[] => {
    return menus.map((menu) => {
      let isChecked = checkAllMenus
        ? checkAllMenus
        : checkedMenuIds.includes(menu.menuId);
      if (checkedPermissionId && menu.permissions) {
        const permission = menu.permissions.find(
          (p) => p.permissionId === checkedPermissionId
        );
        if (permission) {
          permission.checked = checkPermission ?? false;
        }
      }

      const children = menu.children
        ? updateCheckedState(
            menu.children,
            checkedMenuIds,
            checkAllMenus,
            checkedPermissionId,
            checkPermission
          )
        : [];
      const permissions = menu.permissions?.map((perm) => ({
        ...perm,
        checked: perm.checked || (checkAllMenus && isChecked),
      }));

      const halfChecked = children.some(
        (child) => child.checked || child.halfChecked
      );

      return {
        ...menu,
        checked: isChecked,
        children: children,
        halfChecked: halfChecked,
        permissions: permissions,
      };
    });
  };

  const onPermissionFormSubmit = () => {
    const selectedMenus = extractSelectedMenus(mergedMenuTree);
    const selectedPermissions = extractSelectedPermissions(mergedMenuTree);

    dispatch({
      type: "role/updateRolePermissions",
      payload: {
        appCode: selectedAppCode,
        roleId: selectedRoleItem?.roleId,
        menuIds: selectedMenus.map((menu) => menu.menuId),
        permissionIds: selectedPermissions.map((perm) => perm.permissionId),
      },
      callback: (res: any) => {
        message.success("权限更新成功");
        fetchRoleMenuPermissionTree(selectedAppCode, selectedRoleItem?.roleId);
      },
    });
  };

  const extractSelectedMenus = (menus: MenuTreeDTO[]): MenuTreeDTO[] => {
    let selectedMenus: MenuTreeDTO[] = [];
    menus.forEach((menu) => {
      if (menu.checked) {
        selectedMenus.push(menu);
      }
      if (menu.children) {
        selectedMenus = selectedMenus.concat(
          extractSelectedMenus(menu.children)
        );
      }
    });
    return selectedMenus;
  };

  const extractSelectedPermissions = (
    menus: MenuTreeDTO[]
  ): PermissionDTO[] => {
    let selectedPermissions: PermissionDTO[] = [];
    menus.forEach((menu) => {
      if (menu.checked) {
        selectedPermissions = selectedPermissions.concat(
          menu.permissions.filter((perm) => perm.checked)
        );
      }
      if (menu.children) {
        selectedPermissions = selectedPermissions.concat(
          extractSelectedPermissions(menu.children)
        );
      }
    });
    return selectedPermissions;
  };

  return (
    <PageContainer title="角色管理">
      <Layout style={{ height: "80vh" }}>
        <Layout.Sider width="25%" style={{ background: "#fff", padding: 16 }}>
          <Form form={form} layout="vertical">
            <Form.Item label="选择应用" name="appId">
              <Select value={selectedAppCode} onChange={handleAppChange}>
                {appList.map((app) => (
                  <Select.Option key={app.id} value={app.appCode}>
                    {app.appName}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Form>
          {loading ? (
            <Spin />
          ) : (
            <Tree
              showIcon
              defaultExpandAll
              treeData={treeData}
              onSelect={onTreeSelect}
              blockNode
              style={{ marginTop: 16, width: "100%" }}
              titleRender={renderTitle}
              expandedKeys={expandedKeys}
              onExpand={(expandedKeys) => {
                setExpandedKeys(expandedKeys);
              }}
            />
          )}
        </Layout.Sider>
        <Layout.Content style={{ padding: 16 }}>
          {selectedRoleItem && (
            <Tabs defaultActiveKey="1">
              <Tabs.TabPane tab="基本信息" key="1">
                <Form
                  form={chooseRoleForm}
                  layout="vertical"
                  onFinish={handleFormSubmit}
                >
                  <Form.Item label="角色ID" name="roleId">
                    <Input disabled />
                  </Form.Item>
                  <Form.Item
                    label="角色名称"
                    name="roleName"
                    rules={[{ required: true, message: "请输入角色名称" }]}
                  >
                    <Input />
                  </Form.Item>
                  <Form.Item
                    label="角色Key"
                    name="roleKey"
                    rules={[{ required: true, message: "请输入角色Key" }]}
                  >
                    <Input />
                  </Form.Item>
                  <Form.Item label="角色描述" name="roleDesc">
                    <Input />
                  </Form.Item>
                  <Form.Item label="上级角色" name="parentId">
                    <TreeSelect
                      showSearch
                      style={{ width: "100%" }}
                      dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
                      treeDefaultExpandAll
                      treeData={treeData}
                      treeNodeFilterProp="title"
                      value={selectedRoleItem?.parentId}
                      disabled
                    />
                  </Form.Item>
                  <Form.Item>
                    <Button type="primary" htmlType="submit">
                      更新
                    </Button>
                    <Button
                      type="primary"
                      danger
                      onClick={() => deleteNode(selectedRoleItem.roleId)}
                      style={{ marginLeft: 8 }}
                    >
                      删除
                    </Button>
                  </Form.Item>
                </Form>
              </Tabs.TabPane>
              <Tabs.TabPane tab="菜单和权限" key="2">
                <Tree
                  treeData={renderMenuTree(mergedMenuTree)}
                  checkable
                  onCheck={onCheck}
                />
                <Button type="primary" onClick={onPermissionFormSubmit}>
                  提交
                </Button>
              </Tabs.TabPane>
            </Tabs>
          )}
        </Layout.Content>
      </Layout>
      <Drawer
        title="添加角色"
        placement="right"
        onClose={onClose}
        open={isDrawerVisible}
        width={400}
      >
        <Form form={addForm} layout="vertical" onFinish={onAddFormSubmit}>
          <Form.Item label="角色名称" name="roleName">
            <Input />
          </Form.Item>
          <Form.Item label="角色Key" name="roleKey">
            <Input />
          </Form.Item>
          <Form.Item label="角色描述" name="roleDesc">
            <Input />
          </Form.Item>
          <Form.Item label="上级角色" name="parentId">
            <TreeSelect
              showSearch
              style={{ width: "100%" }}
              dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
              placeholder="请选择上级角色"
              allowClear
              treeDefaultExpandAll
              treeData={treeData}
              treeNodeFilterProp="title"
              value={parentRoleId}
              onChange={(value) => {
                setParentRoleId(value);
              }}
            />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </Drawer>
    </PageContainer>
  );
});

export default connect(
  ({
    user,
  }: {
    user: {
      isLogin: boolean;
      userData: API.UserInfo;
      menu: UserRoleMenuDTO;
    };
  }) => ({
    isLogin: user.isLogin,
    userData: user.userData,
    menu: user.menu,
  })
)(RolePage);
