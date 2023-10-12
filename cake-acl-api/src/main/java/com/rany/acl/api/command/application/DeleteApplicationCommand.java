package com.rany.acl.api.command.application;

import com.rany.acl.common.base.BaseCommand;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
public class DeleteApplicationCommand extends BaseCommand {
    /**
     * 账号Id
     */
    private Long appId;
}
