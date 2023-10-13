package com.rany.acl.infra.convertor;

import com.rany.acl.common.dto.permission.PermissionDTO;
import com.rany.acl.domain.aggregate.Permission;
import com.rany.acl.infra.po.PermissionPO;
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
public interface PermissionDataConvertor extends BaseConvertor<Permission, PermissionPO> {


    /**
     * 聚合根转PO
     *
     * @param permission
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Override
    PermissionPO sourceToTarget(Permission permission);

    /**
     * 聚合根转PO
     *
     * @param rolePO
     * @return
     */
    @Override
    @Mapping(source = "id", target = "id.id")
    Permission targetToSource(PermissionPO rolePO);


    @Mapping(source = "id.id", target = "permissionId")
    PermissionDTO sourceToDTO(Permission permission);


    /**
     * PO转DTO
     *
     * @param rolePO
     * @return
     */
    @Mapping(source = "id", target = "permissionId")
    PermissionDTO targetToDTO(PermissionPO rolePO);

    /**
     * PO转DTO
     *
     * @param permissionPOS
     * @return
     */
    @InheritConfiguration(name = "targetToDTO")
    List<PermissionDTO> targetToDTO(List<PermissionPO> permissionPOS);
}
