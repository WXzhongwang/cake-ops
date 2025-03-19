// src/services/menu.ts
import {
  CreateRoleCommand,
  DeleteRoleCommand,
  DisableRoleCommand,
  EnableRoleCommand,
  RoleBasicQuery,
  RoleTreeQuery,
  ModifyRoleCommand,
  RoleMenuPermissionTreeQuery,
} from "@/models/role";
import request from "@/services/request";

export async function fetchRoleTree(data: RoleTreeQuery) {
  return request("/api/ops/role/get-role-tree", {
    method: "POST",
    data,
  });
}
export async function fetchRoleMenuPermissionTree(
  data: RoleMenuPermissionTreeQuery
) {
  return request("/api/ops/role/get-role-menu-permission", {
    method: "POST",
    data,
  });
}

export async function getRole(data: RoleBasicQuery) {
  return request("/api/ops/role/get", {
    method: "POST",
    data,
  });
}

export async function createRole(data: CreateRoleCommand) {
  return request("/api/ops/role/create", {
    method: "POST",
    data,
  });
}

export async function updateRole(data: ModifyRoleCommand) {
  return request("/api/ops/role/update", {
    method: "POST",
    data,
  });
}

export async function enableRole(data: EnableRoleCommand) {
  return request("/api/ops/role/enable", {
    method: "POST",
    data,
  });
}

export async function disableRole(data: DisableRoleCommand) {
  return request("/api/ops/role/disable", {
    method: "POST",
    data,
  });
}

export async function deleteRole(data: DeleteRoleCommand) {
  return request("/api/ops/role/delete", {
    method: "POST",
    data,
  });
}
