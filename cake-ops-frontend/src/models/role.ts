// src/models/menu.ts
import * as api from "@/services/role";
import { API, BaseAction } from "typings";
import { message } from "antd";
import { MenuTreeDTO } from "./menu";

export interface RoleDTO {
  roleId: string;
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
  status: string;
  isDeleted: boolean;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface RoleTreeDTO {
  roleId: string;
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
  status: string;
  isDeleted: boolean;
  gmtCreate: Date;
  gmtModified: Date;
  children: RoleTreeDTO[];
}

export interface RoleBasicQuery {
  id: string;
}

export interface RoleTreeQuery {
  appCode: string;
}

export interface CreateRoleCommand {
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
}

export interface ModifyRoleCommand {
  roleId: string;
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
}

export interface EnableRoleCommand {
  roleId: string;
}

export interface DisableRoleCommand {
  roleId: string;
}

export interface DeleteRoleCommand {
  roleId: string;
}

export interface RoleMenuPermissionTreeQuery {
  appCode: string;
  roleId: string;
}

interface FetchRoleMenuPermissionTreeAction extends BaseAction {
  type: "menu/fetchRoleMenuPermissionTree";
  payload: RoleMenuPermissionTreeQuery;
}

interface FetchRoleTreeAction extends BaseAction {
  type: "role/fetchRoleTree";
  payload: RoleTreeQuery;
}

interface GetRoleAction extends BaseAction {
  type: "role/getRole";
  payload: RoleBasicQuery;
}

interface CreateRoleAction extends BaseAction {
  type: "role/createRole";
  payload: CreateRoleCommand;
}

interface UpdateRoleAction extends BaseAction {
  type: "role/updateRole";
  payload: ModifyRoleCommand;
}

interface EnableRoleAction extends BaseAction {
  type: "role/enableRole";
  payload: EnableRoleCommand;
}

interface DisableRoleAction extends BaseAction {
  type: "role/disableRole";
  payload: DisableRoleCommand;
}

interface DeleteRoleAction extends BaseAction {
  type: "role/deleteRole";
  payload: DeleteRoleCommand;
}

const RoleModel = {
  namespace: "role",
  state: {},
  effects: {
    *fetchRoleTree({ payload, callback }: FetchRoleTreeAction, { call, put }) {
      const response: API.ResponseBody<RoleTreeDTO[]> = yield call(
        api.fetchRoleTree,
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
    *fetchRoleMenuPermissionTree(
      { payload, callback }: FetchRoleMenuPermissionTreeAction,
      { call, put }
    ) {
      const response: API.ResponseBody<MenuTreeDTO[]> = yield call(
        api.fetchRoleMenuPermissionTree,
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
    *getRole({ payload, callback }: GetRoleAction, { call, put }) {
      const response: API.ResponseBody<RoleDTO> = yield call(
        api.getRole,
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
    *createRole({ payload, callback }: CreateRoleAction, { call, put }) {
      const response = yield call(api.createRole, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *updateRole({ payload, callback }: UpdateRoleAction, { call, put }) {
      const response = yield call(api.updateRole, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteRole({ payload, callback }: DeleteRoleAction, { call, put }) {
      const response = yield call(api.deleteRole, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *enableRole({ payload, callback }: EnableRoleAction, { call, put }) {
      const response = yield call(api.enableRole, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *disableRole({ payload, callback }: DisableRoleAction, { call, put }) {
      const response = yield call(api.disableRole, payload);
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

export default RoleModel;
