// src/services/menu.ts
import {
    CreateMenuCommand,
    DeleteMenuCommand,
    DisableMenuCommand,
    EnableMenuCommand,
    MenuBasicQuery,
    MenuTreeQuery,
    ModifyMenuCommand,
} from "@/models/menu";
import request from "@/services/request";

export async function fetchMenuTree(data: MenuTreeQuery) {
    return request("/api/ops/menu/get-menu-tree", {
        method: "POST",
        data,
    });
}

export async function getMenu(data: MenuBasicQuery) {
    return request("/api/ops/menu/get", {
        method: "POST",
        data,
    });
}

export async function createMenu(data: CreateMenuCommand) {
    return request("/api/ops/menu/create", {
        method: "POST",
        data,
    });
}

export async function updateMenu(data: ModifyMenuCommand) {
    return request("/api/ops/menu/update", {
        method: "POST",
        data,
    });
}

export async function enableMenu(data: EnableMenuCommand) {
    return request("/api/ops/menu/enable", {
        method: "POST",
        data,
    });
}

export async function disableMenu(data: DisableMenuCommand) {
    return request("/api/ops/menu/disable", {
        method: "POST",
        data,
    });
}

export async function deleteMenu(data: DeleteMenuCommand) {
    console.log("payload", data);
    return request("/api/ops/menu/delete", {
        method: "POST",
        data,
    });
}
