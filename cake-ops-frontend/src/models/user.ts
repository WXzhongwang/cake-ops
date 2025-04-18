import * as userService from "@/services/user";
import { message } from "antd";
import { API, BaseAction } from "typings";
import { Effect, Reducer } from "umi";
import { PermissionDTO } from "./permission";
import { MenuTreeDTO } from "./menu";

type UserModelState = {
  isLogin: boolean;
  userData: UserInfo;
  menu: UserRoleMenuDTO;
};

interface QueryCurrentUserAction extends BaseAction {
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

/** 用户信息数据 */
export interface UserInfo {
  userId: string;
  userName: string;
  realName: string;
}

const UserModel: UserModelType = {
  namespace: "user",
  state: {
    isLogin: false,
    userData: {
      userId: "",
      userName: "",
      realName: "",
    },
    menu: {
      roles: [],
      menuTree: [],
    },
  },
  effects: {
    *logout(_, { call, put }) {
      const res: API.ResponseBody<Record<string, never>> = yield call(
        userService.logout
      );
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: false,
          userData: {},
        },
      });
    },
    *getUserInfo({ callback }: QueryCurrentUserAction, { call, put }) {
      const res: API.ResponseBody<UserInfo> = yield call(
        userService.getUserInfo
      );
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: true,
          userData: res.content,
        },
      });

      const { success, msg } = res;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(res.content);
        }
      } else {
        message.error(msg);
      }
    },
    *queryMenu({ callback }: QueryMenuAction, { call, put }) {
      const response: API.ResponseBody<UserRoleMenuDTO> = yield call(
        userService.queryUserMenu
      );
      yield put({
        type: "setMenu",
        payload: {
          menu: response.content,
        },
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
      console.log("action", action.payload);
      return {
        ...state,
        ...action.payload,
      };
    },

    setMenu(state, action) {
      console.log("action", action.payload);
      return {
        ...state,
        ...action.payload,
      };
    },
  },
};

export default UserModel;
