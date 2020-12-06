package com.example.shorty.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.shorty.entity.Claim;
import com.example.shorty.model.ClaimCreationRequest;
import com.example.shorty.model.ClaimCreationResponse;
import com.example.shorty.service.UrlShortenerService;

@RestController
@RequestMapping("/claims")
public class ClaimController {

	private final UrlShortenerService urlShortenerService;

	public ClaimController(UrlShortenerService urlShortenerService) {
		this.urlShortenerService = urlShortenerService;
	}

	@PostMapping
	public ResponseEntity<ClaimCreationResponse> createClaim(@RequestBody ClaimCreationRequest claimCreationRequest) {
		ClaimCreationResponse claim = urlShortenerService.createClaim(claimCreationRequest);
		UriComponents location = UriComponentsBuilder.fromPath(claim.getCheckStatusUrl()).build();
		return ResponseEntity.created(location.toUri()).body(claim);
	}

	@GetMapping("/{token}")
	public ResponseEntity<Claim> getClaimInfos(@PathVariable String token) {
		Optional<Claim> claim = urlShortenerService.getClaimByToken(token);
		return claim.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

}
