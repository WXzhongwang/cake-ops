package com.rany.ops.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.ops.common.params.IsvSearchParam;
import com.rany.ops.domain.aggregate.Isv;
import com.rany.ops.domain.pk.IsvId;
import com.rany.ops.domain.repository.IsvRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/1 22:36
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class IsvDomainService {

    @Resource
    private IsvRepository isvRepository;


    public Boolean save(Isv isv) {
        isvRepository.save(isv);
        return Boolean.TRUE;
    }

    public Boolean update(Isv isv) {
        return isvRepository.updateIsv(isv);
    }

    public Isv findById(IsvId isvId) {
        return isvRepository.find(isvId);
    }

    public Page<Isv> page(IsvSearchParam isvPageQuery) {
        return isvRepository.page(isvPageQuery);
    }

    public List<Isv> list(IsvSearchParam isvPageQuery) {
        return isvRepository.list(isvPageQuery);
    }

}
