import {API} from "typings";

export default {
    "GET /api/ops/user/current": (req: Request, res: Response) => {
        const result: API.UserInfoResponse = {
            success: true,
            code: "200",
            msg: "请求成功",
            content: {
                userId: "123456",
                userName: "hz管理员",
                realName: "测试",
            },
        };
        // res.json(result);

        // 返回模拟数据
        res.status(200).json(result);
    },

    'POST /api/ops/user/menu': (req: Request, res: Response) => {
        // 模拟返回的数据
        const mockData = {
            roles: [
                {
                    roleId: '1',
                    roleName: 'Admin',
                    roleDesc: 'Administrator',
                    roleKey: 'admin',
                    parentId: '0',
                    isDeleted: '0',
                    status: 'active'
                },
                {
                    roleId: '2',
                    roleName: 'User',
                    roleDesc: 'Standard User',
                    roleKey: 'user',
                    parentId: '0',
                    isDeleted: '0',
                    status: 'active'
                },
            ],
            menuTree: [
                {
                    menuId: '1',
                    name: 'ISV管理',
                    path: '/uic',
                    parentId: '0',
                    level: 1,
                    hidden: false,
                    isDeleted: '0',
                    sort: 1,
                    children: [
                        {
                            menuId: '2',
                            name: 'ISV管理',
                            path: '/uic/isv',
                            parentId: '0',
                            level: 2,
                            hidden: false,
                            isDeleted: '0',
                            sort: 2
                        },
                        {
                            menuId: '3',
                            name: '租户管理',
                            path: '/uic/tenant',
                            parentId: '0',
                            level: 2,
                            hidden: false,
                            isDeleted: '0',
                            sort: 2
                        },
                        {
                            menuId: '4',
                            name: '账户管理',
                            path: '/uic/account',
                            parentId: '0',
                            level: 2,
                            hidden: false,
                            isDeleted: '0',
                            sort: 2
                        },
                    ]
                },
                {
                    menuId: '11',
                    name: '权限中心',
                    path: '/acl',
                    parentId: '0',
                    level: 1,
                    hidden: false,
                    isDeleted: '0',
                    sort: 1,
                    children: [
                        {
                            menuId: '12',
                            name: '应用管理',
                            path: '/acl/app',
                            parentId: '0',
                            level: 2,
                            hidden: false,
                            isDeleted: '0',
                            sort: 2
                        },
                        {
                            menuId: '13',
                            name: '应用菜单',
                            path: '/acl/menu-tree',
                            parentId: '0',
                            level: 1,
                            hidden: false,
                            isDeleted: '0',
                            sort: 2
                        }
                    ]
                }
            ],
        };

        // 返回模拟数据
        res.status(200).json({
            success: true,
            content: mockData,
        });
    },
};
