import {
    CreateTenantPayload,
    DeleteTenantPayload,
    DisableTenantPayload,
    EnableTenantPayload,
    QueryTenantPayload,
    UpdateTenantPayload,
} from "@/models/tenant";
import request from "@/services/request";

export async function fetchTenantList(data: QueryTenantPayload) {
    return request("/api/ops/tenant/page", {
        method: "POST",
        data,
    });
}

export async function create(data: CreateTenantPayload) {
    return request("/api/ops/tenant/create", {
        method: "POST",
        data,
    });
}

export async function update(data: UpdateTenantPayload) {
    return request("/api/ops/tenant/update", {
        method: "POST",
        data,
    });
}

export async function enable(data: EnableTenantPayload) {
    return request("/api/ops/tenant/enable", {
        method: "POST",
        data,
    });
}

export async function disable(data: DisableTenantPayload) {
    return request("/api/ops/tenant/disable", {
        method: "POST",
        data,
    });
}


export async function deleteTenant(data: DeleteTenantPayload) {
    console.log("payload", data);
    return request("/api/ops/tenant/delete", {
        method: "POST",
        data,
    });
}
