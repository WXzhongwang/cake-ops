import * as api from "@/services/tenant";
import { BaseAction } from "typings";
import { message } from "antd";

export interface TenantDTO {
  id: number;
  isvId: number;
  name: string;
  shortName: string;
  email: string;
  url: string;
  phone: string;
  source: string;
  address: string;
  status: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface QueryTenantPayload {
  name: string;
  isvId: number;
  pageNo: number;
  pageSize: number;
}

export interface CreateTenantPayload {
  isvId: number;
  name: string;
  shortName: string;
  email: string;
  url: string;
  phone: string;
  source: string;
  address: string;
}

export interface DeleteTenantPayload {
  id: number;
}

export interface EnableTenantPayload {
  id: number;
}

export interface DisableTenantPayload {
  id: number;
}

export interface UpdateTenantPayload extends CreateTenantPayload {
  id: number;
}

interface QueryTenantAction extends BaseAction {
  type: "isv/pageTenant";
  payload: QueryTenantPayload;
}

interface CreateTenantAction extends BaseAction {
  type: "isv/createTenant";
  payload: CreateTenantPayload;
}

interface UpdateTenantAction extends BaseAction {
  type: "isv/updateTenant";
  payload: CreateTenantPayload;
}

interface EnableAction extends BaseAction {
  type: "isv/enable";
  payload: EnableTenantPayload;
}

interface DisableAction extends BaseAction {
  type: "isv/disable";
  payload: DisableTenantPayload;
}

interface DeleteTenantAction extends BaseAction {
  type: "isv/delete";
  payload: DeleteTenantPayload;
}

const TenantModel = {
  namespace: "tenant",
  state: {},
  effects: {
    *page({ payload, callback }: QueryTenantAction, { call, put }) {
      const response = yield call(api.fetchTenantList, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *create({ payload, callback }: CreateTenantAction, { call, put }) {
      const response = yield call(api.create, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *update({ payload, callback }: UpdateTenantAction, { call, put }) {
      const response = yield call(api.update, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteTenant({ payload, callback }: DeleteTenantAction, { call, put }) {
      const response = yield call(api.deleteTenant, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *enbale({ payload, callback }: EnableAction, { call, put }) {
      const response = yield call(api.enable, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *disable({ payload, callback }: DisableAction, { call, put }) {
      const response = yield call(api.disable, payload);
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

export default TenantModel;
