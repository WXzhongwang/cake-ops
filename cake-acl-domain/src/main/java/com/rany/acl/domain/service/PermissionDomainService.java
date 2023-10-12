package com.rany.acl.domain.service;

import com.rany.acl.common.dto.permission.PermissionDTO;
import com.rany.acl.common.params.PermissionSearchParam;
import com.rany.acl.domain.aggregate.Permission;
import com.rany.acl.domain.pk.PermissionId;
import com.rany.acl.domain.repository.PermissionRepository;
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
public class PermissionDomainService {

    @Resource
    private PermissionRepository permissionRepository;


    public Permission findById(PermissionId permissionId) {
        return permissionRepository.find(permissionId);
    }


    public List<PermissionDTO> selectPermissionList(PermissionSearchParam searchParam) {
        return permissionRepository.findPermissions(searchParam);
    }

    public Boolean save(Permission permission) {
        permissionRepository.save(permission);
        return Boolean.TRUE;
    }

    public Boolean update(Permission permission) {
        permissionRepository.update(permission);
        return Boolean.TRUE;
    }
}
