package com.example.shorty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "shorty")
@Getter @Setter
public class ShortyProperties {

	private String salt;
	private String baseUrl;
	private ClaimProperties claim;

}
