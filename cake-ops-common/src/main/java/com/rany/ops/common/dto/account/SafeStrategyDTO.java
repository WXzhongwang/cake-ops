package com.rany.ops.common.dto.account;

import com.rany.ops.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 登录方式
 *
 * @author zhongshengwang
 * @description 登录方式
 * @date 2023/1/7 21:32
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SafeStrategyDTO extends DTO {

    /**
     * 主键
     */
    private String id;

    private Long accountId;
    /**
     * 登录策略
     */
    private String loginStrategy;
    /**
     * 授权码
     */
    private String authCode;
    /**
     * authValue
     */
    private String authValue;
    /**
     * 冻结时间
     */
    private Date blockAt;
    /**
     * 过期时间
     */
    private Date expiredAt;

    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
    private String isDeleted;
}
