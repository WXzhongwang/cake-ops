// src/pages/app/menu-tree.tsx
import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Button, Form, Input, Layout, Radio, Select, Tree } from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import { AppDTO } from "@/models/app";
import * as AllIcons from "@ant-design/icons";

interface MenuTreeProps {
  dispatch: Dispatch;
}

const MenuPage: React.FC<MenuTreeProps> = React.memo(({ dispatch }) => {
  const [form] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | undefined>(
    undefined
  );
  const [appMenu, setAppMenu] = useState<MenuTreeDTO[]>([]);
  const [selectedMenuItem, setSelectedMenuItem] = useState<MenuTreeDTO | null>(
    null
  );
  const [treeData, setTreeData] = useState<any[]>([]);
  const [appList, setAppList] = useState<AppDTO[]>([]);

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

  // 将 MenuTreeDTO 转换为 Tree 组件所需的数据格式
  const convertToTreeData = (menuTree: MenuTreeDTO[]): any[] => {
    return menuTree.map((menuItem) => {
      const IconComponent =
        AllIcons[menuItem.icon] || AllIcons.QuestionCircleOutlined;
      return {
        title: menuItem.name,
        key: menuItem.menuId,
        icon: <IconComponent />,
        children: menuItem.children
          ? convertToTreeData(menuItem.children)
          : undefined,
      };
    });
  };

  const handleAppChange = (appCode: string) => {
    setSelectedAppCode(appCode);
    if (appCode === undefined || appCode === "") {
      return;
    }
    dispatch({
      type: "menu/fetchMenuTree",
      payload: {
        appCode: appCode,
      },
      callback: (res: MenuTreeDTO[]) => {
        // 处理返回的数据
        setAppMenu(res);
        const convertedMenuData = convertToTreeData(res);
        setTreeData(convertedMenuData);
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
    setSelectedMenuItem(menuItem);
    form.setFieldsValue(menuItem);
  };

  // 获取所有图标并创建选项数组
  const icons = Object.entries(AllIcons).map(([key, IconComponent]) => ({
    label: (
      <div style={{ display: "flex", alignItems: "center" }}>
        <IconComponent style={{ marginRight: 8 }} />
        {key}
      </div>
    ),
    value: key,
  }));

  // 自定义过滤函数
  const filterIconOptions = (
    input: string,
    option?: { label: React.ReactNode; value: string }
  ) => {
    if (!option) {
      return false;
    }
    return (
      option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0 ||
      option.label.toString().toLowerCase().indexOf(input.toLowerCase()) >= 0
    );
  };

  // 处理表单提交
  const handleFormSubmit = (values: any) => {
    console.log("Received values of form: ", values);
    // 在这里添加提交逻辑，例如调用 dispatch 发送更新请求
    dispatch({
      type: "menu/updateMenu",
      payload: {
        ...values,
        appCode: selectedAppCode,
      },
      callback: (res: any) => {
        // 处理响应
        console.log("Update response:", res);
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
            style={{ marginTop: 16 }}
          />
        </Layout.Sider>
        <Layout.Content style={{ padding: 16 }}>
          {selectedMenuItem && (
            <Form form={form} layout="vertical" onFinish={handleFormSubmit}>
              <Form.Item label="菜单ID" name="menuId">
                <Input value={selectedMenuItem.menuId} disabled />
              </Form.Item>
              <Form.Item label="菜单名称" name="name">
                <Input value={selectedMenuItem.name} />
              </Form.Item>
              <Form.Item label="图标" name="icon">
                <Select
                  placeholder="选择图标"
                  options={icons}
                  style={{ width: "100%" }}
                  value={selectedMenuItem.icon}
                  onChange={(value: any) => {
                    console.log(value);
                    form.setFieldsValue({ icon: value });
                  }}
                  filterOption={filterIconOptions}
                />
              </Form.Item>
              <Form.Item label="路径" name="path">
                <Input value={selectedMenuItem.path} />
              </Form.Item>
              <Form.Item label="是否隐藏" name="hidden">
                <Radio.Group value={selectedMenuItem.hidden}>
                  <Radio value="false"> 否 </Radio>
                  <Radio value="true"> 是 </Radio>
                </Radio.Group>
              </Form.Item>
              <Form.Item label="排序" name="sort">
                <Input value={selectedMenuItem.sort} />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  提交
                </Button>
              </Form.Item>
            </Form>
          )}
        </Layout.Content>
      </Layout>
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
