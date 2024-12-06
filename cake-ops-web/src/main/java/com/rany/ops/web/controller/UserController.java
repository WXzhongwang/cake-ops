package com.rany.ops.web.controller;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.configuration.SsoConstants;
import com.rany.cake.dingtalk.sdk.utils.SsoTokenLoginHelper;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.Constants;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import com.rany.ops.common.exception.BusinessErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/user")
public class UserController {
    @Resource
    private RbacQueryFacade rbacQueryFacade;

    @GetMapping("/current")

    public PojoResult<SsoUser> getUser(HttpServletRequest request) {
        String token = request.getHeader(SsoConstants.TOKEN_AUTH_HEADER);
        if (StringUtils.isNotEmpty(token)) {
            return PojoResult.succeed(SsoTokenLoginHelper.getStorageUser(token));
        }
        return PojoResult.succeed(SsoUtil.getCurrentUser(request));
    }
    
    @PostMapping("/menu")
    public PojoResult<UserRoleMenuDTO> getUser(@CurrentUser SsoUser ssoUser) {
        UserRoleMenuPermissionQuery userRoleMenuPermissionQuery = new UserRoleMenuPermissionQuery();
        userRoleMenuPermissionQuery.setAccountId(Long.valueOf(ssoUser.getUserId()));
        userRoleMenuPermissionQuery.setAppCode(Constants.ACL_PROJECT);
        UserRoleMenuDTO userRbacModel = rbacQueryFacade.getUserRbacModel(userRoleMenuPermissionQuery);
        if (userRbacModel == null) {
            throw new BusinessException(BusinessErrorMessage.USER_ROLE_REL_NOT_EXIST);
        }
        return PojoResult.succeed(userRbacModel);
    }
}
