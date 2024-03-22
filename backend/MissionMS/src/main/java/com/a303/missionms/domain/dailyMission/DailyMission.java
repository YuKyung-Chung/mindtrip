package com.a303.missionms.domain.dailyMission;


import com.a303.missionms.domain.mission.Mission;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class DailyMission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "daily_mission_id")
	private int dailyMissionId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "mission_id")
	private Mission mission;

	private int memberId;

	private boolean isFinish;

	public static DailyMission createDailyMission(Mission mission, int memberId) {
		LocalDate now = LocalDate.now();

		DailyMission dailyMission = new DailyMission();
		dailyMission.setMission(mission);
		dailyMission.setMemberId(memberId);
		dailyMission.setFinish(false);

		return dailyMission;
	}



}
