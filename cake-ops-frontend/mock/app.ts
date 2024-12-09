import mockjs from 'mockjs';

// 模拟AppDTO对象
const appTemplate = {
    id: '@id',
    appName: '@cname()应用',
    authType: '@pick(["基础认证"])',
    appCode: '123456',
    gmtCreate: '@datetime',
    gmtModified: '@datetime',
    status: '@pick(["0", "1"])',
    isDeleted: '@pick(["0", "1"])',
};

export default {
    // 模拟查询App列表的响应
    "POST /api/ops/app/page": (req: any, res: any) => {
        const list = mockjs.mock({
            [`items|${10}`]: [appTemplate], // 假设每次返回10条记录
        }).items;
        res.status(200).json({
            success: true,
            msg: '查询成功',
            content: {
                total: 200,
                items: list,
            },
        });
    },

    "POST /api/ops/app/list": (req: any, res: any) => {
        const list = mockjs.mock({
            [`items|${10}`]: [appTemplate], // 假设每次返回10条记录
        }).items;
        res.status(200).json({
            success: true,
            msg: '查询成功',
            content: list,
        });
    },

    // 模拟创建App的响应
    "POST /api/ops/app/create": (req: any, res: any) => {
        const newApp = mockjs.mock(appTemplate);
        res.status(200).json({
            success: true,
            msg: '创建成功',
            content: newApp,
        });
    },

    // 模拟更新App的响应
    "POST /api/ops/app/update": (req: any, res: any) => {
        const updatedApp = mockjs.mock(appTemplate);
        res.status(200).json({
            success: true,
            msg: '更新成功',
            content: updatedApp,
        });
    },

    // 模拟删除App的响应
    "POST /api/ops/app/delete": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '删除成功',
        });
    },

    // 模拟启用App的响应
    "POST /api/ops/app/enable": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '启用成功',
        });
    },

    // 模拟禁用App的响应
    "POST /api/ops/app/disable": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '禁用成功',
        });
    },
};
