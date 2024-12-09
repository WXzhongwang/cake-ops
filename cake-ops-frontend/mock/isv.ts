import mockjs from 'mockjs';

// 模拟IsvDTO对象
const isvTemplate = {
    id: '@id',
    name: '@cname()有限公司',
    shortName: '@cname()',
    isvName: '@cname()有限公司',
    email: '@email',
    url: '@url',
    phone: /^1[3-9]\d{9}$/,
    country: '@county(true)',
    registerIp: '@ip',
    address: '@county(true)',
    gmtCreate: '@datetime',
    gmtModified: '@datetime',
    status: '@pick(["0", "1"])',
    tenantsCount: '@integer(0, 100)',
    maxTenants: '@integer(100, 500)',
};

export default {
    // 模拟查询ISV列表的响应
    "POST /api/ops/isv/page": (req: any, res: any) => {
        const list = mockjs.mock({
            [`items|${10}`]: [isvTemplate], // 假设每次返回10条记录
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

    "POST /api/ops/isv/list": (req: any, res: any) => {
        const list = mockjs.mock({
            [`items|${10}`]: [isvTemplate], // 假设每次返回10条记录
        }).items;
        res.status(200).json({
            success: true,
            msg: '查询成功',
            content: list,
        });
    },


    // 模拟创建ISV的响应
    "POST /api/ops/isv/create": (req: any, res: any) => {
        const newIsv = mockjs.mock(isvTemplate);
        res.status(200).json({
            success: true,
            msg: '创建成功',
            content: newIsv,
        });
    },

    // 模拟更新ISV的响应
    "POST /api/ops/isv/update": (req: any, res: any) => {
        const updatedIsv = mockjs.mock(isvTemplate);
        res.status(200).json({
            success: true,
            msg: '更新成功',
            content: updatedIsv,
        });
    },

    // 模拟删除ISV的响应
    "POST /api/ops/isv/delete": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '删除成功',
        });
    },

    // 模拟启用ISV的响应
    "POST /api/ops/isv/enable": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '启用成功',
        });
    },

    // 模拟禁用ISV的响应
    "POST /api/ops/isv/disable": (req: any, res: any) => {
        res.status(200).json({
            success: true,
            msg: '禁用成功',
        });
    },
};
