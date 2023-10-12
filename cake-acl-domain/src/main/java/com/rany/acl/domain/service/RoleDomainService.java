package com.rany.acl.domain.service;

import com.rany.acl.common.dto.role.RoleDTO;
import com.rany.acl.common.params.RoleSearchParam;
import com.rany.acl.common.params.SubRoleSearchParam;
import com.rany.acl.domain.aggregate.Role;
import com.rany.acl.domain.pk.RoleId;
import com.rany.acl.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class RoleDomainService {

    @Resource
    private RoleRepository roleRepository;


    public Role findById(RoleId roleId) {
        return roleRepository.find(roleId);
    }

    public Role findByRoleKey(String appCode, Long tenantId, String roleKey) {
        return roleRepository.findByRoleKey(appCode, tenantId, roleKey);
    }

    public List<RoleDTO> selectRoleList(RoleSearchParam searchParam) {
        return roleRepository.findRoles(searchParam);
    }

    public List<Role> findSubRoleListByRoleId(RoleId roleId) {
        SubRoleSearchParam searchParam = new SubRoleSearchParam();
        searchParam.setRoleId(roleId.getId());
        return roleRepository.findSubRoles(searchParam);
    }

    /**
     * 获取下级全部角色列表，全部层级，包含未启用的
     *
     * @param roleId
     * @return
     */
    public List<Role> findAllSubRolesByRoleId(RoleId roleId) {
        List<Role> menus = new ArrayList<>();
        recursive(roleId.getId(), menus);
        return menus;
    }


    public void recursive(Long roleId, List<Role> menus) {
        SubRoleSearchParam searchParam = new SubRoleSearchParam();
        searchParam.setRoleId(roleId);
        List<Role> subRoles = roleRepository.findSubRoles(searchParam);
        menus.addAll(subRoles);
        if (CollectionUtils.isNotEmpty(subRoles)) {
            for (Role subRole : subRoles) {
                recursive(subRole.getId().getId(), menus);
            }
        }
    }


    public Boolean save(Role role) {
        roleRepository.save(role);
        return Boolean.TRUE;
    }

    public Boolean update(Role role) {
        roleRepository.update(role);
        return Boolean.TRUE;
    }
}
