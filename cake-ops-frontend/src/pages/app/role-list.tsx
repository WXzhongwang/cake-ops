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
import { RoleTreeDTO } from "@/models/role";
import RoleBasicInfoTab from "./components/role-basic-pannel";
import CreateRoleDrawer from "./components/create-role-drawer";
import RoleMenuTab from "./components/role-menu-pannel";
import RolePermissionTab from "./components/role-permission-panel";
import { MenuDTO } from "@/models/menu";
import { PermissionDTO } from "@/models/permission";

interface RoleTreeProps {
  dispatch: Dispatch;
}

const RolePage: React.FC<RoleTreeProps> = React.memo(({ dispatch }) => {
  const [loading, setLoading] = useState(false);
  const [appChooseForm] = Form.useForm();
  const [chooseRoleForm] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | null>(null);
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
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]);
  const [fullMenuTree, setFullMenuTree] = useState<MenuTreeDTO[]>([]);
  const [roleMenus, setRoleMenus] = useState<MenuDTO[]>([]);
  const [rolePermissions, setRolePermissions] = useState<PermissionDTO[]>([]);

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
    if (appList.length > 0 && selectedAppCode === null) {
      setSelectedAppCode(appList[0].appCode);
    }
  }, [appList, selectedAppCode]);

  useEffect(() => {
    if (selectedAppCode) {
      fetchRoleTree(selectedAppCode);
      fetchParentMenuTree(selectedAppCode, "");
    }
  }, [selectedAppCode]);

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

  const extractCheckedMenuKeys = (roles: RoleTreeDTO[]): React.Key[] => {
    const keys: React.Key[] = [];
    roles.forEach((roleItem) => {
      keys.push(roleItem.roleId);
      if (roleItem.children) {
        keys.push(...extractCheckedMenuKeys(roleItem.children));
      }
    });
    return keys;
  };

  const handleAppChange = (appCode: string) => {
    setSelectedAppCode(appCode);
    setSelectedRoleItem(null);
    if (appCode === undefined || appCode === "") {
      return;
    }
    fetchRoleTree(appCode);
    fetchParentMenuTree(appCode, "");
  };

  const fetchRoleTree = (appCode: string | null) => {
    if (appCode === null || appCode === "") {
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
        // setExpandedKeys(["virtual-root"]);
        // 展开全部节点
        const expandedKeys = extractCheckedMenuKeys(updatedRoleTree);
        setExpandedKeys(expandedKeys);
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
      setRoleMenus([]);
      setRolePermissions([]);
    } else {
      setSelectedRoleItem(roleItem);
      chooseRoleForm.setFieldsValue({
        ...roleItem,
      });
      if (roleItem && selectedAppCode) {
        // 通过选中节点获取上级节点的全量菜单和权限点
        fetchParentMenuTree(selectedAppCode, roleItem.parentId);
        // 通过当前节点的全量菜单和权限点
        listRoleMenu(selectedAppCode, roleItem.roleId);
        listRolePermissions(selectedAppCode, roleItem.roleId);
      }
    }
  };

  const listRoleMenu = (
    appCode: string | undefined,
    roleId: string | undefined
  ) => {
    dispatch({
      type: "role/listRoleMenu",
      payload: { appCode, roleId },
      callback: (res: MenuDTO[]) => {
        setRoleMenus(res);
      },
    });
  };

  const listRolePermissions = (
    appCode: string | undefined,
    roleId: string | undefined
  ) => {
    dispatch({
      type: "role/listRolePermission",
      payload: { appCode, roleId },
      callback: (res: PermissionDTO[]) => {
        setRolePermissions(res);
      },
    });
  };

  const fetchParentMenuTree = (
    appCode: string | undefined,
    parentRoleId: string | undefined
  ) => {
    dispatch({
      type: "role/fetchRoleMenuTree",
      payload: { appCode, roleId: parentRoleId },
      callback: (res: MenuTreeDTO[]) => {
        setFullMenuTree(res);
      },
    });
  };

  // 处理表单提交
  const handleUpdateRole = (values: any) => {
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

  const deleteRole = (roleId: string) => {
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
  };

  const onClose = () => {
    setIsDrawerVisible(false);
  };

  const saveRole = (values: any) => {
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
        message.success("添加成功");
        fetchRoleTree(selectedAppCode);
        onClose();
      },
    });
  };

  const handleMenuSubmit = (checkedMenuKeys: React.Key[]) => {
    // 处理菜单提交逻辑
    console.log("Checked menu keys:", checkedMenuKeys);
    if (selectedRoleItem && selectedAppCode) {
      dispatch({
        type: "role/grantRoleMenu",
        payload: {
          appCode: selectedAppCode,
          roleId: selectedRoleItem.roleId,
          menuIds: checkedMenuKeys,
        },
        callback: (res: any) => {
          message.success("角色绑定菜单成功");
          fetchParentMenuTree(selectedAppCode, selectedRoleItem.roleId);
        },
      });
    }
  };

  const handlePermissionSubmit = (
    addedKeys: React.Key[],
    removedKeys: React.Key[]
  ) => {
    if (selectedRoleItem && selectedAppCode) {
      dispatch({
        type: "role/grantRolePermission",
        payload: {
          appCode: selectedAppCode,
          roleId: selectedRoleItem.roleId,
          addedPermissionIds: addedKeys,
          removedPermissionIds: removedKeys,
        },
        callback: (res: any) => {
          message.success("角色权限更新成功");
          fetchParentMenuTree(selectedAppCode, selectedRoleItem.roleId);
        },
      });
    }
  };

  return (
    <PageContainer title="角色管理">
      <Layout style={{ height: "80vh" }}>
        <Layout.Sider width="25%" style={{ background: "#fff", padding: 16 }}>
          <Form
            // form={appChooseForm}
            layout="vertical"
          >
            <Form.Item>
              <Select value={selectedAppCode} onChange={handleAppChange}>
                {appList.map((app) => (
                  <Select.Option key={app.appCode} value={app.appCode}>
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
                <RoleBasicInfoTab
                  form={chooseRoleForm}
                  roleItem={selectedRoleItem}
                  treeData={treeData}
                  updateRole={handleUpdateRole}
                  deleteRole={deleteRole}
                />
              </Tabs.TabPane>
              <Tabs.TabPane tab="角色菜单" key="2">
                <RoleMenuTab
                  fullMenuTree={fullMenuTree}
                  roleMenus={roleMenus}
                  onFormSubmit={handleMenuSubmit}
                />
              </Tabs.TabPane>
              <Tabs.TabPane tab="角色权限" key="3">
                <RolePermissionTab
                  fullMenuTree={fullMenuTree}
                  roleMenus={roleMenus}
                  rolePermissions={rolePermissions}
                  onFormSubmit={handlePermissionSubmit}
                />
              </Tabs.TabPane>
            </Tabs>
          )}
        </Layout.Content>
      </Layout>
      <CreateRoleDrawer
        visible={isDrawerVisible}
        onClose={onClose}
        treeData={treeData}
        selectedAppCode={selectedAppCode}
        saveRole={saveRole}
        finalParentRoleId={parentRoleId}
      />
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
