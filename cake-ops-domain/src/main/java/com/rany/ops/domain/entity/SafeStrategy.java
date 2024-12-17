package com.rany.ops.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.ops.common.enums.DeleteStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 安全策略
 *
 * @author zhongshengwang
 * @description 安全策略
 * @date 2022/12/29 22:03
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SafeStrategy extends BaseEntity<Long> {

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
    private Date blockAt;
    private Date expiredAt;

    public Boolean isBlocked() {
        return this.blockAt != null
                && DateUtil.date().before(blockAt);
    }

    public Boolean isExpired() {
        return this.expiredAt != null
                && DateUtil.date().before(expiredAt);
    }

    public void delete(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
        this.isDeleted = DeleteStatusEnum.YES.getValue();
    }

    public void modify(String user) {
        this.gmtModified = DateUtil.date();
        this.modifier = user;
    }

    public void init(String user) {
        this.gmtCreate = DateUtil.date();
        this.creator = user;
        this.modifier = user;
    }


    public SafeStrategy(Long accountId, String loginStrategy, String authCode, String authValue) {
        this.accountId = accountId;
        this.loginStrategy = loginStrategy;
        this.authCode = authCode;
        this.authValue = authValue;
    }
}
