package com.example.shorty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClaimCreationResponse {

	private final String token;
	private final String redirectionUrl;
	private final String checkStatusUrl;

}
