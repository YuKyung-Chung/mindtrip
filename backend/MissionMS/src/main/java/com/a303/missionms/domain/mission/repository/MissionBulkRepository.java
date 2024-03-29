package com.a303.missionms.domain.mission.repository;

import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.mission.dto.response.DailyMissionBaseRes;
import com.a303.missionms.domain.mission.dto.response.MissionLogRes;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MissionBulkRepository {

	private final JdbcTemplate jdbcTemplate;

	public void deleteAllDailyMission() {
		String sql = "delete from daily_mission where 1=1";

		jdbcTemplate.batchUpdate(sql);
	}


	public void saveAllMissionLog(List<MissionLogRes> missionLogList) {
		String sql = "insert into mission_log (is_finish, member_id, mission_id, mission_date) ";
		sql += "values (?, ?, ?, ?)";

		jdbcTemplate.batchUpdate(sql,
			missionLogList,
			missionLogList.size(),
			(PreparedStatement ps, MissionLogRes missionLog) -> {
				ps.setBoolean(1, missionLog.isFinish());
				ps.setInt(2, missionLog.memberId());
				ps.setInt(3, missionLog.missionId());
				ps.setString(4,
					missionLog.missionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		);
	}

	public void saveAllDailyMission(List<DailyMissionBaseRes> dailyMissionList) {
		String sql = "insert into daily_mission (mission_id, member_id, is_finish) ";
		sql += "values (?, ?, ?)";

		jdbcTemplate.batchUpdate(sql,
			dailyMissionList,
			dailyMissionList.size(),
			(PreparedStatement ps, DailyMissionBaseRes dailymission) -> {
				ps.setInt(1, dailymission.missionId());
				ps.setInt(2, dailymission.memberId());
				ps.setBoolean(3, dailymission.isFinish());
			}
		);
	}

}
