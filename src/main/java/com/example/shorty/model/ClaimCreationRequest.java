package com.example.shorty.model;

import javax.validation.constraints.Null;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClaimCreationRequest {

	private String callback;
	@Null
	private Integer expiresIn;
	@Null
	private Boolean allowMultipleAccess;

}
