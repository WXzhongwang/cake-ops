# CAKE ops

# 介绍

**cake-ops** 是一个基于 SpringBoot + UIC + ACL + RBAC + ABAC 的服务端应用, 应用，账号，角色，菜单，授权 统一管控。

1. springboot 2.2.6.RELEASE
2. RBAC+ABAC
3. UIC

**特性:**

- [x] ** springboot **  : 接入快速，支持tomcat+webflux快速接入
- [x] ** 自定义错误处理 ** : 支持自定义错误处理
- [x] ** 多租户 ** : 多租户
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
    <artifactId>cake-ops-api</artifactId>
    <groupId>com.rany.ops</groupId>
    <version>1.0.0-SNAPSHOT</version>
</parent>

```

## 应用管理接口

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.application.*;
import com.rany.acl.api.query.application.ApplicationBasicQuery;
import com.rany.acl.api.query.application.ApplicationPageQuery;
import com.rany.acl.api.query.application.ApplicationQuery;
import com.rany.ops.common.dto.application.ApplicationDTO;

/**
 * 应用管理
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface ApplicationFacade {


    /**
     * 创建应用
     *
     * @param createApplicationCommand
     * @return
     */
    PojoResult<Long> createApplication(CreateApplicationCommand createApplicationCommand);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery
     * @return
     */
    PojoResult<ApplicationDTO> getApplication(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery
     * @return
     */
    PojoResult<ApplicationDTO> getApplicationByAppCode(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 应用禁用
     *
     * @param disableApplicationCommand
     * @return
     */
    PojoResult<Boolean> disableApplication(DisableApplicationCommand disableApplicationCommand);

    /**
     * 启用应用
     *
     * @param enableApplicationCommand
     * @return
     */
    PojoResult<Boolean> enableApplication(EnableApplicationCommand enableApplicationCommand);

    /**
     * 删除应用
     *
     * @param deleteApplicationCommand
     * @return
     */
    PojoResult<Boolean> deleteApplication(DeleteApplicationCommand deleteApplicationCommand);

    /**
     * 更新应用基本信息
     *
     * @param modifyApplicationCommand
     * @return
     */
    PojoResult<Boolean> modifyApplication(ModifyApplicationCommand modifyApplicationCommand);


    /**
     * 分页查询应用
     *
     * @param applicationQuery
     * @return
     */
    ListResult<ApplicationDTO> findApplications(ApplicationQuery applicationQuery);

    /**
     * 查询应用分页
     *
     * @param applicationPageQuery
     * @return
     */
    PageResult<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery);

}

```

## 菜单管理接口

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.menu.*;
import com.rany.acl.api.query.menu.MenuBasicQuery;
import com.rany.acl.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;

/**
 * 菜单管理
 *
 * @author zhongshengwang
 * @description 菜单管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface MenuFacade {


    /**
     * 创建菜单
     *
     * @param createMenuCommand
     * @return
     */
    PojoResult<Long> createMenu(CreateMenuCommand createMenuCommand);

    /**
     * 获取菜单信息
     *
     * @param menuBasicQuery
     * @return
     */
    PojoResult<MenuDTO> getMenu(MenuBasicQuery menuBasicQuery);

    /**
     * 获取菜单树信息
     *
     * @param menuTreeQuery
     * @return
     */
    ListResult<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery);

    /**
     * 菜单禁用
     *
     * @param disableMenuCommand
     * @return
     */
    PojoResult<Boolean> disableMenu(DisableMenuCommand disableMenuCommand);

    /**
     * 启用菜单
     *
     * @param enableMenuCommand
     * @return
     */
    PojoResult<Boolean> enableMenu(EnableMenuCommand enableMenuCommand);

    /**
     * 删除菜单
     *
     * @param deleteMenuCommand
     * @return
     */
    PojoResult<Boolean> deleteMenu(DeleteMenuCommand deleteMenuCommand);

    /**
     * 更新菜单基本信息
     *
     * @param modifyMenuCommand
     * @return
     */
    PojoResult<Boolean> modifyMenu(ModifyMenuCommand modifyMenuCommand);
}


