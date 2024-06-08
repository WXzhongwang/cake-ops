package com.rany.ops.infra.convertor;

import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.infra.po.ApplicationPO;
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
public interface ApplicationDataConvertor extends BaseConvertor<Application, ApplicationPO> {


    /**
     * 聚合根转PO
     *
     * @param application
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Override
    ApplicationPO sourceToTarget(Application application);


    @Mapping(source = "id.id", target = "id")
    ApplicationDTO sourceToDTO(Application application);

    ApplicationDTO targetToDTO(ApplicationPO applicationPO);

    @InheritConfiguration(name = "targetToDTO")
    List<ApplicationDTO> targetToDTO(List<ApplicationPO> applicationPO);

    /**
     * 聚合根转PO
     *
     * @param applicationPO
     * @return
     */
    @Override
    @Mapping(source = "id", target = "id.id")
    Application targetToSource(ApplicationPO applicationPO);
}
