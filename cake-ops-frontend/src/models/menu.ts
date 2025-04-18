// src/models/menu.ts
import * as api from "@/services/menu";
import { API, BaseAction } from "typings";
import { message } from "antd";
import { PermissionDTO } from "./permission";

export interface MenuDTO {
  menuId: string;
  name: string;
  path: string;
  icon: string;
  level: number;
  menuType: string;
  parentId: string;
  hidden: boolean;
  isDeleted: boolean;
  sort: number;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface MenuTreeDTO {
  menuId: number;
  name: string;
  path: string;
  icon: string;
  level: number;
  parentId: number;
  hidden: boolean;
  isDeleted: boolean;
  sort: number;
  children: MenuTreeDTO[];
  permissions: PermissionDTO[];
}

export interface MenuBasicQuery {
  menuId: string;
}

export interface MenuTreeQuery {
  appCode: string;
}

export interface CreateMenuCommand {
  name: string;
  path: string;
  icon: string;
  level: number;
  parentId: number;
  menuType: string;
  hidden: boolean;
  isDeleted: boolean;
  sort: number;
}

export interface ModifyMenuCommand {
  menuId: string;
  name: string;
  path: string;
  icon: string;
  level: number;
  parentId: number;
  hidden: boolean;
  isDeleted: boolean;
  sort: number;
}

export interface EnableMenuCommand {
  menuId: string;
}

export interface DisableMenuCommand {
  menuId: string;
}

export interface DeleteMenuCommand {
  menuId: string;
}

interface FetchMenuTreeAction extends BaseAction {
  type: "menu/fetchMenuTree";
  payload: MenuTreeQuery;
}

interface GetMenuAction extends BaseAction {
  type: "menu/getMenu";
  payload: MenuBasicQuery;
}

interface CreateMenuAction extends BaseAction {
  type: "menu/createMenu";
  payload: CreateMenuCommand;
}

interface UpdateMenuAction extends BaseAction {
  type: "menu/updateMenu";
  payload: ModifyMenuCommand;
}

interface EnableMenuAction extends BaseAction {
  type: "menu/enableMenu";
  payload: EnableMenuCommand;
}

interface DisableMenuAction extends BaseAction {
  type: "menu/disableMenu";
  payload: DisableMenuCommand;
}

interface DeleteMenuAction extends BaseAction {
  type: "menu/deleteMenu";
  payload: DeleteMenuCommand;
}

const MenuModel = {
  namespace: "menu",
  state: {},
  effects: {
    *fetchMenuTree({ payload, callback }: FetchMenuTreeAction, { call, put }) {
      const response: API.ResponseBody<MenuTreeDTO[]> = yield call(
        api.fetchMenuTree,
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
    *getMenu({ payload, callback }: GetMenuAction, { call, put }) {
      const response: API.ResponseBody<MenuDTO> = yield call(
        api.getMenu,
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
    *createMenu({ payload, callback }: CreateMenuAction, { call, put }) {
      const response = yield call(api.createMenu, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *updateMenu({ payload, callback }: UpdateMenuAction, { call, put }) {
      const response = yield call(api.updateMenu, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteMenu({ payload, callback }: DeleteMenuAction, { call, put }) {
      const response = yield call(api.deleteMenu, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *enableMenu({ payload, callback }: EnableMenuAction, { call, put }) {
      const response = yield call(api.enableMenu, payload);
      const { success, msg } = response;
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *disableMenu({ payload, callback }: DisableMenuAction, { call, put }) {
      const response = yield call(api.disableMenu, payload);
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

export default MenuModel;
