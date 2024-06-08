package com.rany.ops.infra.repo.impl;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.ops.common.dto.application.ApplicationDTO;
import com.rany.ops.common.exception.BusinessErrorMessage;
import com.rany.ops.common.params.ApplicationPageSearchParam;
import com.rany.ops.common.params.ApplicationSearchParam;
import com.rany.ops.domain.aggregate.Application;
import com.rany.ops.domain.page.annotation.PagingQuery;
import com.rany.ops.domain.pk.ApplicationId;
import com.rany.ops.domain.repository.ApplicationRepository;
import com.rany.ops.infra.convertor.ApplicationDataConvertor;
import com.rany.ops.infra.dao.ApplicationDao;
import com.rany.ops.infra.mapper.ApplicationPOMapper;
import com.rany.ops.infra.po.ApplicationPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/26 00:18
 * @email 18668485565163.com
 */
@AllArgsConstructor
@Slf4j
@Service
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final ApplicationPOMapper applicationPOMapper;
    private final ApplicationDao applicationDao;
    private final ApplicationDataConvertor applicationDataConvertor;

    @Override
    public Application find(@NotNull ApplicationId applicationId) {
        ApplicationPO applicationPO = applicationPOMapper.selectByPrimaryKey(applicationId.getId());
        if (applicationPO == null) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        return applicationDataConvertor.targetToSource(applicationPO);
    }

    @Override
    public void remove(@NotNull Application application) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Application application) {
        applicationDao.save(application);
    }


    @Override
    public int update(Application account) {
        return applicationDao.update(account);
    }

    @Override
    public Application findByAppCode(String appCode) {
        ApplicationPO applicationPO = applicationDao.selectByAppCode(appCode);
        if (applicationPO == null) {
            throw new BusinessException(BusinessErrorMessage.APP_NOT_FOUND);
        }
        return applicationDataConvertor.targetToSource(applicationPO);
    }


    @Override
    public List<ApplicationDTO> findApplications(ApplicationSearchParam applicationSearchParam) {
        List<ApplicationPO> accountPOS = applicationDao.selectList(applicationSearchParam);
        return applicationDataConvertor.targetToDTO(accountPOS);
    }

    @Override
    @PagingQuery
    public Page<ApplicationDTO> pageApplications(ApplicationPageSearchParam applicationPageSearchParam) {
        List<ApplicationPO> content = applicationDao.selectPage(applicationPageSearchParam);
        PageInfo<ApplicationPO> pageInfo = new PageInfo<>(content);

        Page<ApplicationDTO> pageDTO = new Page<>();
        pageDTO.setPageNo(pageInfo.getPageNum());
        pageDTO.setPageSize(pageInfo.getPageSize());
        pageDTO.setTotalPage(pageInfo.getPages());
        pageDTO.setTotal(Long.valueOf(pageInfo.getTotal()).intValue());
        List<ApplicationDTO> values = applicationDataConvertor.targetToDTO(pageInfo.getList());
        pageDTO.setItems(values);
        return pageDTO;
    }
}
