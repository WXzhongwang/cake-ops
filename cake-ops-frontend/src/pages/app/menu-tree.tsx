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

interface MenuTreeProps {
  dispatch: Dispatch;
}

const MenuPage: React.FC<MenuTreeProps> = React.memo(({ dispatch }) => {
  const [form] = Form.useForm();
  const [menuItemForm] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | undefined>(
    undefined
  );
  const [appMenu, setAppMenu] = useState<MenuTreeDTO[]>([]);
  const [selectedMenuItem, setSelectedMenuItem] = useState<MenuTreeDTO | null>(
    null
  );
  const [treeData, setTreeData] = useState<any[]>([]);
  const [appList, setAppList] = useState<AppDTO[]>([]);

  const [isDrawerVisible, setIsDrawerVisible] = useState(false);
  const [parentMenuId, setParentMenuId] = useState<string | undefined>(
    undefined
  );
  const [addForm] = Form.useForm();
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]); // 新增 expandedKeys 状态
  const [permissions, setPermissions] = useState<PermissionDTO[]>([]);
  const [editingPermissionId, setEditingPermissionId] = useState<string | null>(
    null
  );

  const editPermission = (permissionId: string) => {
    setEditingPermissionId(permissionId);
    // 打开编辑表单或弹窗
  };

  const deletePermission = (permissionId: string) => {
    dispatch({
      type: "permission/deletePermission",
      payload: {
        permissionId,
      },
      callback: (res: any) => {
        message.success("删除成功");
        fetchPermissions();
      },
    });
  };

  const handlePermissionFormSubmit = (values: any) => {
    if (editingPermissionId) {
      // 更新权限
      dispatch({
        type: "permission/updatePermission",
        payload: {
          ...values,
          permissionId: editingPermissionId,
        },
        callback: (res: any) => {
          message.success("更新成功");
          fetchPermissions();
          setEditingPermissionId(null);
        },
      });
    } else {
      // 创建权限
      dispatch({
        type: "permission/createPermission",
        payload: {
          ...values,
          appCode: selectedAppCode,
          refMenuId: selectedMenuItem?.menuId,
        },
        callback: (res: any) => {
          message.success("添加成功");
          fetchPermissions();
        },
      });
    }
  };

  const fetchPermissions = () => {
    if (selectedMenuItem) {
      dispatch({
        type: "permission/listMenuPermission",
        payload: {
          menuId: selectedMenuItem.menuId,
          appCode: selectedAppCode,
        },
        callback: (res: PermissionDTO[]) => {
          setPermissions(res);
        },
      });
    }
  };

  useEffect(() => {
    fetchAppList();
  }, []);

  useEffect(() => {
    if (selectedMenuItem) {
      dispatch({
        type: "permission/listMenuPermission",
        payload: {
          permissionId: selectedMenuItem.menuId,
        },
        callback: (res: PermissionDTO[]) => {
          setPermissions(res);
        },
      });
    }
  }, [selectedMenuItem]);

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

  // 将 MenuTreeDTO 转换为 Tree 组件所需的数据格式
  const convertToTreeData = (menuTree: MenuTreeDTO[]): any[] => {
    return menuTree.map((menuItem) => {
      // @ts-ignore
      const IconComponent = AllIcons[menuItem.icon];
      return {
        title: menuItem.name,
        key: menuItem.menuId,
        icon: <IconComponent />,
        value: menuItem.menuId, // 确保每个节点都有 value
        children: menuItem.children
          ? convertToTreeData(menuItem.children)
          : undefined,
      };
    });
  };

  const handleAppChange = (appCode: string) => {
    setSelectedAppCode(appCode);
    setSelectedMenuItem(null);
    if (appCode === undefined || appCode === "") {
      return;
    }
    fetchMenuTree(appCode);
  };

  const fetchMenuTree = (appCode: string | undefined) => {
    if (appCode === undefined || appCode === "") {
      return;
    }
    dispatch({
      type: "menu/fetchMenuTree",
      payload: {
        appCode: appCode,
      },
      callback: (res: MenuTreeDTO[]) => {
        // 处理返回的数据，添加一个虚拟的顶级节点
        const virtualRoot: MenuTreeDTO = {
          menuId: "virtual-root",
          name: "根节点",
          icon: "FolderOutlined", // 假设使用 FolderOutlined 图标
          children: res,
          path: "",
          hidden: false,
          sort: 0,
          parentId: "0",
          level: 0,
          isDeleted: "false",
        };
        const updatedMenuData = [virtualRoot];
        setAppMenu(updatedMenuData);
        const convertedMenuData = convertToTreeData(updatedMenuData);
        setTreeData(convertedMenuData);
        setExpandedKeys(["virtual-root"]); // 清空展开的节点
      },
    });
  };

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

  const onTreeSelect = (selectedKeys: React.Key[], info: any) => {
    const selectedKey = selectedKeys[0];
    console.log("Selected key:", selectedKey);
    const menuItem = findMenuItem(appMenu, selectedKey);

    // 检查是否是虚拟根节点
    if (menuItem && menuItem.menuId === "virtual-root") {
      setSelectedMenuItem(null); // 不设置 selectedMenuItem
      menuItemForm.resetFields(); // 清空表单
    } else {
      setSelectedMenuItem(menuItem);
      menuItemForm.setFieldsValue({
        ...menuItem,
        hidden: menuItem?.hidden ? "true" : "false",
      });
    }
  };

  // 获取所有图标并创建选项数组
  const icons = Object.entries(AllIcons).map(([key, IconComponent]) => {
    // 确保 IconComponent 是一个有效的 JSX 组件
    const ValidIconComponent = IconComponent as unknown as React.ComponentType<{
      style?: React.CSSProperties;
    }>;
    return {
      label: (
        <div style={{ display: "flex", alignItems: "center" }}>
          <ValidIconComponent style={{ marginRight: 8 }} />
          {key}
        </div>
      ),
      value: key,
    };
  });
  // 自定义过滤函数
  const filterIconOptions = (
    input: string,
    option?: { label: React.ReactNode; value: string }
  ) => {
    if (!option) {
      return false;
    }
    return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
  };

  // 处理表单提交
  const handleFormSubmit = (values: any) => {
    console.log("Received values of form: ", values);
    dispatch({
      type: "menu/updateMenu",
      payload: {
        ...values,
        appCode: selectedAppCode,
      },
      callback: (res: any) => {
        // 处理响应
        console.log("Update response:", res);
        message.success("更新成功");
      },
    });
  };

  const deleteNode = (menuId: string) => {
    if (!menuId) return;
    dispatch({
      type: "menu/deleteMenu",
      payload: {
        id: menuId,
      },
      callback: (res: any) => {
        // 处理响应
        message.success("删除成功");
      },
    });
    fetchMenuTree(selectedAppCode);
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
    setParentMenuId(parentId); // 设置 parentMenuId 为当前选中的菜单项的 menuId
    addForm.setFieldsValue({ parentId }); // 设置表单的初始值
  };

  const onClose = () => {
    setIsDrawerVisible(false);
    addForm.resetFields();
  };

  const onAddFormSubmit = (values: any) => {
    console.log("Received values of form: ", values);
    // 检查并转换 parentMenuId
    let finalParentMenuId = values.parentId;
    if (finalParentMenuId === "virtual-root") {
      finalParentMenuId = null;
    }
    dispatch({
      type: "menu/createMenu",
      payload: {
        ...values,
        appCode: selectedAppCode,
        parentId: finalParentMenuId,
      },
      callback: (res: any) => {
        // 处理响应
        message.success("添加成功");
        fetchMenuTree(selectedAppCode);
        onClose();
      },
    });
  };

  return (
    <PageContainer title="应用菜单">
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
          {selectedMenuItem && (
            <Tabs defaultActiveKey="1">
              <Tabs.TabPane tab="基本信息" key="1">
                <Form
                  form={menuItemForm}
                  layout="vertical"
                  onFinish={handleFormSubmit}
                >
                  <Form.Item label="菜单ID" name="menuId">
                    <Input disabled />
                  </Form.Item>
                  <Form.Item label="菜单名称" name="name">
                    <Input />
                  </Form.Item>
                  <Form.Item label="图标" name="icon">
                    <Select
                      placeholder="选择图标"
                      options={icons}
                      style={{ width: "100%" }}
                      onChange={(value: any) => {
                        menuItemForm.setFieldsValue({ icon: value });
                      }}
                      showSearch
                      filterOption={filterIconOptions}
                    />
                  </Form.Item>
                  <Form.Item label="路径" name="path">
                    <Input />
                  </Form.Item>
                  <Form.Item label="是否隐藏" name="hidden">
                    <Radio.Group>
                      <Radio value="false"> 否 </Radio>
                      <Radio value="true"> 是 </Radio>
                    </Radio.Group>
                  </Form.Item>
                  <Form.Item label="排序" name="sort">
                    <Input type="number" />
                  </Form.Item>
                  <Form.Item>
                    <Button type="primary" htmlType="submit">
                      更新
                    </Button>
                    <Button
                      type="primary"
                      danger
                      onClick={() => deleteNode(selectedMenuItem.menuId)}
                      style={{ marginLeft: 8 }}
                    >
                      删除
                    </Button>
                  </Form.Item>
                </Form>
              </Tabs.TabPane>

              <Tabs.TabPane tab="菜单权限" key="2">
                <Space
                  size="middle"
                  direction="vertical"
                  style={{ width: "100%" }}
                >
                  <Button type="primary">新增权限点</Button>
                  <Table
                    dataSource={permissions}
                    columns={[
                      {
                        title: "资源类型",
                        dataIndex: "resourceType",
                        key: "resourceType",
                      },
                      {
                        title: "资源名称",
                        dataIndex: "resourceName",
                        key: "resourceName",
                      },
                      {
                        title: "资源路径",
                        dataIndex: "resourcePath",
                        key: "resourcePath",
                      },
                      {
                        title: "操作",
                        key: "action",
                        render: (_, record: PermissionDTO) => (
                          <>
                            <Button
                              type="link"
                              onClick={() =>
                                editPermission(record.permissionId)
                              }
                            >
                              编辑
                            </Button>
                            <Button
                              type="link"
                              danger
                              onClick={() =>
                                deletePermission(record.permissionId)
                              }
                            >
                              删除
                            </Button>
                          </>
                        ),
                      },
                    ]}
                    pagination={false}
                    style={{ marginTop: 16 }}
                  />
                </Space>
              </Tabs.TabPane>
            </Tabs>
          )}
        </Layout.Content>
      </Layout>
      {/* 菜单抽屉 */}
      <Drawer
        title="添加菜单节点"
        placement="right"
        onClose={onClose}
        open={isDrawerVisible}
        width={400}
      >
        <Form form={addForm} layout="vertical" onFinish={onAddFormSubmit}>
          <Form.Item
            label="菜单名称"
            name="name"
            rules={[{ required: true, message: "请输入菜单名称" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item label="图标" name="icon">
            <Select
              placeholder="选择图标"
              options={icons}
              style={{ width: "100%" }}
              onChange={(value: any) => {
                addForm.setFieldsValue({ icon: value });
              }}
              showSearch
              filterOption={filterIconOptions}
            />
          </Form.Item>
          <Form.Item label="路径" name="path">
            <Input />
          </Form.Item>
          <Form.Item label="是否隐藏" name="hidden">
            <Radio.Group>
              <Radio value="false"> 否 </Radio>
              <Radio value="true"> 是 </Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label="排序" name="sort">
            <Input type="number" />
          </Form.Item>
          <Form.Item label="上级菜单" name="parentId">
            <TreeSelect
              showSearch
              style={{ width: "100%" }}
              dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
              placeholder="请选择上级菜单"
              allowClear
              treeDefaultExpandAll
              treeData={treeData}
              treeNodeFilterProp="title"
              value={parentMenuId}
              onChange={(value) => {
                console.log("parent menu id:", value);
                setParentMenuId(value);
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
)(MenuPage);
