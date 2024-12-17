// src/services/menu.ts
import {
  CreatePermissionCommand,
  DeletePermissionCommand,
  DisablePermissionCommand,
  EnablePermissionCommand,
  MenuPermissionQuery,
  ModifyPermissionCommand,
  PermissionBasicQuery,
} from "@/models/permission";
import request from "@/services/request";

export async function listMenuPermission(data: MenuPermissionQuery) {
  return request("/api/ops/permission/list-menu-permissions", {
    method: "POST",
    data,
  });
}

export async function getPermission(data: PermissionBasicQuery) {
  return request("/api/ops/permission/get", {
    method: "POST",
    data,
  });
}

export async function createPermission(data: CreatePermissionCommand) {
  return request("/api/ops/permission/create", {
    method: "POST",
    data,
  });
}

export async function updatePermission(data: ModifyPermissionCommand) {
  return request("/api/ops/permission/update", {
    method: "POST",
    data,
  });
}

export async function enablePermission(data: EnablePermissionCommand) {
  return request("/api/ops/permission/enable", {
    method: "POST",
    data,
  });
}

export async function disablePermission(data: DisablePermissionCommand) {
  return request("/api/ops/permission/disable", {
    method: "POST",
    data,
  });
}

export async function deletePermission(data: DeletePermissionCommand) {
  console.log("payload", data);
  return request("/api/ops/permission/delete", {
    method: "POST",
    data,
  });
}
