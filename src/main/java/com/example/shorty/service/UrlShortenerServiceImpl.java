package com.example.shorty.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import com.example.shorty.config.ShortyProperties;
import com.example.shorty.entity.Claim;
import com.example.shorty.entity.ClaimStatus;
import com.example.shorty.model.ClaimCreationRequest;
import com.example.shorty.model.ClaimCreationResponse;
import com.example.shorty.repository.ClaimRepository;
import com.google.common.base.Preconditions;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	private final ClaimRepository claimRepository;
	private final ShortyProperties shortyProperties;
	private final Hashids hashids;

	public UrlShortenerServiceImpl(ClaimRepository claimRepository, ShortyProperties shortyProperties) {
		this.claimRepository = claimRepository;
		this.shortyProperties = shortyProperties;
		this.hashids = new Hashids(shortyProperties.getSalt());
	}

	@Override
	public ClaimCreationResponse createClaim(ClaimCreationRequest claimCreationRequest) {
		validateClaimCreation(claimCreationRequest);
		Claim claim = new Claim();
		claim.setCallback(claimCreationRequest.getCallback());
		if (claimCreationRequest.getExpiresIn() != null) {
			claim.setExpirationDate(LocalDateTime.now().plusDays(claimCreationRequest.getExpiresIn()));
		} else {
			claim.setExpirationDate(LocalDateTime.now().plusDays(shortyProperties.getClaim().getDefaultValidityDuration()));
		}
		if (claimCreationRequest.getAllowMultipleAccess() != null) {
			claim.setAllowMultipleAccess(claimCreationRequest.getAllowMultipleAccess());
		}
		claimRepository.save(claim);

		String token = hashids.encode(claim.getId());
		String redirectionUrl = shortyProperties.getBaseUrl() + token;
		String checkStatusUrl = shortyProperties.getBaseUrl() + "claims/" + token;

		return new ClaimCreationResponse(token, redirectionUrl, checkStatusUrl);
	}

	@Override
	public String getRedirection(String token) {
		Optional<Claim> optionalClaim = getClaimByToken(token);
		if (optionalClaim.isPresent()) {
			Claim claim = optionalClaim.get();
			if (isClaimExpired(claim)) {
				return shortyProperties.getClaim().getExpiredPage() + token;
			} else {
				claim.setStatus(ClaimStatus.ACCESSED);
				claim.setLastAccessDate(LocalDateTime.now());
				claim.setNbAccess(claim.getNbAccess() + 1);
				claimRepository.save(claim);
				return claim.getCallback();
			}
		} else {
			return shortyProperties.getClaim().getUnknownPage() + token;
		}
	}

	@Override
	public Optional<Claim> getClaimByToken(String token) {
		long[] decodedIds = hashids.decode(token);
		if (decodedIds.length == 0) {
			return Optional.empty();
		}
		return claimRepository.findById(decodedIds[0]);
	}

	private void validateClaimCreation(ClaimCreationRequest claimCreationRequest) {
		Preconditions.checkArgument(claimCreationRequest != null, "Body should not be null");
		Preconditions.checkArgument(claimCreationRequest.getCallback() != null, "'callback' argument is required");
		if (claimCreationRequest.getExpiresIn() != null) {
			Preconditions.checkArgument(claimCreationRequest.getExpiresIn() > 0
							&& claimCreationRequest.getExpiresIn() <= shortyProperties.getClaim().getMaxValidityDuration(),
					String.format("'expiresIn' argument should be an integer between 1 and %d", shortyProperties.getClaim().getMaxValidityDuration()));
		}
	}

	private boolean isClaimExpired(Claim claim) {
		if (ClaimStatus.EXPIRED.equals(claim.getStatus())) {
			return true;
		}
		if (ClaimStatus.ACCESSED.equals(claim.getStatus()) && Boolean.FALSE.equals(claim.getAllowMultipleAccess())) {
			return true;
		}
		return LocalDateTime.now().isAfter(claim.getExpirationDate());
	}

}
