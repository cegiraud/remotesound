package com.github.cegiraud.remotesound.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class RemoteSoundApplicationProperties {
    private String httpProxy;

    public String getHttpProxy() {
        return httpProxy;
    }

    public void setHttpProxy(String httpProxy) {
        this.httpProxy = httpProxy;
    }
}