package com.rany.ops.api.command.application;

import com.rany.ops.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新应用基本信息
 *
 * @author zhongshengwang
 * @description 更新应用基本信息
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifyApplicationCommand extends BaseCommand {
    private String id;
    private String appName;
    private String authType;
}
