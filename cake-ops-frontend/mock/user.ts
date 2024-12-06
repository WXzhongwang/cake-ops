import { defineMock } from "umi";
import Mock from "mockjs";
import { API } from "typings";

export default {
  "GET /api/devops/user/getUser": (req: Request, res: Response) => {
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
    res.json(result);
  },
};
