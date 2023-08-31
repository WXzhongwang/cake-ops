package com.rany.acl.domain.repository.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import com.rany.acl.common.enums.DeleteStatusEnum;
import com.rany.acl.domain.repository.IsvRepository;
import com.rany.acl.infra.mapper.IsvPOMapper;
import com.rany.acl.domain.aggregate.Isv;
import com.rany.acl.domain.convertor.IsvDataConvertor;
import com.rany.acl.domain.dao.IsvDao;
import com.rany.acl.domain.pk.IsvId;
import com.rany.acl.infra.po.IsvPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

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
public class IsvRepositoryImpl implements IsvRepository {

    private final IsvPOMapper isvPOMapper;
    private final IsvDao isvDao;
    private final IsvDataConvertor isvDataConvertor;

    @Override
    public Isv find(@NotNull IsvId isvId) {
        IsvPO isvPo = isvPOMapper.selectByPrimaryKey(isvId.getId());
        return isvDataConvertor.targetToSource(isvPo);
    }

    @Override
    public void remove(@NotNull Isv isv) {
        IsvId id = isv.getId();
        IsvPO isvPo = isvPOMapper.selectByPrimaryKey(id.getId());
        isvPo.setIsDeleted(DeleteStatusEnum.YES.getValue());
        isvPo.setGmtModified(DateUtil.date());
        isvPOMapper.updateByPrimaryKey(isvPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Isv isv) {
        isvDao.save(isv);
    }

    @Override
    public Boolean updateIsv(Isv isv) {
        IsvPO isvPO = isvDataConvertor.sourceToTarget(isv);
        int update = isvPOMapper.updateByPrimaryKey(isvPO);
        return BooleanUtil.isTrue(update > 0);
    }
}
