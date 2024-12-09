import {
    CreateAppPayload,
    DeleteAppPayload,
    DisableAppPayload,
    EnableAppPayload,
    ListAppPayload,
    QueryAppPayload,
    UpdateAppPayload,
  } from "@/models/app";
  import request from "@/services/request";
  
  export async function fetchAppList(data: QueryAppPayload) {
    return request("/api/ops/app/page", {
      method: "POST",
       data,
    });
  }
  
  export async function listApp(data: ListAppPayload) {
    return request("/api/ops/app/list", {
      method: "POST",
      data,
    });
  }
  
  export async function create(data: CreateAppPayload) {
    return request("/api/ops/app/create", {
      method: "POST",
      data,
    });
  }
  
  export async function update(data: UpdateAppPayload) {
    return request("/api/ops/app/update", {
      method: "POST",
      data,
    });
  }
  
  export async function enable(data: EnableAppPayload) {
    return request("/api/ops/app/enable", {
      method: "POST",
      data,
    });
  }
  
  export async function disable(data: DisableAppPayload) {
    return request("/api/ops/app/disable", {
      method: "POST",
      data,
    });
  }
  
  export async function deleteApp(data: DeleteAppPayload) {
    console.log("payload", data);
    return request("/api/ops/app/delete", {
      method: "POST",
      data,
    });
  }
  