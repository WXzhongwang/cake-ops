package com.rany.acl.common.exception;

import com.cake.framework.common.exception.CommonReturnCode;
import lombok.Getter;

/**
 * BusinessException
 *
 * @author zhongshengwang
 * @description BusinessException
 * @date 2022/3/24 1:55 下午
 * @email zhongshengwang@shuwen.com
 */

@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(CommonReturnCode returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
        this.message = returnCode.getMessage();
    }


    public BusinessException(BusinessErrorMessage returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
        this.message = returnCode.getMessage();
    }
}
