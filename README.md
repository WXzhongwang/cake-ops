# CAKE ACL

# 介绍

**cake-acl** 是一个基于 SpringBoot + ACL + RBAC + ABAC 的服务端应用, 角色统一管控。

1. springboot 2.2.6.RELEASE
2. RBAC+ABAC

**特性:**

- [x] ** springboot **  : 接入快速，支持tomcat+webflux快速接入
- [x] ** 自定义错误处理 ** : 支持自定义错误处理
- [x] ** RBAC 模型 ** :  支持应用级别+租户级别 RBAC模型
- [ ] ** ACL 模型 ** :   计划支持应用级别+租户级别 ACL 模型
- [ ] ** ABAC 模型 ** :  计划支持应用级别+租户级别 ABAC 模型

# 快速使用

## GAV

```xml

<parent>
    <artifactId>cake-acl-api</artifactId>
    <groupId>com.rany.acl</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>

```