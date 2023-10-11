package com.rany.acl.domain.repository.impl;

import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.params.RoleSearchParam;
import com.rany.acl.common.params.SubRoleSearchParam;
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

    @Override
    public Role findByRoleKey(String appCode, Long tenantId, String roleKey) {
        RolePO rolePO = roleDao.selectByRoleKey(appCode, tenantId, roleKey);
        return roleDataConvertor.targetToSource(rolePO);
    }

    @Override
    public List<RoleDTO> findRoles(RoleSearchParam roleSearchParam) {
        List<RolePO> rolePOS = roleDao.selectList(roleSearchParam);
        return roleDataConvertor.targetToDTO(rolePOS);
    }

    @Override
    public List<Role> findSubRoles(SubRoleSearchParam subRoleSearchParam) {
        List<RolePO> rolePOList = roleDao.selectSubRoleList(subRoleSearchParam);
        return roleDataConvertor.targetToSource(rolePOList);
    }
}
