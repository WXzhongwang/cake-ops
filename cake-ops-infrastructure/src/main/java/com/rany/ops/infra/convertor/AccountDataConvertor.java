package com.rany.ops.infra.convertor;

import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.domain.aggregate.Account;
import com.rany.ops.infra.po.AccountPO;
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
@Mapper(componentModel = "spring", uses = {TenantDataConvertor.class})
public interface AccountDataConvertor extends BaseConvertor<Account, AccountPO> {


    /**
     * 聚合根转PO
     *
     * @param account
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "accountName.name", target = "accountName")
    @Mapping(source = "tenantId.id", target = "tenantId")
    @Mapping(source = "emailAddress.email", target = "email")
    @Mapping(source = "phone.phone", target = "phone")
    @Mapping(source = "headImage.img", target = "headImage")
    @Mapping(expression = "java(cn.hutool.core.util.BooleanUtil.toString(account.getIsAdmin(), \"1\", \"0\"))", target = "isAdmin")
    @Override
    AccountPO sourceToTarget(Account account);


    /**
     * 聚合根转PO
     *
     * @param accountPO
     * @return
     */
    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "accountName.name", source = "accountName")
    @Mapping(target = "tenantId.id", source = "tenantId")
    @Mapping(target = "emailAddress.email", source = "email")
    @Mapping(target = "phone.phone", source = "phone")
    @Mapping(target = "headImage.img", source = "headImage")
    @Mapping(target = "isAdmin", expression = "java(cn.hutool.core.util.BooleanUtil.toBoolean(accountPO.getIsAdmin()))")
    @Override
    Account targetToSource(AccountPO accountPO);


    /**
     * source转DTO
     *
     * @param account
     * @return
     */
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "accountName.name", target = "accountName")
    @Mapping(source = "tenantId.id", target = "tenantId")
    @Mapping(source = "emailAddress.email", target = "email")
    @Mapping(source = "phone.phone", target = "phone")
    @Mapping(source = "headImage.img", target = "headImage")
    AccountDTO sourceToDTO(Account account);

    @InheritConfiguration(name = "sourceToDTO")
    List<AccountDTO> sourceToDTO(List<Account> account);


    /**
     * PO转DTO
     *
     * @param accountPO
     * @return
     */
    AccountDTO targetToDTO(AccountPO accountPO);

    /**
     * PO转DTO
     *
     * @param accountPO
     * @return
     */
    @InheritConfiguration(name = "targetToDTO")
    List<AccountDTO> targetToDTO(List<AccountPO> accountPO);
}
