import React, { useEffect, useState } from "react";
import { MenuDataItem, ProLayout, ProSettings } from "@ant-design/pro-layout";
import { Dropdown, message, Spin } from "antd";
import { logout } from "@/services/user";
import { connect, Dispatch, Link, Outlet } from "umi";
import * as allIcons from "@ant-design/icons";
import { LogoutOutlined } from "@ant-design/icons";
import defaultProps from "./_default";
import { UserInfo, UserRoleMenuDTO } from "@/models/user";
import Loading from "@/wrappers/loading";
import { MenuTreeDTO } from "@/models/menu";

interface LayoutProps {
  dispatch: Dispatch;
}

const Layout: React.FC<LayoutProps> = ({ dispatch }) => {
  const [settings, setSetting] = useState<Partial<ProSettings>>({
    fixSiderbar: true,
    layout: "top",
    splitMenus: false,
    navTheme: "light",
    contentWidth: "Fluid",
    colorPrimary: "#1677FF",
    siderMenuType: "sub",
  });
  const [pathname, setPathname] = useState("/apps");
  const [loading, setLoading] = useState(true); // 初始状态为 true
  const [menuLoaded, setMenuLoaded] = useState(false);
  const [userMenu, setUserMenu] = useState<MenuDataItem[]>([]);
  const [userData, setUserData] = useState<UserInfo>();

  const getUserInfo = () => {
    dispatch({
      type: "user/getUserInfo",
      callback: (user: UserInfo) => {
        setUserData(user);
      },
    });
  };

  const queryUserMenuV2 = async () => {
    setLoading(true);
    dispatch({
      type: "user/queryMenu",
      callback: (content: UserRoleMenuDTO) => {
        const convertedMenuData = convertMenuTreeToProLayoutMenu(
          content.menuTree
        );
        setUserMenu(convertedMenuData);
        setLoading(false);
        setMenuLoaded(true); // 设置菜单已加载
      },
    });
  };

  const convertMenuTreeToProLayoutMenu = (
    menuTree: MenuTreeDTO[]
  ): MenuDataItem[] => {
    return menuTree.map((item) => {
      return {
        name: item.name,
        icon: getIcon(item.icon),
        path: item.path, // 确保有 path 字段
        children: item.children
          ? convertMenuTreeToProLayoutMenu(item.children)
          : undefined,
      };
    });
  };

  const getIcon = (icon: string) => {
    if (!icon) {
      return undefined;
    }
    if (icon.startsWith("http")) {
      return <img src={icon} alt={icon} />;
    }
    let fixIconName = icon.slice(0, 1).toLocaleUpperCase() + icon.slice(1);
    // @ts-ignore
    const IconComponent = allIcons[fixIconName];
    return IconComponent ? <IconComponent /> : undefined;
  };

  useEffect(() => {
    getUserInfo();
    queryUserMenuV2();
  }, []);

  return loading ? (
    <Loading />
  ) : (
    <div
      id="main-pro-layout"
      style={{
        height: "100vh",
        overflow: "auto",
      }}
    >
      <ProLayout
        {...settings}
        title="Cake"
        menu={{
          type: "group",
          collapsedShowGroupTitle: true,
          request: async () => {
            if (menuLoaded) {
              return userMenu;
            }
            return [];
          },
          // request: queryUserMenu,
        }}
        waterMarkProps={{
          content: [
            userData?.userName || "", // 如果 userName 为 undefined，则使用空字符串
            userData?.userId || "", // 如果 userId 为 undefined，则使用空字符串
          ],
        }}
        breadcrumbRender={false}
        appList={defaultProps.appList}
        avatarProps={{
          src: "https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg",
          size: "small",
          title: userData?.userName,
          render: (props, dom) => (
            <Dropdown
              menu={{
                items: [
                  {
                    key: "logout",
                    icon: <LogoutOutlined />,
                    label: "退出登录",
                    onClick: async () => {
                      try {
                        const res = await logout();
                        if (!res.success) {
                          message.error("退出登录失败，请重试");
                        }
                      } catch (error) {
                        console.error("退出登录时发生错误:", error);
                        message.error("退出登录时发生错误，请重试");
                      }
                    },
                  },
                ],
              }}
            >
              {dom}
            </Dropdown>
          ),
        }}
        menuFooterRender={(props) =>
          props?.collapsed ? undefined : (
            <div style={{ textAlign: "center", paddingBlockStart: 12 }}>
              <div>© 2024 Made with 钟望</div>
              <div>by Cake</div>
            </div>
          )
        }
        menuItemRender={(menuItemProps, defaultDom) => {
          if (menuItemProps.isUrl || menuItemProps.children) {
            return defaultDom;
          }
          if (menuItemProps.path && location.pathname !== menuItemProps.path) {
            return (
              <Link
                to={menuItemProps.path}
                target={menuItemProps.target}
                onClick={() => setPathname(menuItemProps.path || "/apps")}
              >
                <div className="ant-pro-base-menu-horizontal-item-title">
                  {menuItemProps.icon} &nbsp;&nbsp;
                  {menuItemProps.name}
                </div>
              </Link>
            );
          }
          return (
            <div className="ant-pro-base-menu-horizontal-item-title">
              {menuItemProps.icon} &nbsp;&nbsp;
              {menuItemProps.name}
            </div>
          );
        }}
      >
        {menuLoaded ? (
          <>
            <Outlet />
          </>
        ) : (
          <Spin size="large" />
        )}
      </ProLayout>
    </div>
  );
};

export default connect()(Layout);
