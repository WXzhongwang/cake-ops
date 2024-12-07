import * as userService from "@/services/user";
import { message } from "antd";
import { API, BaseAction } from "typings";
import { Effect, Reducer } from "umi";

type UserModelState = {
  isLogin: boolean;
  userData: API.UserInfo;
  menu: UserRoleMenuDTO | null;
};

interface QueryCurrentUserAction {
  type: "user/getUserInfo";
}

interface QueryMenuAction extends BaseAction {
  type: "user/queryMenu";
}

export interface QueryAccountPayload {
  name: string;
  pageNo: number;
  pageSize: number;
}

export interface QueryAppAccountPayload {
  appId: string;
  name: string;
  pageNo: number;
  pageSize: number;
}

export interface UserRoleMenuDTO {
  roles: RoleDTO[];
  menuTree: MenuTreeDTO[];
}
export interface RoleDTO {
  roleId: string;
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
  isDeleted: string;
  status: string;
}
export interface MenuTreeDTO {
  menuId: string;
  name: string;
  path: string;
  parentId: string;
  level: number;
  icon: string;
  hidden: boolean;
  isDeleted: string;
  sort: number;
  children?: MenuTreeDTO[];
}

export interface AppAccountDTO {
  id: string;
  accountName: string;
  phone: string;
  email: string;
  tenantId: string;
  isAdmin: boolean;
  accountType: string;
  status: string;
  isDeleted: string;
  lastLoginIp: string;
  lastLoginTime: Date;
  feature: string;
  gmtCreate: Date;
  gmtModified: Date;
  headImage: string;
  dingding: string;
  qq: string;
  wechat: string;
  birthday: Date;
  tags: string;
}

type UserModelType = {
  namespace: "user";
  state: UserModelState;
  effects: {
    logout: Effect;
    getUserInfo: Effect;
    queryMenu: Effect;
  };
  reducers: {
    setUserInfo: Reducer<UserModelState>;
    setMenu: Reducer<UserModelState>;
  };
};

const UserModel: UserModelType = {
  namespace: "user",
  state: {
    isLogin: false,
    userData: {
      userId: "",
      userName: "",
      realName: "",
    },
    menu: null,
  },
  effects: {
    *logout(_, { call, put }) {
      const res: API.LogoutResponse = yield call(userService.logout);
      console.log("res", res);
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: false,
          userData: {},
        },
      });
    },
    *getUserInfo(_: QueryCurrentUserAction, { call, put }) {
      const res: API.UserInfoResponse = yield call(userService.getUserInfo);
      console.log("res", res);
      console.log("content", res.content);
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: true,
          userData: res.content,
        },
      });
    },
    *queryMenu({ callback }: QueryMenuAction, { call, put }) {
      const response = yield call(userService.queryUserMenu);
      yield put({
        type: "setMenu",
        payload: response.content,
      });

      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
  },
  reducers: {
    setUserInfo(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },
    setMenu(state, action) {
      return {
        ...state,
        menu: action.payload,
      };
    },
  },
};

export default UserModel;