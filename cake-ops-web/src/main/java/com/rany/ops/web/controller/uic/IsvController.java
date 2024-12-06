package com.rany.ops.web.controller.uic;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.ops.api.command.isv.*;
import com.rany.ops.api.facade.isv.IsvFacade;
import com.rany.ops.api.query.isv.IsvBasicQuery;
import com.rany.ops.api.query.isv.IsvPageQuery;
import com.rany.ops.common.dto.isv.IsvDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * isv
 *
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/ops/isv")
public class IsvController {

    @Resource
    private IsvFacade isvFacade;

    @PostMapping("/page")
    public PageResult<IsvDTO> page(@RequestBody IsvPageQuery appPageQuery) {
        Page<IsvDTO> page = isvFacade.pageIsv(appPageQuery);
        return PageResult.succeed(page);
    }

    @PostMapping("/get")
    public PojoResult<IsvDTO> get(IsvBasicQuery query) {
        IsvDTO isv = isvFacade.findIsv(query);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/create")
    public PojoResult<String> create(@RequestBody CreateIsvCommand createAppCommand) {
        Long isv = isvFacade.createIsv(createAppCommand);
        return PojoResult.succeed(isv.toString());
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody UpdateIsvCommand command) {
        Boolean success = isvFacade.updateIsv(command);
        return PojoResult.succeed(success);
    }

    @PostMapping("/enable")
    public PojoResult<Boolean> enable(@RequestBody EnableIsvCommand command) {
        Boolean isv = isvFacade.enableIsv(command);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/disable")
    public PojoResult<Boolean> disable(@RequestBody DisableIsvCommand command) {
        Boolean isv = isvFacade.disableIsv(command);
        return PojoResult.succeed(isv);
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteIsvCommand command) {
        Boolean isv = isvFacade.deleteIsv(command);
        return PojoResult.succeed(isv);
    }
}
