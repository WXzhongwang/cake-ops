import mockjs from "mockjs";

// 模拟AccountDTO对象
const accountTemplate = {
  id: "@id",
  accountName: "@cname()",
  phone: /^1[3-9]\d{9}$/,
  email: "@email",
  tenantId: "@id",
  isAdmin: "@boolean",
  accountType: '@pick(["ADMIN", "USER", "GUEST"])',
  status: '@pick(["0", "1"])',
  isDeleted: '@pick(["0", "1"])',
  lastLoginIp: "@ip",
  lastLoginTime: "@datetime",
  feature: "@sentence(5, 10)",
  gmtCreate: "@datetime",
  gmtModified: "@datetime",
  headImage: '@image("100x100")',
  dingding: "@word(8, 12)",
  qq: "@integer(100000000, 999999999)",
  wechat: "@word(8, 12)",
  birthday: "@date",
  tags: "@sentence(3, 5)",
  tenantName: "@cname()有限公司",
};

export default {
  // 模拟查询账号列表的响应
  "POST /api/ops/account/page": (req: any, res: any) => {
    const list = mockjs.mock({
      [`items|${10}`]: [accountTemplate], // 假设每次返回10条记录
    }).items;
    res.status(200).json({
      success: true,
      msg: "查询成功",
      content: {
        total: 200,
        items: list,
      },
    });
  },

  "POST /api/ops/account/list": (req: any, res: any) => {
    const list = mockjs.mock({
      [`items|${10}`]: [accountTemplate], // 假设每次返回10条记录
    }).items;
    res.status(200).json({
      success: true,
      msg: "查询成功",
      content: list,
    });
  },

  // 模拟创建账号的响应
  "POST /api/ops/account/create": (req: any, res: any) => {
    const newAccount = mockjs.mock(accountTemplate);
    res.status(200).json({
      success: true,
      msg: "创建成功",
      content: newAccount,
    });
  },

  // 模拟更新账号的响应
  "POST /api/ops/account/update": (req: any, res: any) => {
    const updatedAccount = mockjs.mock(accountTemplate);
    res.status(200).json({
      success: true,
      msg: "更新成功",
      content: updatedAccount,
    });
  },

  // 模拟删除账号的响应
  "POST /api/ops/account/delete": (req: any, res: any) => {
    res.status(200).json({
      success: true,
      msg: "删除成功",
    });
  },

  // 模拟启用账号的响应
  "POST /api/ops/account/enable": (req: any, res: any) => {
    res.status(200).json({
      success: true,
      msg: "启用成功",
    });
  },

  // 模拟禁用账号的响应
  "POST /api/ops/account/disable": (req: any, res: any) => {
    res.status(200).json({
      success: true,
      msg: "禁用成功",
    });
  },
};
