package com.a303.missionms.domain.dailyMission.service;

import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.dailyMission.repository.DailyMissionRepository;
import com.a303.missionms.domain.mission.Category;
import com.a303.missionms.domain.mission.Mission;
import com.a303.missionms.domain.mission.dto.request.MyTableMissionDTO;
import com.a303.missionms.domain.mission.dto.response.CategoryMissionRes;
import com.a303.missionms.domain.mission.dto.response.MissionBaseRes;
import com.a303.missionms.domain.mission.dto.response.MissionListRes;
import com.a303.missionms.domain.mission.repository.MissionRepository;
import com.a303.missionms.global.exception.BaseExceptionHandler;
import com.a303.missionms.global.exception.code.ErrorCode;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import java.io.IOException;
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
public class DailyMissionServiceImpl implements DailyMissionService {

	private final DailyMissionRepository dailyMissionRepository;
	private final MissionRepository missionRepository;


	// TODO missionId가 바뀌지는 않지만 만약 없는 미션아이디일 경우의 예외처리도 필요하다.
	@Override
	public List<MyTableMissionDTO> putMyTableMissions(int memberId,
		HashMap<Integer, MyTableMissionDTO> myTableMissionDTOMap)
		throws BaseExceptionHandler, IOException {

		// 기존 저장된 유저의 마이테이블 미션들을 엔티티 형태로 가져온다.
		List<DailyMission> dailyMissions = dailyMissionRepository.findByMemberId(
			memberId);

		// 기존 마이테이블 미션들 중에 수행된것 제외, 넣으려는 것과 동일한 것 제외하고 남은 애들은 이번에 교체되는 애들
		// 따라서 해당 엔티티는 삭제한다.
		for (int i = dailyMissions.size() - 1; i >= 0; i--) {
			DailyMission temp = dailyMissions.get(i);

			// 기존 마이테이블 미션들 중에 수행된것 제외, 넣으려는 것과 동일한 것 제외하고
			if (temp.isFinish() || myTableMissionDTOMap.containsKey(
				temp.getMission().getMissionId())) {
				dailyMissions.remove(i);
				myTableMissionDTOMap.remove(temp.getMission().getMissionId());
			}
		}

		// 남은 애들은 이번에 교체되는 애들. 따라서 해당 엔티티는 삭제한다.
		if (dailyMissions.size() != 0) {
			dailyMissionRepository.deleteAll(dailyMissions); // TODO deleteAll 최적화 필요
		}
		// 남은 애들은 이번에 들어가는 애들이다. 따라서 해당 객체는 엔티티화한다.
		if (myTableMissionDTOMap.size() != 0) {
			List<Integer> missionIdList = new ArrayList<>(myTableMissionDTOMap.keySet());
			List<Mission> missions = missionRepository.getMissionsByMissionIdIn(
				missionIdList);
			Map<Integer, Mission> missionHashMap = new HashMap<>();
			for (Mission mission : missions) {
				missionHashMap.put(mission.getMissionId(), mission);
			}

			for (Entry<Integer, MyTableMissionDTO> entry : myTableMissionDTOMap.entrySet()) {
				Mission mission = missionHashMap.get(entry.getKey());

				DailyMission dailyMission = DailyMission.createDailyMission(mission, memberId);
				dailyMission.setFinish(entry.getValue().isFinish());
				dailyMissionRepository.save(dailyMission); // TODO batch insert 최적화 필요
			}
		}

		// 반환값은 업데이트 이후 마이테이블
		dailyMissions = dailyMissionRepository.findByMemberId(
			memberId);

		List<MyTableMissionDTO> myTableMissionDTOList = new ArrayList<>();
		for (DailyMission dailyMission : dailyMissions) {
			myTableMissionDTOList.add(
				MyTableMissionDTO.builder()
					.missionId(dailyMission.getMission().getMissionId())
					.isFinish(dailyMission.isFinish())
					.build()
			);
		}

		return myTableMissionDTOList;
	}

	@Override
	public List<MyTableMissionDTO> getMyTableMissions(int memberId)
		throws BaseExceptionHandler, IOException {

		List<DailyMission> myTableList = dailyMissionRepository.findByMemberId(memberId);

		List<MyTableMissionDTO> myTableMissionDTOList = new ArrayList<>();
		for (DailyMission dailyMission : myTableList) {
			myTableMissionDTOList.add(
				MyTableMissionDTO.builder()
					.missionId(dailyMission.getMission().getMissionId())
					.isFinish(dailyMission.isFinish())
					.build()
			);
		}

		return myTableMissionDTOList;
	}

	@Override
	public int completeMission(int memberId, int missionId)
		throws BaseExceptionHandler, IOException {

		DailyMission dailyMission = dailyMissionRepository.findByMemberIdAndMission_MissionId(
			memberId, missionId).orElseThrow(
			() -> new BaseExceptionHandler(
				"해당 멤버:" + memberId + " mission: " + missionId + "의 레코드가 없습니다.",
				ErrorCode.NOT_FOUND_ERROR)
		);

		dailyMission.setFinish(true);

		return dailyMission.getDailyMissionId();
	}
}
