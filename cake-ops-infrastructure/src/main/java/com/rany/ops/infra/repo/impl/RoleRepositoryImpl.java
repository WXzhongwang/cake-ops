package com.rany.ops.infra.repo.impl;

import com.rany.ops.common.dto.role.RoleDTO;
import com.rany.ops.common.params.*;
import com.rany.ops.domain.aggregate.Role;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.entity.RolePermission;
import com.rany.ops.domain.entity.UserRole;
import com.rany.ops.domain.pk.RoleId;
import com.rany.ops.domain.repository.RoleRepository;
import com.rany.ops.infra.convertor.RoleDataConvertor;
import com.rany.ops.infra.convertor.RoleMenuDataConvertor;
import com.rany.ops.infra.convertor.RolePermissionDataConvertor;
import com.rany.ops.infra.convertor.UserRoleDataConvertor;
import com.rany.ops.infra.dao.RoleDao;
import com.rany.ops.infra.dao.RoleMenuDao;
import com.rany.ops.infra.dao.RolePermissionDao;
import com.rany.ops.infra.dao.UserRoleDao;
import com.rany.ops.infra.mapper.RoleMenuPOMapper;
import com.rany.ops.infra.mapper.RolePOMapper;
import com.rany.ops.infra.mapper.RolePermissionPOMapper;
import com.rany.ops.infra.mapper.UserRolePOMapper;
import com.rany.ops.infra.po.RoleMenuPO;
import com.rany.ops.infra.po.RolePO;
import com.rany.ops.infra.po.RolePermissionPO;
import com.rany.ops.infra.po.UserRolePO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
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
    private final UserRolePOMapper userRolePOMapper;
    private final UserRoleDao userRoleDao;
    private final UserRoleDataConvertor userRoleDataConvertor;
    private final RoleMenuPOMapper roleMenuPOMapper;
    private final RoleMenuDao roleMenuDao;
    private final RoleMenuDataConvertor roleMenuDataConvertor;

    private final RolePermissionPOMapper rolePermissionPOMapper;
    private final RolePermissionDao rolePermissionDao;
    private final RolePermissionDataConvertor rolePermissionDataConvertor;

    @Override
    public Role find(@NotNull RoleId roleId) {
        RolePO rolePO = roleMapper.selectByPrimaryKey(roleId.getId());
        return roleDataConvertor.targetToSource(rolePO);
    }

    @Override
    public void remove(@NotNull Role role) {
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public UserRole findUserRole(UserRoleSearchParam userRoleSearchParam) {
        UserRolePO userRole = userRoleDao.selectUserRole(userRoleSearchParam);
        if (userRole == null) {
            return null;
        }
        return userRoleDataConvertor.targetToSource(userRole);
    }

    @Override
    public List<UserRole> findUserRoles(UserRoleSearchParam userRoleSearchParam) {
        List<UserRolePO> userRoles = userRoleDao.selectUserRoleList(userRoleSearchParam);
        if (userRoles == null || userRoles.isEmpty()) {
            return Collections.emptyList();
        }
        return userRoleDataConvertor.targetToSource(userRoles);
    }

    @Override
    public List<RoleMenu> findRoleMenus(RoleMenuSearchParam roleMenuSearchParam) {
        List<RoleMenuPO> roleMenuPOS = roleMenuDao.selectRoleMenuList(roleMenuSearchParam);
        if (roleMenuPOS == null || roleMenuPOS.isEmpty()) {
            return Collections.emptyList();
        }
        return roleMenuDataConvertor.targetToSource(roleMenuPOS);
    }

    @Override
    public List<RolePermission> findRolePermissions(RolePermissionSearchParam rolePermissionSearchParam) {
        List<RolePermissionPO> rolePermissionPOS = rolePermissionDao.selectRolePermissionList(rolePermissionSearchParam);
        if (rolePermissionPOS == null || rolePermissionPOS.isEmpty()) {
            return Collections.emptyList();
        }
        return rolePermissionDataConvertor.targetToSource(rolePermissionPOS);
    }

    @Override
    public Boolean saveUserRole(UserRole userRole) {
        userRoleDao.save(userRole);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUserRole(UserRole userRole) {
        userRoleDao.update(userRole);
        return Boolean.TRUE;
    }

    @Override
    public Boolean saveRoleMenu(RoleMenu roleMenu) {
        roleMenuDao.save(roleMenu);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateRoleMenu(RoleMenu roleMenu) {
        roleMenuDao.update(roleMenu);
        return Boolean.TRUE;
    }

    @Override
    public Boolean saveRolePermission(RolePermission rolePermission) {
        rolePermissionDao.save(rolePermission);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateRolePermission(RolePermission rolePermission) {
        rolePermissionDao.update(rolePermission);
        return Boolean.TRUE;
    }
}
