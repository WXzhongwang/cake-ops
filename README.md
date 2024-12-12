# CAKE ops

# 介绍

**cake-ops** 是一个基于 SpringBoot + UIC + ACL 的服务端应用, 应用，账号，角色，菜单，授权 统一管控。

1. springboot 2.2.6.RELEASE
2. RBAC

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

![租户账号模型](https://github.com/WXzhongwang/cake-ops/blob/main/WX20231015-225622@2x.png)

# 快速使用

## GAV

```xml

<parent>
    <artifactId>cake-ops-api</artifactId>
    <groupId>com.rany.ops</groupId>
    <version>1.0.0-SNAPSHOT</version>
</parent>

```

## ISV接口

```java
package com.rany.ops.api.facade.isv;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.isv.*;
import com.rany.ops.api.query.isv.IsvBasicQuery;
import com.rany.ops.api.query.isv.IsvPageQuery;
import com.rany.ops.common.dto.isv.IsvDTO;

/**
 * IsvFacade
 *
 * @author zhongshengwang
 * @description IsvFacade
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public interface IsvFacade {


    /**
     * 创建ISV
     *
     * @param createIsvCommand 指令
     * @return isv id
     */
    Long createIsv(CreateIsvCommand createIsvCommand);

    /**
     * 更新ISV
     *
     * @param command 指令
     * @return 是否成功
     */
    Boolean updateIsv(UpdateIsvCommand command);


    /**
     * ISV 删除
     *
     * @param deleteIsvCommand 指令
     * @return 是否成功
     */
    Boolean deleteIsv(DeleteIsvCommand deleteIsvCommand);


    /**
     * 禁用ISV
     *
     * @param disableIsvCommand 指令
     * @return 是否成功
     */
    Boolean disableIsv(DisableIsvCommand disableIsvCommand);


    /**
     * 启用ISV
     *
     * @param enableIsvCommand 指令
     * @return 是否成功
     */
    Boolean enableIsv(EnableIsvCommand enableIsvCommand);


    /**
     * 查询ISV基本信息
     *
     * @param isvBaseQuery isv查询
     * @return isv
     */
    IsvDTO findIsv(IsvBasicQuery isvBaseQuery);


    /**
     * 分页查询ISV信息
     *
     * @param isvPageQuery 查询参数
     * @return 分页结果
     */
    Page<IsvDTO> pageIsv(IsvPageQuery isvPageQuery);
}

```

## 租户管理

```java
package com.rany.ops.api.facade.tenant;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.tenant.*;
import com.rany.ops.api.query.tenant.TenantBasicQuery;
import com.rany.ops.api.query.tenant.TenantPageQuery;
import com.rany.ops.api.query.tenant.TenantQuery;
import com.rany.ops.common.dto.tenant.TenantDTO;

import java.util.List;

/**
 * 租户管理
 *
 * @author zhongshengwang
 * @description 租户管理
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public interface TenantFacade {


    /**
     * 创建租户
     *
     * @param createTenantCommand
     * @return
     */
    Long createTenant(CreateTenantCommand createTenantCommand);

    /**
     * 更新租户
     *
     * @param modifyTenantCommand
     * @return
     */
    Boolean modifyTenant(ModifyTenantCommand modifyTenantCommand);

    /**
     * 租户禁用
     *
     * @param disableTenantCommand
     * @return
     */
    Boolean disableTenant(DisableTenantCommand disableTenantCommand);

    /**
     * 启用租户
     *
     * @param enableTenantCommand
     * @return
     */
    Boolean enableTenant(EnableTenantCommand enableTenantCommand);

    /**
     * 启用租户
     *
     * @param deleteTenantCommand
     * @return
     */
    Boolean deleteTenant(DeleteTenantCommand deleteTenantCommand);


    /**
     * 查询单个租户信息
     *
     * @param tenantBasicQuery
     * @return
     */
    TenantDTO getTenant(TenantBasicQuery tenantBasicQuery);


    /**
     * 查询指定isv全部租户信息
     *
     * @param tenantQuery
     * @return
     */
    List<TenantDTO> findTenants(TenantQuery tenantQuery);

    /**
     * 分页查询租户信息
     *
     * @param tenantPageQuery
     * @return
     */
    Page<TenantDTO> pageTenants(TenantPageQuery tenantPageQuery);
}

```

## 租户账号

```java
package com.rany.ops.api.facade.account;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.account.*;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.api.query.account.AccountDingIdQuery;
import com.rany.ops.api.query.account.AccountPageQuery;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;

import java.util.List;

/**
 * 账号服务
 *
 * @author zhongshengwang
 * @description 账号服务
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface AccountFacade {


    /**
     * 创建租户账号
     *
     * @param command command
     * @return accountId
     */
    Long createAccount(CreateAccountCommand command);

    /**
     * 获取账号信息
     *
     * @param accountBasicQuery query
     * @return account
     */
    AccountDTO getAccount(AccountBasicQuery accountBasicQuery);


    /**
     * 获取账号信息
     *
     * @param accountBasicQuery query
     * @return account
     */
    AccountDTO getAccountByDingId(AccountDingIdQuery accountBasicQuery);

    /**
     * 账号禁用
     *
     * @param disableAccountCommand 指令
     * @return success
     */
    Boolean disableAccount(DisableAccountCommand disableAccountCommand);

    /**
     * 启用账号
     *
     * @param enableAccountCommand 指令
     * @return success
     */
    Boolean enableAccount(EnableAccountCommand enableAccountCommand);

    /**
     * 删除账户
     *
     * @param deleteAccountCommand 指令
     * @return success
     */
    Boolean deleteAccount(DeleteAccountCommand deleteAccountCommand);

    /**
     * 更新账号基本信息
     *
     * @param modifyAccountCommand 指令
     * @return success
     */
    Boolean modifyAccount(ModifyAccountCommand modifyAccountCommand);

    /**
     * 创建登录策略
     *
     * @param createSafeStrategyCommand 指令
     * @return success
     */
    Boolean createSafeStrategy(CreateSafeStrategyCommand createSafeStrategyCommand);

    /**
     * 更新登录策略
     *
     * @param updateSafeStrategyCommand 指令
     * @return success
     */
    Boolean updateSafeStrategy(UpdateSafeStrategyCommand updateSafeStrategyCommand);


    /**
     * 分页查询账号
     *
     * @param accountQuery 查询
     * @return 账号
     */
    List<AccountDTO> findAccounts(AccountQuery accountQuery);

    /**
     * 查询账号
     *
     * @param accountPageQuery 查询
     * @return 分页
     */
    Page<AccountDTO> pageAccounts(AccountPageQuery accountPageQuery);

}

```

## 应用管理接口

```java
package com.rany.ops.api.facade.application;

import com.cake.framework.common.response.Page;
import com.rany.ops.api.command.application.*;
import com.rany.ops.api.query.application.ApplicationBasicQuery;
import com.rany.ops.api.query.application.ApplicationPageQuery;
import com.rany.ops.api.query.application.ApplicationQuery;
import com.rany.ops.common.dto.application.ApplicationDTO;

import java.util.List;

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
     * @param command 指令
     * @return 应用id
     */
    Long createApplication(CreateApplicationCommand command);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery 查询
     * @return 应用信息
     */
    ApplicationDTO getApplication(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 获取应用信息
     *
     * @param applicationBasicQuery 查询
     * @return 应用信息
     */
    ApplicationDTO getApplicationByAppCode(ApplicationBasicQuery applicationBasicQuery);

    /**
     * 应用禁用
     *
     * @param disableApplicationCommand 指令
     * @return boolean
     */
    Boolean disableApplication(DisableApplicationCommand disableApplicationCommand);

    /**
     * 启用应用
     *
     * @param enableApplicationCommand 指令
     * @return boolean
     */
    Boolean enableApplication(EnableApplicationCommand enableApplicationCommand);

    /**
     * 删除应用
     *
     * @param deleteApplicationCommand 指令
     * @return boolean
     */
    Boolean deleteApplication(DeleteApplicationCommand deleteApplicationCommand);

    /**
     * 更新应用基本信息
     *
     * @param modifyApplicationCommand 指令
     * @return boolean
     */
    Boolean modifyApplication(ModifyApplicationCommand modifyApplicationCommand);


    /**
     * 分页查询应用
     *
     * @param applicationQuery 查询
     * @return 列表
     */
    List<ApplicationDTO> findApplications(ApplicationQuery applicationQuery);

    /**
     * 查询应用分页
     *
     * @param applicationPageQuery 查询
     * @return 分页
     */
    Page<ApplicationDTO> pageApplications(ApplicationPageQuery applicationPageQuery);

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
package com.rany.ops.api.facade.menu;

import com.rany.ops.api.command.menu.*;
import com.rany.ops.api.query.menu.MenuBasicQuery;
import com.rany.ops.api.query.menu.MenuTreeQuery;
import com.rany.ops.common.dto.menu.MenuDTO;
import com.rany.ops.common.dto.menu.MenuTreeDTO;

import java.util.List;

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
     * @param createMenuCommand 创建菜单
     * @return 菜单ID
     */
    Long createMenu(CreateMenuCommand createMenuCommand);

    /**
     * 获取菜单信息
     *
     * @param menuBasicQuery 基础信息获取
     * @return
     */
    MenuDTO getMenu(MenuBasicQuery menuBasicQuery);

    /**
     * 获取菜单树信息
     *
     * @param menuTreeQuery 获取菜单树
     * @return 获取菜单树
     */
    List<MenuTreeDTO> getMenuTree(MenuTreeQuery menuTreeQuery);

    /**
     * 菜单禁用
     *
     * @param disableMenuCommand 菜单禁用
     * @return 是否成功
     */
    Boolean disableMenu(DisableMenuCommand disableMenuCommand);

    /**
     * 启用菜单
     *
     * @param enableMenuCommand 菜单启用
     * @return 是否成功
     */
    Boolean enableMenu(EnableMenuCommand enableMenuCommand);

    /**
     * 删除菜单
     *
     * @param deleteMenuCommand 菜单删除
     * @return 是否成功
     */
    Boolean deleteMenu(DeleteMenuCommand deleteMenuCommand);

    /**
     * 更新菜单基本信息
     *
     * @param modifyMenuCommand 菜单更新
     * @return 是否成功
     */
    Boolean modifyMenu(ModifyMenuCommand modifyMenuCommand);
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

## 角色权限模型接口查询

```java
package com.rany.ops.api.facade.grant;

import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;

/**
 * 查询用户角色权限模型接口
 *
 * @author zhongshengwang
 * @description 应用管理
 * @date 2022/12/27 20:39
 * @email 18668485565163.com
 */
public interface RbacQueryFacade {


    /**
     * 获取单一用户应用下权限集合
     *
     * @param query 查询条件
     * @return 单一用户应用下权限集合
     */
    UserRoleMenuDTO getUserRbacModel(UserRoleMenuPermissionQuery query);
}

```