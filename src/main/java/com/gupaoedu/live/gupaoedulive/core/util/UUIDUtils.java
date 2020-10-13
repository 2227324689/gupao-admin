package com.gupaoedu.live.gupaoedulive.core.util;

import java.util.UUID;

/**
 * uuid工具类
 * @author link 1119132339@qq.com
 */
public abstract class UUIDUtils {

    /**
     * @return 获取uuid字符串
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
