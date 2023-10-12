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

# 模型

## RBAC

> 相关设计的实际表都存在租户ID字段，所以理论上支持单一应用下多租户场景。

1. 用户表（实际不存在）：用户我这边暂时不需要直接存储用户信息，因此暂不需要直接存储user表
2. 应用表：支持多个应用
3. 角色表：支持多级角色
4. 用户角色关联表：支持用户在单个应用内绑定多个角色
5. 菜单表：这里和传统的RBAC有一些不一样，我们菜单是单独出来一张表，菜单支持多级
6. 权限点表：权限点绑定关联任意一级菜单，指代菜单可以绑定的若干权限，这些权限点可以为加载，行为等。
7. 角色菜单关联表：单个角色支持选定其应用下任意菜单范围
8. 角色权限表关联表：角色存在菜单权限之后，仍然需要关联菜单下的权限点，进而确定角色具备特定的权限

参考：
![RBAC模型](https://github.com/WXzhongwang/cake-acl-center/blob/main/RBAC.png)

# 快速使用

## GAV

```xml

<parent>
    <artifactId>cake-acl-api</artifactId>
    <groupId>com.rany.acl</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>

```