package com.example.shorty.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClaimProperties {

	private String expiredPage;
	private String unknownPage;
	private Integer defaultValidityDuration = 30;
	private Integer maxValidityDuration = 365;

}
