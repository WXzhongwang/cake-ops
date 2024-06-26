package com.rany.ops.domain.service;

import com.rany.ops.common.params.RoleMenuSearchParam;
import com.rany.ops.domain.entity.RoleMenu;
import com.rany.ops.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class RoleMenuDomainService {

    @Resource
    private RoleRepository roleRepository;

    public List<RoleMenu> getRoleMenus(RoleMenuSearchParam roleMenuSearchParam) {
        return roleRepository.findRoleMenus(roleMenuSearchParam);
    }

    public Boolean save(RoleMenu roleMenu) {
        return roleRepository.saveRoleMenu(roleMenu);
    }

    public Boolean update(RoleMenu roleMenu) {
        return roleRepository.updateRoleMenu(roleMenu);
    }
}
