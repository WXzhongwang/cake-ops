package com.rany.ops.infra.convertor;

import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.infra.po.UserRolePO;
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
public interface UserRoleDataConvertor extends BaseConvertor<UserRole, UserRolePO> {

}
