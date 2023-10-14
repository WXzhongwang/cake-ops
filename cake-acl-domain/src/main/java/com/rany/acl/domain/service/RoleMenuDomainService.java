package com.rany.acl.domain.service;

import com.rany.acl.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RoleMenuDomainService {

    @Resource
    private RoleRepository roleRepository;

}
