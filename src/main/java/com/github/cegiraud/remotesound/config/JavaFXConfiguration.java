package com.github.cegiraud.remotesound.config;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * Created by cegiraud on 24/06/2016.
 */
@Configuration
public class JavaFXConfiguration {

    @Bean
    public JFXPanel jfxPanel() {
        return new JFXPanel();
    }

}