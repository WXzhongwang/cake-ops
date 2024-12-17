// src/pages/app/menu-tree.tsx
import React, { useEffect, useMemo, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Button,
  Divider,
  Drawer,
  Form,
  Input,
  Layout,
  message,
  Radio,
  Select,
  Space,
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
import * as AllIcons from "@ant-design/icons";
import { PlusOutlined } from "@ant-design/icons";
import { PermissionDTO } from "@/models/permission";
import CreatePermissionForm from "./components/create-permission-form";
import { RoleTreeDTO } from "@/models/role";

interface RoleTreeProps {
  dispatch: Dispatch;
}

const RolePage: React.FC<RoleTreeProps> = React.memo(({ dispatch }) => {
  const [form] = Form.useForm();
  const [chooseRoleForm] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | undefined>(
    undefined
  );
  const [appRoleTree, setAppRoleTree] = useState<RoleTreeDTO[]>([]);
  const [selectedRoleItem, setSelectedRoleItem] = useState<RoleTreeDTO | null>(
    null
  );
  const [treeData, setTreeData] = useState<any[]>([]);
  const [appList, setAppList] = useState<AppDTO[]>([]);

  const [isDrawerVisible, setIsDrawerVisible] = useState(false);
  const [parentRoleId, setParentRoleId] = useState<string | undefined>(
    undefined
  );
  const [addForm] = Form.useForm();
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]);
  const [permissions, setPermissions] = useState<PermissionDTO[]>([]);
  const [editingPermissionId, setEditingPermissionId] = useState<string | null>(
    null
  );
  const [editingPermission, setEditingPermission] = useState<PermissionDTO>();
  const [permissionDrawer, setPermissionDrawer] = useState(false);
  const editPermission = (permission: PermissionDTO) => {
    setEditingPermissionId(permission.permissionId);
    // 打开编辑表单或弹窗
    setPermissionDrawer(true);
    setEditingPermission(permission);
  };

  const handleAddPermission = () => {
    setPermissionDrawer(true);
  };

  const handleCloseDrawer = () => {
    setPermissionDrawer(false);
    form.resetFields();
    setEditingPermission(undefined);
  };

  const deletePermission = (permissionId: string) => {
    dispatch({
      type: "permission/deletePermission",
      payload: {
        permissionId,
      },
      callback: (res: boolean) => {
        message.success("删除成功");
        fetchPermissions();
      },
    });
  };

  useEffect(() => {
    fetchAppList();
  }, []);

  const fetchAppList = () => {
    dispatch({
      type: "app/listApp",
      payload: {},
      callback: (res: AppDTO[]) => {
        setAppList(res);
      },
    });
  };

  useEffect(() => {
    if (appList.length > 0 && !selectedAppCode) {
      setSelectedAppCode(appList[0].id);
    }
  }, [appList]);

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
    } else {
      setSelectedRoleItem(roleItem);
      chooseRoleForm.setFieldsValue({
        ...roleItem,
      });
    }
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
        </Layout.Sider>
        <Layout.Content style={{ padding: 16 }}>
          {selectedRoleItem && (
            <Form
              form={chooseRoleForm}
              layout="vertical"
              onFinish={handleFormSubmit}
            >
              <Form.Item label="角色ID" name="roleId">
                <Input disabled />
              </Form.Item>
              <Form.Item label="角色名称" name="roleName">
                <Input />
              </Form.Item>
              <Form.Item label="角色Key" name="roleKey">
                <Input disabled />
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
                  onClick={() => deleteNode(selectedRoleItem.roleKey)}
                  style={{ marginLeft: 8 }}
                >
                  删除
                </Button>
              </Form.Item>
            </Form>
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

      {/* <Drawer
        title={editingPermissionId ? "编辑权限点" : "新增权限点"}
        width={400}
        open={permissionDrawer}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreatePermissionForm
          initialValues={editingPermission}
          onSave={handleSavePermission}
          onUpdate={handleUpdatePermission}
          onCancel={handleCloseDrawer}
        />
      </Drawer> */}
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
