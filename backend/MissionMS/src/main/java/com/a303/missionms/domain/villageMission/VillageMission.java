package com.a303.missionms.domain.villageMission;

import com.a303.missionms.domain.mission.Category;
import com.a303.missionms.domain.mission.Mission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class VillageMission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "village_mission_id")
	private int villageMissionId;

	// VillageMission - Mission 연관 관계
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mission_id")
	private Mission mission;

	private int villageId;

}
