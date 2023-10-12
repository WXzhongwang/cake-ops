package com.rany.acl.api.command.application;

import com.rany.acl.common.base.BaseCommand;
import lombok.Data;

/**
 * 更新账号基本信息
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
public class ModifyApplicationCommand extends BaseCommand {

    /**
     * accountId
     */
    private Long appId;
    private String appName;
    private String authType;
}
