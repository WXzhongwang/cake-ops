package com.rany.ops.web.controller.acl;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.application.*;
import com.rany.ops.api.facade.application.ApplicationFacade;
import com.rany.ops.api.query.application.ApplicationBasicQuery;
import com.rany.ops.api.query.application.ApplicationPageQuery;
import com.rany.ops.common.dto.application.ApplicationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * application
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/application")
public class ApplicationController {

    @Resource
    private ApplicationFacade applicationFacade;

    @PostMapping("/page")
    public PageResult<ApplicationDTO> page(@RequestBody ApplicationPageQuery appPageQuery) {
        Page<ApplicationDTO> page = applicationFacade.pageApplications(appPageQuery);
        return PageResult.succeed(page);
    }

    @PostMapping("/get")
    public PojoResult<ApplicationDTO> get(ApplicationBasicQuery query) {
        ApplicationDTO application = applicationFacade.getApplication(query);
        return PojoResult.succeed(application);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateApplicationCommand createAppCommand) {
        Long application = applicationFacade.createApplication(createAppCommand);
        return PojoResult.succeed(application.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody ModifyApplicationCommand command) {
        Boolean success = applicationFacade.modifyApplication(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableApplicationCommand command) {
        Boolean application = applicationFacade.enableApplication(command);
        return PojoResult.succeed(application);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableApplicationCommand command) {
        Boolean application = applicationFacade.disableApplication(command);
        return PojoResult.succeed(application);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteApplicationCommand command) {
        Boolean application = applicationFacade.deleteApplication(command);
        return PojoResult.succeed(application);
    }
}
