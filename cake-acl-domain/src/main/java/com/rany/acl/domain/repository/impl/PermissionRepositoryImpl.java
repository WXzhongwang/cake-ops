package com.rany.acl.domain.repository.impl;

import com.rany.acl.common.dto.permission.PermissionDTO;
import com.rany.acl.common.params.PermissionSearchParam;
import com.rany.acl.domain.aggregate.Permission;
import com.rany.acl.domain.convertor.PermissionDataConvertor;
import com.rany.acl.domain.dao.PermissionDao;
import com.rany.acl.domain.pk.PermissionId;
import com.rany.acl.domain.repository.PermissionRepository;
import com.rany.acl.infra.mapper.PermissionPOMapper;
import com.rany.acl.infra.po.PermissionPO;
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
public class PermissionRepositoryImpl implements PermissionRepository {

    private final PermissionPOMapper permissionPOMapper;
    private final PermissionDao permissionDao;
    private final PermissionDataConvertor permissionDataConvertor;

    @Override
    public Permission find(@NotNull PermissionId permissionId) {
        PermissionPO permissionPO = permissionPOMapper.selectByPrimaryKey(permissionId.getId());
        return permissionDataConvertor.targetToSource(permissionPO);
    }

    @Override
    public void remove(@NotNull Permission permission) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public int update(Permission permission) {
        return permissionDao.update(permission);
    }


    @Override
    public List<PermissionDTO> findPermissions(PermissionSearchParam roleSearchParam) {
        List<PermissionPO> rolePOS = permissionDao.selectList(roleSearchParam);
        return permissionDataConvertor.targetToDTO(rolePOS);
    }
}
