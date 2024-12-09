// src/services/account.ts
import {
    CreateAccountPayload,
    DeleteAccountPayload,
    DisableAccountPayload,
    EnableAccountPayload,
    QueryAccountPayload,
    UpdateAccountPayload,
} from "@/models/account";
import request from "@/services/request";

export async function fetchAccountList(data: QueryAccountPayload) {
    return request("/api/ops/account/page", {
        method: "POST",
        data,
    });
}

export async function createAccount(data: CreateAccountPayload) {
    return request("/api/ops/account/create", {
        method: "POST",
        data,
    });
}

export async function updateAccount(data: UpdateAccountPayload) {
    return request("/api/ops/account/update", {
        method: "POST",
        data,
    });
}

export async function enableAccount(data: EnableAccountPayload) {
    return request("/api/ops/account/enable", {
        method: "POST",
        data,
    });
}

export async function disableAccount(data: DisableAccountPayload) {
    return request("/api/ops/account/disable", {
        method: "POST",
        data,
    });
}

export async function deleteAccount(data: DeleteAccountPayload) {
    console.log("payload", data);
    return request("/api/ops/account/delete", {
        method: "POST",
        data,
    });
}
