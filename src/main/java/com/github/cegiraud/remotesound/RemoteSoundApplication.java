package com.github.cegiraud.remotesound;

import com.github.cegiraud.remotesound.config.RemoteSoundApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RemoteSoundApplicationProperties.class)
public class RemoteSoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteSoundApplication.class, args);
	}
}
