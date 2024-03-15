package com.a303.missionms.domain.missionLog;


import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.mission.Mission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
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
public class MissionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_log_id")
	private int missionLogId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mission_id")
	private Mission mission;

	private int memberId;

	private LocalDate missionDate;

	private boolean isFinish;

	public static MissionLog createMissionLog(int missionLogId, Mission mission, int memberId,
		LocalDate localDate, boolean isFinish) {

		MissionLog missionLog = new MissionLog();
		missionLog.setMissionLogId(missionLogId);
		missionLog.setMission(mission);
		missionLog.setMemberId(memberId);
		missionLog.setMissionDate(localDate);
		missionLog.setFinish(isFinish);

		return missionLog;
	}


}
