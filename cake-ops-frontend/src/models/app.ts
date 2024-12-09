import * as api from "@/services/app";
import {API, BaseAction} from "typings";
import {message} from "antd";

export interface AppDTO {
    id: string;
    appName: string;
    authType: string;
    appCode: string;
    status: string;
    gmtCreate: Date;
    gmtModified: Date;
}

export interface QueryAppPayload {
    appName: string;
    pageNo: number;
    pageSize: number;
}

export interface ListAppPayload {
    appName: string;
}

export interface CreateAppPayload {
    appName: string;
    authType: string;
    appCode: string;
}

export interface DeleteAppPayload {
    id: string;
}

export interface EnableAppPayload {
    id: string;
}

export interface DisableAppPayload {
    id: string;
}

export interface UpdateAppPayload extends CreateAppPayload {
    id: string;
}

interface QueryAppAction extends BaseAction {
    type: "app/fetchAppList";
    payload: QueryAppPayload;
}

interface ListAppAction extends BaseAction {
    type: "app/listApp";
    payload: ListAppPayload;
}

interface CreateAppAction extends BaseAction {
    type: "app/createApp";
    payload: CreateAppPayload;
}

interface UpdateAppAction extends BaseAction {
    type: "app/updateApp";
    payload: UpdateAppPayload;
}

interface EnableAction extends BaseAction {
    type: "app/enable";
    payload: EnableAppPayload;
}

interface DisableAction extends BaseAction {
    type: "app/disable";
    payload: DisableAppPayload;
}

interface DeleteAppAction extends BaseAction {
    type: "app/delete";
    payload: DeleteAppPayload;
}

const AppModel = {
    namespace: "app",
    state: {},
    effects: {
        * fetchAppList({payload, callback}: QueryAppAction, {call, put}) {
            const response: API.ResponseBody<API.Page<AppDTO>> = yield call(
                api.fetchAppList,
                payload
            );
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * listApp({payload, callback}: ListAppAction, {call, put}) {
            const response: API.ResponseBody<API.ResponseBody<AppDTO[]>> = yield call(
                api.listApp,
                payload
            );
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * create({payload, callback}: CreateAppAction, {call, put}) {
            const response = yield call(api.create, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * update({payload, callback}: UpdateAppAction, {call, put}) {
            const response = yield call(api.update, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * deleteApp({payload, callback}: DeleteAppAction, {call, put}) {
            const response = yield call(api.deleteApp, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * enable({payload, callback}: EnableAction, {call, put}) {
            const response = yield call(api.enable, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        * disable({payload, callback}: DisableAction, {call, put}) {
            const response = yield call(api.disable, payload);
            const {success, msg} = response;
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

export default AppModel;
