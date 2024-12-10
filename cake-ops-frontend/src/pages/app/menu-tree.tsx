import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Form, Layout, Select, Tree } from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import { AppDTO } from "@/models/app";

interface MenuTreeProps {
  dispatch: Dispatch;
}

const MenuPage: React.FC<MenuTreeProps> = ({ dispatch }) => {
  const [form] = Form.useForm();
  const [selectedAppCode, setSelectedAppCode] = useState<string | undefined>(
    undefined
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
    return menuTree.map((menuItem) => ({
      title: menuItem.name,
      key: menuItem.menuId,
      icon: <span className={`anticon anticon-${menuItem.icon}`} />,
      children: menuItem.children
        ? convertToTreeData(menuItem.children)
        : undefined,
    }));
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
      callback: (res: API.ResponseBody<MenuTreeDTO[]>) => {
        // 处理返回的数据
        console.log(res);
        const convertedMenuData = convertToTreeData(res.content);
        setTreeData(convertedMenuData);
      },
    });
  };

  return (
    <PageContainer title="应用菜单">
      <Layout style={{ height: "100vh" }}>
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
            style={{ marginTop: 16 }}
          />
        </Layout.Sider>
        <Layout.Content style={{ padding: 16 }}>
          {/* 这里可以放置其他内容 */}
          <div>其他内容区域</div>
        </Layout.Content>
      </Layout>
    </PageContainer>
  );
};

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
