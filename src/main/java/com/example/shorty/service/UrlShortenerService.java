package com.example.shorty.service;

import java.util.Optional;

import com.example.shorty.entity.Claim;
import com.example.shorty.model.ClaimCreationRequest;
import com.example.shorty.model.ClaimCreationResponse;

public interface UrlShortenerService {

	ClaimCreationResponse createClaim(ClaimCreationRequest claimCreationRequest);

	String getRedirection(String token);

	Optional<Claim> getClaimByToken(String token);
}
