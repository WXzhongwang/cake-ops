package com.rany.ops.facade.isv;

import cn.hutool.core.lang.Assert;
import com.rany.ops.BaseTests;
import com.rany.ops.api.command.isv.CreateIsvCommand;
import com.rany.ops.api.command.isv.DeleteIsvCommand;
import com.rany.ops.api.command.isv.DisableIsvCommand;
import com.rany.ops.api.facade.isv.IsvFacade;
import com.rany.ops.api.query.isv.IsvBasicQuery;
import com.rany.ops.common.dto.isv.IsvDTO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/28 19:53
 * @email 18668485565163.com
 */
public class IsvFacadeTest extends BaseTests {

    @Resource
    private IsvFacade isvFacade;

    @Test
    public void createIsv() {
        CreateIsvCommand createIsvCommand = new CreateIsvCommand();
        createIsvCommand.setName("杭州锐尼科技有限公司");
        createIsvCommand.setShortName("hzrany");
        createIsvCommand.setEmail("18668485565@163.com");
        createIsvCommand.setUrl("www.ranytech.com");
        createIsvCommand.setCountry("中国");
        createIsvCommand.setRegisterIp("127.0.0.1");
        createIsvCommand.setPhone("18668485565");
        Long isv = isvFacade.createIsv(createIsvCommand);
        Assert.notNull(isv);
    }

    @Test
    public void deleteIsv() {
        DeleteIsvCommand deleteIsvCommand = new DeleteIsvCommand();
        deleteIsvCommand.setId(768060752375459840L);
        Boolean isv = isvFacade.deleteIsv(deleteIsvCommand);
        Assert.isTrue(isv);
    }

    @Test
    public void disableIsv() {
        DisableIsvCommand disableIsvCommand = new DisableIsvCommand();
        disableIsvCommand.setId(768060752375459840L);
        Boolean isv = isvFacade.disableIsv(disableIsvCommand);
        Assert.isTrue(isv);
    }

    @Test
    public void basicQueryIsv() {
        IsvBasicQuery isvBaseQuery = new IsvBasicQuery();
        isvBaseQuery.setIsvId(768060752375459840L);
        IsvDTO isv = isvFacade.findIsv(isvBaseQuery);
        Assert.notNull(isv);
    }
}