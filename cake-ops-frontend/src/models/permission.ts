// src/models/menu.ts
import * as api from "@/services/permission";
import { API, BaseAction } from "typings";
import { message } from "antd";

export interface PermissionDTO {
  permissionId: string;
  appCode: string;
  tenantId: string;
  resourceType: string;
  resourceName: string;
  resourcePath: string;
  refMenuId: string;
  isDeleted: boolean;
  status: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface PermissionBasicQuery {
  permissionId: string;
}

export interface MenuPermissionQuery {
  permissionId: string;
}

export interface CreatePermissionCommand {
  appCode: string;
  tenantId: string;
  resourceType: string;
  resourceName: string;
  resourcePath: string;
  refMenuId: string;
}

export interface ModifyPermissionCommand {
  permissionId: string;
  resourceType: string;
  resourceName: string;
  resourcePath: string;
}

export interface EnablePermissionCommand {
  permissionId: string;
}

export interface DisablePermissionCommand {
  permissionId: string;
}

export interface DeletePermissionCommand {
  permissionId: string;
}

interface FetchPermissionAction extends BaseAction {
  type: "permission/listMenuPermission";
  payload: MenuPermissionQuery;
}

interface GetPermissionAction extends BaseAction {
  type: "permission/getPermission";
  payload: PermissionBasicQuery;
}

interface CreatePermissionAction extends BaseAction {
  type: "permission/createPermission";
  payload: CreatePermissionCommand;
}

interface UpdatePermissionAction extends BaseAction {
  type: "permission/updatePermission";
  payload: ModifyPermissionCommand;
}

interface EnablePermissionAction extends BaseAction {
  type: "permission/enableMPermission";
  payload: EnablePermissionCommand;
}

interface DisablePermissionAction extends BaseAction {
  type: "permission/disablePermission";
  payload: DisablePermissionCommand;
}

interface DeletePermissionAction extends BaseAction {
  type: "permission/deletePermission";
  payload: DeletePermissionCommand;
}

const PermissionModel = {
  namespace: "permission",
  state: {},
  effects: {
    *listMenuPermission(
      { payload, callback }: FetchPermissionAction,
      { call, put }
    ) {
      const response: API.ResponseBody<PermissionDTO[]> = yield call(
        api.listMenuPermission,
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
    *getPermission({ payload, callback }: GetPermissionAction, { call, put }) {
      const response: API.ResponseBody<PermissionDTO> = yield call(
        api.getPermission,
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
    *createPermission(
      { payload, callback }: CreatePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.createPermission, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *updatePermission(
      { payload, callback }: UpdatePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.updatePermission, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deletePermission(
      { payload, callback }: DeletePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.deletePermission, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *enablePermission(
      { payload, callback }: EnablePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.enablePermission, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *disablePermission(
      { payload, callback }: DisablePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.disablePermission, payload);
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

export default PermissionModel;
