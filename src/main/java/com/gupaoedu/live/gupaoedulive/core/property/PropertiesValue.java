package com.gupaoedu.live.gupaoedulive.core.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValue {
    /**
     * 头像路径
     */
    @Value("${avatar.file.upload.path}")
    public String avatarPath;


}
