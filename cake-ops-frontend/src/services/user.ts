// src/services/user.ts
import { QueryAccountPayload, QueryAppAccountPayload } from "@/models/user";
import request from "@/services/request";

export async function logout() {
  return request("/sso/logout", { method: "GET" });
}

export async function queryMembers(data: QueryAccountPayload) {
  return request("/api/ops/user/query-members", {
    method: "POST",
    data,
  });
}

export async function getUserInfo() {
  return request("/api/ops/user/current", {
    method: "GET",
  });
}

export async function queryUserMenu() {
  return request("/api/ops/user/menu", {
    method: "POST",
  });
}
