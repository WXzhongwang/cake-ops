package com.rany.ops.infra.convertor;

import com.rany.ops.common.dto.account.SafeStrategyDTO;
import com.rany.ops.domain.entity.SafeStrategy;
import com.rany.ops.infra.po.SafeStrategyPO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

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
public interface SafeStrategyConvertor extends BaseConvertor<SafeStrategy, SafeStrategyPO> {


    SafeStrategyDTO sourceToDTO(SafeStrategy strategy);

    @InheritConfiguration(name = "sourceToDTO")
    List<SafeStrategyDTO> sourceToDTO(List<SafeStrategy> strategies);
}
