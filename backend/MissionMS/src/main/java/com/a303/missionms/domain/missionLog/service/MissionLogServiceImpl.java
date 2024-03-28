package com.a303.missionms.domain.missionLog.service;

import com.a303.missionms.domain.mission.Mission;
import com.a303.missionms.domain.mission.dto.response.CalenderDayMissionRes;
import com.a303.missionms.domain.mission.dto.response.MissionLogBaseRes;
import com.a303.missionms.domain.mission.dto.response.MissionReportRes;
import com.a303.missionms.domain.mission.dto.response.MyTableMissionRes;
import com.a303.missionms.domain.missionLog.MissionLog;
import com.a303.missionms.domain.missionLog.repository.MissionLogRepository;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MissionLogServiceImpl implements MissionLogService {

	private final MissionLogRepository missionLogRepository;


	@Override
	public MissionReportRes getMissionReport(int memberId, int year, int month) {

		Map<Integer, List<MyTableMissionRes>> dayMap = new HashMap<>();
		Map<Integer, CalenderDayMissionRes> calenderDayMissionResMap = new HashMap<>();
		int[] daySuccessCountArray = new int[33];

		int percent = 0;

		List<MissionLogBaseRes> missionLogBaseResList = missionLogRepository.getMyReport(memberId,
			year, month);
		for (MissionLogBaseRes missionLog : missionLogBaseResList) {

			int day = missionLog.missionDate().getDayOfMonth();

			if (missionLog.isFinish()) {
				percent++;
				daySuccessCountArray[day] += 1;
			}

			MyTableMissionRes myTableMissionRes = MyTableMissionRes.builder()
				.missionId(missionLog.missionBaseRes().missionId())
				.name(missionLog.missionBaseRes().name())
				.isFinish(missionLog.isFinish())
				.build();
			if (dayMap.containsKey(day)) {
				dayMap.get(day).add(myTableMissionRes);
			} else {
				List<MyTableMissionRes> temp = new ArrayList<>();
				temp.add(myTableMissionRes);
				dayMap.put(day, temp);
			}
		}
		percent *= 100;
		percent /= missionLogBaseResList.size();

		for (Entry<Integer, List<MyTableMissionRes>> entry : dayMap.entrySet()) {
			int day = entry.getKey();

			CalenderDayMissionRes calenderDayMissionRes = CalenderDayMissionRes.builder()
				.count(daySuccessCountArray[day])
				.missions(entry.getValue())
				.build();

			calenderDayMissionResMap.put(day, calenderDayMissionRes);
		}

		System.out.println(calenderDayMissionResMap);

		return MissionReportRes.builder()
			.percent(percent)
			.year(year)
			.month(month)
			.missionCalender(calenderDayMissionResMap)
			.build();
	}
}
