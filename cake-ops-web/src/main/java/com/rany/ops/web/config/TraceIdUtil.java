package com.rany.ops.web.config;

import java.util.UUID;

/**
 * @author zhongshengwang
 */
public class TraceIdUtil {

    public static String getTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
