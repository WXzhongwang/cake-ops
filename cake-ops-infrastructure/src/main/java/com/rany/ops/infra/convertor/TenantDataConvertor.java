package com.rany.ops.infra.convertor;

import com.rany.ops.common.dto.tenant.TenantDTO;
import com.rany.ops.domain.aggregate.Tenant;
import com.rany.ops.infra.po.TenantPO;
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
@Mapper(componentModel = "spring", uses = {IsvDataConvertor.class})
public interface TenantDataConvertor extends BaseConvertor<Tenant, TenantPO> {


    /**
     * 聚合根转PO
     *
     * @param tenant
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "isvId.id", target = "isvId")
    @Mapping(source = "tenantName.name", target = "name")
    @Mapping(source = "tenantName.shortName", target = "shortName")
    @Mapping(source = "emailAddress.email", target = "email")
    @Mapping(source = "source.source", target = "source")
    @Mapping(source = "phone.phone", target = "phone")
    @Override
    TenantPO sourceToTarget(Tenant tenant);


    /**
     * 聚合根转PO
     *
     * @param tenantPO
     * @return
     */
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "isvId.id", source = "isvId")
    @Mapping(target = "tenantName.name", source = "name")
    @Mapping(target = "tenantName.shortName", source = "shortName")
    @Mapping(target = "emailAddress.email", source = "email")
    @Mapping(target = "source.source", source = "source")
    @Mapping(target = "phone.phone", source = "phone")
    @Override
    Tenant targetToSource(TenantPO tenantPO);


    /**
     * 聚合根转DTO
     *
     * @param tenant
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "isvId.id", target = "isvId")
    @Mapping(source = "tenantName.name", target = "name")
    @Mapping(source = "tenantName.shortName", target = "shortName")
    @Mapping(source = "emailAddress.email", target = "email")
    @Mapping(source = "source.source", target = "source")
    @Mapping(source = "phone.phone", target = "phone")
    TenantDTO sourceToDTO(Tenant tenant);


    /**
     * 转DTO
     *
     * @param tenants
     * @return
     */
    @InheritConfiguration(name = "targetToDTO")
    List<TenantDTO> sourceToDTO(List<Tenant> tenants);
}
