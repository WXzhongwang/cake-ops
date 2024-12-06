export default {
    // 布局路由配置
    route: {
        path: "/",
        redirect: "/isv",
        // routes: [
        //     {
        //         path: "/isv",
        //         icon: <SmileFilled/>,
        //         component: "isv/isv-list",
        //         name: "ISV管理",
        //     },
        //     {
        //         path: "/tenant",
        //         icon: <SmileFilled/>,
        //         component: "tenant/tenant-list",
        //         name: "租户管理",
        //         routes: [
        //             {
        //                 path: "/tenant/account-list",
        //                 name: "租户账号",
        //                 icon: <TabletFilled/>,
        //                 component: "tenant/account-list",
        //             }
        //         ]
        //     },
        //     {
        //         path: "/application",
        //         icon: <SmileFilled/>,
        //         component: "application/app-list",
        //         name: "应用管理",
        //         routes: [
        //             {
        //                 path: "/application/menu-tree",
        //                 name: "应用菜单",
        //                 icon: <TabletFilled/>,
        //                 component: "application/menu-tree",
        //             }
        //         ]
        //     }
        // ],
    },
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
