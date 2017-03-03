package com.github.cegiraud.remotesound.config;

import com.github.cegiraud.remotesound.types.SoundActionType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties
public class RemoteSoundApplicationProperties {
    private String httpProxy;

    private Sound sound;

    public static class Sound {
        private Map<SoundActionType, String> control;

        public Map<SoundActionType, String> getControl() {
            return control;
        }

        public void setControl(Map<SoundActionType, String> control) {
            this.control = control;
        }
    }

    public String getHttpProxy() {
        return httpProxy;
    }

    public void setHttpProxy(String httpProxy) {
        this.httpProxy = httpProxy;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
}