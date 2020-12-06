package com.example.shorty.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Enumerated(EnumType.STRING)
	private ClaimStatus status = ClaimStatus.CREATED;
	private String callback;
	private Boolean allowMultipleAccess = Boolean.TRUE;
	private LocalDateTime creationDate = LocalDateTime.now();
	private LocalDateTime expirationDate;
	private LocalDateTime lastAccessDate;
	private Integer nbAccess = 0;
	@Transient
	private String token;
	@Transient
	private String redirectionUrl;
	@Transient
	private String checkStatusUrl;

}
