package com.rany.acl.domain.repository.impl;

import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.convertor.RoleDataConvertor;
import com.rany.acl.domain.dao.RoleDao;
import com.rany.acl.domain.pk.RoleId;
import com.rany.acl.domain.repository.RoleRepository;
import com.rany.acl.infra.mapper.RolePOMapper;
import com.rany.acl.infra.po.RolePO;
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
public class RoleRepositoryImpl implements RoleRepository {

    private final RolePOMapper roleMapper;
    private final RoleDao roleDao;
    private final RoleDataConvertor roleDataConvertor;

    @Override
    public Role find(@NotNull RoleId roleId) {
        RolePO rolePO = roleMapper.selectByPrimaryKey(roleId.getId());
        return roleDataConvertor.targetToSource(rolePO);
    }

    @Override
    public void remove(@NotNull Role role) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Role role) {
        roleDao.save(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }
}
