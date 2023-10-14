package com.rany.acl.infra.convertor;

import com.rany.acl.domain.entity.RolePermission;
import com.rany.acl.infra.po.RolePermissionPO;
import org.mapstruct.Mapper;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface RolePermissionDataConvertor extends BaseConvertor<RolePermission, RolePermissionPO> {

}
