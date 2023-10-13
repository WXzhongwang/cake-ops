package com.rany.acl.infra.convertor;

import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.dto.role.RoleTreeDTO;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.infra.po.RolePO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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


    @Mapping(source = "id.id", target = "roleId")
    RoleDTO sourceToDTO(Role role);


    /**
     * PO转DTO
     *
     * @param rolePO
     * @return
     */
    @Mapping(source = "id", target = "roleId")
    RoleDTO targetToDTO(RolePO rolePO);

    /**
     * PO转DTO
     *
     * @param rolePOS
     * @return
     */
    @InheritConfiguration(name = "targetToDTO")
    List<RoleDTO> targetToDTO(List<RolePO> rolePOS);

    List<RoleTreeDTO> convertToTreeDTO(List<RoleDTO> menuDTOS);
}
