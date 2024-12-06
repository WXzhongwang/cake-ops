import * as api from "@/services/isv";
import {API, BaseAction} from "typings";
import {message} from "antd";

export interface IsvDTO {
    id: number;
    name: string;
    shortName: string;
    email: string;
    url: string;
    phone: string;
    country: string;
    registerIp: string;
    gmtCreate: Date;
    gmtModified: Date;
    status: string;
}

export interface QueryIsvPayload {
    name: string;
    pageNo: number;
    pageSize: number;
}

export interface CreateIsvPayload {
    name: string;
    shortName: string;
    email: string;
    url: string;
    phone: string;
    country: string;
}

export interface DeleteIsvPayload {
    id: number;
}

export interface EnableIsvPayload {
    id: number;
}

export interface DisableIsvPayload {
    id: number;
}


export interface UpdateIsvPayload extends CreateIsvPayload {
    id: number;
}


interface QueryIsvAction extends BaseAction {
    type: "isv/pageIsv";
    payload: QueryIsvPayload;
}

interface CreateIsvAction extends BaseAction {
    type: "isv/createIsv";
    payload: CreateIsvPayload;
}

interface UpdateIsvAction extends BaseAction {
    type: "isv/updateIsv";
    payload: CreateIsvPayload;
}


interface EnableAction extends BaseAction {
    type: "isv/enable";
    payload: EnableIsvPayload;
}

interface DisableAction extends BaseAction {
    type: "isv/disable";
    payload: DisableIsvPayload;
}

interface DeleteIsvAction extends BaseAction {
    type: "isv/delete";
    payload: DeleteIsvPayload;
}


const IsvModel = {
    namespace: "isv",
    state: {},
    effects: {
        *page({payload, callback}: QueryIsvAction, {call, put}) {
            const response: API.ResponseBody<API.Page<IsvDTO>> = yield call(api.fetchIsvList, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        *create({payload, callback}: CreateIsvAction, {call, put}) {
            const response = yield call(api.create, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        *update({payload, callback}: UpdateIsvAction, {call, put}) {
            const response = yield call(api.update, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        *deleteIsv({payload, callback}: DeleteIsvAction, {call, put}) {
            const response = yield call(api.deleteIsv, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        *enbale({payload, callback}: EnableAction, {call, put}) {
            const response = yield call(api.enable, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        },
        *disable({payload, callback}: DisableAction, {call, put}) {
            const response = yield call(api.disable, payload);
            const {success, msg} = response;
            // 调用回调函数
            if (success && callback && typeof callback === "function") {
                callback(response.content);
            } else {
                message.error(msg);
            }
        }
    },

    reducers: {},
};

export default IsvModel;
