package com.a303.villagems.domain.village;

import com.a303.villagems.domain.BaseEntity;
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
public class Village extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "village_id")
	private int villageId;

	@Column(length = 20)
	private String name;
}
