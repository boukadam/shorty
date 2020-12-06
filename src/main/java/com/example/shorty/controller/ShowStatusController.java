package com.example.shorty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class ShowStatusController {

	@GetMapping("/expired")
	public String showExpiredClaimPage(@RequestParam String token) {
		return String.format("The token '%s' is expired.", token);
	}

	@GetMapping("/unknown")
	public String showUnknownClaimPage(@RequestParam String token) {
		return String.format("The token '%s' is unknown.", token);
	}

}
