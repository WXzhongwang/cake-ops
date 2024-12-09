import {
    CreateIsvPayload,
    DeleteIsvPayload,
    DisableIsvPayload,
    EnableIsvPayload,
    ListIsvPayload,
    QueryIsvPayload,
    UpdateIsvPayload,
} from "@/models/isv";
import request from "@/services/request";

export async function fetchIsvList(data: QueryIsvPayload) {
    return request("/api/ops/isv/page", {
        method: "POST",
        data,
    });
}

export async function listIsv(data: ListIsvPayload) {
    return request("/api/ops/isv/list", {
        method: "POST",
        data,
    });
}

export async function create(data: CreateIsvPayload) {
    return request("/api/ops/isv/create", {
        method: "POST",
        data,
    });
}

export async function update(data: UpdateIsvPayload) {
    return request("/api/ops/isv/update", {
        method: "POST",
        data,
    });
}

export async function enable(data: EnableIsvPayload) {
    return request("/api/ops/isv/enable", {
        method: "POST",
        data,
    });
}

export async function disable(data: DisableIsvPayload) {
    return request("/api/ops/isv/disable", {
        method: "POST",
        data,
    });
}


export async function deleteIsv(data: DeleteIsvPayload) {
    console.log("payload", data);
    return request("/api/ops/isv/delete", {
        method: "POST",
        data,
    });
}
