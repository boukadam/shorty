package com.example.shorty.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.shorty.service.UrlShortenerService;

@RestController
public class RedirectionController {

	private final UrlShortenerService urlShortenerService;

	public RedirectionController(UrlShortenerService urlShortenerService) {
		this.urlShortenerService = urlShortenerService;
	}

	@GetMapping("/{token}")
	public ResponseEntity<Object> redirect(@PathVariable String token) {
		return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, urlShortenerService.getRedirection(token)).build();
	}

}
