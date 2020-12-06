package com.example.shorty.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClaimCreationResponse {

	private final String token;
	private final String redirectionUrl;
	@JsonIgnore
	private final String checkStatusUrl;

}
