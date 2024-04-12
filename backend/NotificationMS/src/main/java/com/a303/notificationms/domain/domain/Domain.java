package com.a303.notificationms.domain.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "domain_id")
	private int domainId;

	@Column(length = 50)
	private String name;

}
