// src/models/menu.ts
import * as api from "@/services/role";
import { API, BaseAction } from "typings";
import { message } from "antd";
import { MenuDTO, MenuTreeDTO } from "./menu";
import { PermissionDTO } from "./permission";

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

export interface UpdateRoleMenuCommand {
  appCode: string;
  roleId: string;
  menuIds: string[];
}

export interface UpdateRolePermissionCommand {
  appCode: string;
  roleId: string;
  permissionIds: string[];
}

export interface RoleMenuTreeQuery {
  appCode: string;
  roleId: string;
}

export interface RolePermissionListQuery {
  appCode: string;
  roleId: string;
}

export interface RoleMenuListQuery {
  appCode: string;
  roleId: string;
}

interface FetchRoleMenuTreeAction extends BaseAction {
  type: "role/fetchRoleMenuTree";
  payload: RoleMenuTreeQuery;
}

interface FetchRoleMenuAction extends BaseAction {
  type: "role/listRoleMenu";
  payload: RoleMenuListQuery;
}

interface FetchRolePermissionAction extends BaseAction {
  type: "role/listRolePermission";
  payload: RolePermissionListQuery;
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

interface UpdateRoleMenuAction extends BaseAction {
  type: "role/grantRoleMenu";
  payload: UpdateRoleMenuCommand;
}

interface UpdateRolePermissionAction extends BaseAction {
  type: "role/grantRolePermission";
  payload: UpdateRoleMenuCommand;
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
    *fetchRoleMenuTree(
      { payload, callback }: FetchRoleMenuAction,
      { call, put }
    ) {
      const response: API.ResponseBody<MenuTreeDTO[]> = yield call(
        api.fetchRoleMenuTree,
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

    *listRoleMenu(
      { payload, callback }: FetchRoleMenuTreeAction,
      { call, put }
    ) {
      const response: API.ResponseBody<MenuDTO[]> = yield call(
        api.listRoleMenu,
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

    *listRolePermission(
      { payload, callback }: FetchRolePermissionAction,
      { call, put }
    ) {
      const response: API.ResponseBody<PermissionDTO[]> = yield call(
        api.listRolePermission,
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

    *grantRoleMenu({ payload, callback }: UpdateRoleMenuAction, { call, put }) {
      const response = yield call(api.grantRoleMenu, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *grantRolePermission(
      { payload, callback }: UpdateRolePermissionAction,
      { call, put }
    ) {
      const response = yield call(api.grantRolePermission, payload);
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
