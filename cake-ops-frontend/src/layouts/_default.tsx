import { SmileFilled, TabletFilled } from "@ant-design/icons";

export default {
  // 布局路由配置
  //   route: {
  //     path: "/",
  //     redirect: "/uic",
  //     routes: [
  //       {
  //         path: "/uic",
  //         icon: <SmileFilled />,
  //         name: "账号中心",
  //         children: [
  //           {
  //             path: "/uic/isv",
  //             icon: <SmileFilled />,
  //             component: "isv/isv-list",
  //             name: "ISV管理",
  //           },
  //           {
  //             path: "/uic/tenant",
  //             icon: <SmileFilled />,
  //             component: "tenant/tenant-list",
  //             name: "租户管理",
  //           },
  //           {
  //             path: "/uic/account",
  //             name: "账号管理",
  //             icon: <TabletFilled />,
  //             component: "tenant/account-list",
  //           },
  //         ],
  //       },
  //       {
  //         path: "/acl",
  //         icon: <SmileFilled />,
  //         name: "权限中心",
  //         children: [
  //           {
  //             path: "/acl/app",
  //             icon: <SmileFilled />,
  //             component: "app/app-list",
  //             name: "应用管理",
  //           },
  //           {
  //             path: "/acl/menu-tree",
  //             name: "菜单管理",
  //             icon: <TabletFilled />,
  //             component: "app/menu-tree",
  //           },
  //         ],
  //       },
  //     ],
  //   },
  location: {
    pathname: "/",
  },
  appList: [
    {
      icon: "https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg",
      title: "基础平台",
      desc: "UIC&ACL",
      url: "http://test-ops.rany.com",
    },
    {
      icon: "https://gw.alipayobjects.com/zos/antfincdn/upvrAjAPQX/Logo_Tech%252520UI.svg",
      title: "搜索中台",
      desc: "搜索中台",
      url: "https://procomponents.ant.design/",
    },
  ],
};
