package com.github.cegiraud.remotesound.config;


import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * Created by cegiraud on 24/06/2016.
 */
@Configuration
public class RestTemplateConfiguration {


    private final RemoteSoundApplicationProperties applicationProperties;

    public RestTemplateConfiguration(RemoteSoundApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public RestTemplate template() throws Exception {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        if(!StringUtils.isEmpty(applicationProperties.getHttpProxy())) {
            httpClientBuilder.setProxy(HttpHost.create(applicationProperties.getHttpProxy()));
        }
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClientBuilder.build());
        return new RestTemplate(requestFactory);
    }
}
