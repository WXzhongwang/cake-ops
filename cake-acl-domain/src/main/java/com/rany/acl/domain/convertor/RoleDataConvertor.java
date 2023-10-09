package com.rany.acl.domain.convertor;

import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.infra.po.RolePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface RoleDataConvertor extends BaseConvertor<Role, RolePO> {


    /**
     * 聚合根转PO
     *
     * @param role
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Override
    RolePO sourceToTarget(Role role);

    /**
     * 聚合根转PO
     *
     * @param rolePO
     * @return
     */
    @Override
    @Mapping(source = "id", target = "id.id")
    Role targetToSource(RolePO rolePO);
}