```

## 角色管理接口

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.role.*;
import com.rany.acl.api.query.role.RoleBasicQuery;
import com.rany.acl.api.query.role.RoleTreeQuery;
import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.dto.role.RoleTreeDTO;


/**
 * 角色管理
 *
 * @author zhongshengwang
 * @description 角色管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface RoleFacade {

    /**
     * 创建角色
     *
     * @param createRoleCommand
     * @return
     */
    PojoResult<Long> createRole(CreateRoleCommand createRoleCommand);

    /**
     * 获取角色信息
     *
     * @param RoleBasicQuery
     * @return
     */
    PojoResult<RoleDTO> getRole(RoleBasicQuery RoleBasicQuery);

    /**
     * 获取角色树信息
     *
     * @param RoleTreeQuery
     * @return
     */
    ListResult<RoleTreeDTO> getRoleTree(RoleTreeQuery RoleTreeQuery);

    /**
     * 角色禁用
     *
     * @param disableRoleCommand
     * @return
     */
    PojoResult<Boolean> disableRole(DisableRoleCommand disableRoleCommand);

    /**
     * 启用角色
     *
     * @param enableRoleCommand
     * @return
     */
    PojoResult<Boolean> enableRole(EnableRoleCommand enableRoleCommand);

    /**
     * 删除角色
     *
     * @param deleteRoleCommand
     * @return
     */
    PojoResult<Boolean> deleteRole(DeleteRoleCommand deleteRoleCommand);

    /**
     * 更新角色基本信息
     *
     * @param modifyRoleCommand
     * @return
     */
    PojoResult<Boolean> modifyRole(ModifyRoleCommand modifyRoleCommand);
}

```

## 权限点管理

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.permission.*;
import com.rany.acl.api.query.permission.PermissionBasicQuery;
import com.rany.ops.common.dto.permission.PermissionDTO;


/**
 * 权限点管理，权限点关联页面
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface PermissionFacade {


    /**
     * 创建权限
     *
     * @param createPermissionCommand
     * @return
     */
    PojoResult<Long> createPermission(CreatePermissionCommand createPermissionCommand);

    /**
     * 获取权限信息
     *
     * @param PermissionBasicQuery
     * @return
     */
    PojoResult<PermissionDTO> getPermission(PermissionBasicQuery PermissionBasicQuery);


    /**
     * 权限禁用
     *
     * @param disablePermissionCommand
     * @return
     */
    PojoResult<Boolean> disablePermission(DisablePermissionCommand disablePermissionCommand);

    /**
     * 启用权限
     *
     * @param enablePermissionCommand
     * @return
     */
    PojoResult<Boolean> enablePermission(EnablePermissionCommand enablePermissionCommand);

    /**
     * 删除权限
     *
     * @param deletePermissionCommand
     * @return
     */
    PojoResult<Boolean> deletePermission(DeletePermissionCommand deletePermissionCommand);

    /**
     * 更新权限基本信息
     *
     * @param modifyPermissionCommand
     * @return
     */
    PojoResult<Boolean> modifyPermission(ModifyPermissionCommand modifyPermissionCommand);
}


```

## 用户角色授权

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantUserRoleCommand;
import com.rany.acl.api.command.grant.GrantUserRoleCommand;

/**
 * 用户角色授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantUserRoleFacade {

    /**
     * 用户角色授权
     *
     * @param grantUserRoleCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantUserRole(GrantUserRoleCommand grantUserRoleCommand);


    /**
     * 解除用户角色授权
     *
     * @param disGrantUserRoleCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantUserRole(DisGrantUserRoleCommand disGrantUserRoleCommand);
}

```

## 角色菜单授权

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRoleMenusCommand;
import com.rany.acl.api.command.grant.GrantRoleMenusCommand;

/**
 * 角色菜单授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantRoleMenuFacade {


    /**
     * 角色菜单绑定
     *
     * @param grantRoleMenusCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantRoleMenus(GrantRoleMenusCommand grantRoleMenusCommand);


    /**
     * 解除角色菜单绑定
     *
     * @param disGrantRoleMenusCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantRoleMenus(DisGrantRoleMenusCommand disGrantRoleMenusCommand);
}


```

## 角色权限授权

```java
package com.rany.acl.api.facade;

import com.cake.framework.common.response.PojoResult;
import com.rany.acl.api.command.grant.DisGrantRolePermissionsCommand;
import com.rany.acl.api.command.grant.GrantRolePermissionsCommand;

/**
 * 角色权限授权
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface GrantRolePermissionFacade {

    /**
     * 角色权限绑定
     *
     * @param grantRolePermissionsCommand 授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> grantRolePermissions(GrantRolePermissionsCommand grantRolePermissionsCommand);


    /**
     * 解除角色权限绑定
     *
     * @param disGrantRolePermissionsCommand 解除授权请求
     * @return 是否成功
     */
    PojoResult<Boolean> disGrantRolePermissions(DisGrantRolePermissionsCommand disGrantRolePermissionsCommand);
}


```