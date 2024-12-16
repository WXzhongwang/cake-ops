// src/models/account.ts
import * as api from "@/services/account";
import { API, BaseAction } from "typings";
import { message } from "antd";
import { TenantDTO } from "./tenant";

export interface AccountDTO {
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
  tenant: TenantDTO;
}

export interface QueryAccountPayload {
  accountName: string;
  pageNo: number;
  pageSize: number;
}

export interface CreateAccountPayload {
  accountName: string;
  email: string;
  isAdmin: boolean;
  phone: string;
  accountType: string;
  dingding: string;
  headImage: string;
  qq: string;
  wechat: string;
  birthday: Date;
  tags: string;
}

export interface DeleteAccountPayload {
  id: string;
}

export interface EnableAccountPayload {
  id: string;
}

export interface DisableAccountPayload {
  id: string;
}

export interface UpdateAccountPayload extends CreateAccountPayload {
  id: string;
}

interface QueryAccountAction extends BaseAction {
  type: "account/pageAccount";
  payload: QueryAccountPayload;
}

interface CreateAccountAction extends BaseAction {
  type: "account/createAccount";
  payload: CreateAccountPayload;
}

interface UpdateAccountAction extends BaseAction {
  type: "account/updateAccount";
  payload: UpdateAccountPayload;
}

interface EnableAccountAction extends BaseAction {
  type: "account/enableAccount";
  payload: EnableAccountPayload;
}

interface DisableAccountAction extends BaseAction {
  type: "account/disableAccount";
  payload: DisableAccountPayload;
}

interface DeleteAccountAction extends BaseAction {
  type: "account/deleteAccount";
  payload: DeleteAccountPayload;
}

const AccountModel = {
  namespace: "account",
  state: {},
  effects: {
    *fetchAccountList(
      { payload, callback }: QueryAccountAction,
      { call, put }
    ) {
      const response: API.ResponseBody<API.Page<AccountDTO>> = yield call(
        api.fetchAccountList,
        payload
      );
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *createAccount({ payload, callback }: CreateAccountAction, { call, put }) {
      const response = yield call(api.createAccount, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *updateAccount({ payload, callback }: UpdateAccountAction, { call, put }) {
      const response = yield call(api.updateAccount, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteAccount({ payload, callback }: DeleteAccountAction, { call, put }) {
      const response = yield call(api.deleteAccount, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *enableAccount({ payload, callback }: EnableAccountAction, { call, put }) {
      const response = yield call(api.enableAccount, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *disableAccount(
      { payload, callback }: DisableAccountAction,
      { call, put }
    ) {
      const response = yield call(api.disableAccount, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {},
};

export default AccountModel;
