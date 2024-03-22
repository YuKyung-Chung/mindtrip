package com.a303.missionms.domain.dailyMission.service;

import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.dailyMission.repository.DailyMissionRepository;
import com.a303.missionms.domain.member.dto.response.MemberBaseRes;
import com.a303.missionms.domain.mission.Category;
import com.a303.missionms.domain.mission.Mission;
import com.a303.missionms.domain.mission.dto.request.MyTableMissionDTO;
import com.a303.missionms.domain.mission.dto.response.CategoryMissionRes;
import com.a303.missionms.domain.mission.dto.response.MissionBaseRes;
import com.a303.missionms.domain.mission.dto.response.MissionListRes;
import com.a303.missionms.domain.mission.dto.response.MyTableMissionRes;
import com.a303.missionms.domain.mission.repository.MissionRepository;
import com.a303.missionms.domain.missionLog.MissionLog;
import com.a303.missionms.domain.missionLog.repository.MissionLogRepository;
import com.a303.missionms.global.api.response.BaseResponse;
import com.a303.missionms.global.client.MemberClient;
import com.a303.missionms.global.exception.BaseExceptionHandler;
import com.a303.missionms.global.exception.code.ErrorCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DailyMissionServiceImpl implements DailyMissionService {

	@Autowired
	private final EntityManager em;


	private final DailyMissionRepository dailyMissionRepository;
	private final MissionLogRepository missionLogRepository;
	private final MissionRepository missionRepository;
	private final MemberClient memberClient;


	// TODO missionId가 바뀌지는 않지만 만약 없는 미션아이디일 경우의 예외처리도 필요하다.
	@Override
	public List<MyTableMissionRes> putMyTableMissions(int memberId,
		HashMap<Integer, MyTableMissionDTO> myTableMissionDTOMap)
		throws BaseExceptionHandler, IOException {

		// 스케쥴링 시간과 겹치지는 않는지 체크
		if (!isValidRequestTime()) {
			log.error("putMyTableMissions method failed with request-time:{}", LocalDateTime.now());

			throw new BaseExceptionHandler("12시~12시5분은 수정,삭제,추가 요청이 불가합니다.",
				ErrorCode.INVALID_REQUEST_TIME_EXCEPTION);
		}

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

		List<MyTableMissionRes> myTableMissionDTOList = new ArrayList<>();
		for (DailyMission dailyMission : dailyMissions) {
			myTableMissionDTOList.add(
				MyTableMissionRes.builder()
					.missionId(dailyMission.getMission().getMissionId())
					.name(dailyMission.getMission().getName())
					.isFinish(dailyMission.isFinish())
					.build()
			);
		}

		return myTableMissionDTOList;
	}

	@Override
	public List<MyTableMissionRes> getMyTableMissions(int memberId)
		throws BaseExceptionHandler, IOException {

//		BaseResponse<MemberBaseRes> memberBaseRes = memberClient.getMemberDtoByMemberId(memberId);
//		System.out.println(memberBaseRes.getResult());

		List<DailyMission> myTableList = dailyMissionRepository.findByMemberId(memberId);

		List<MyTableMissionRes> myTableMissionDTOList = new ArrayList<>();
		for (DailyMission dailyMission : myTableList) {
			myTableMissionDTOList.add(
				MyTableMissionRes.builder()
					.missionId(dailyMission.getMission().getMissionId())
					.name(dailyMission.getMission().getName())
					.isFinish(dailyMission.isFinish())
					.build()
			);
		}

		return myTableMissionDTOList;
	}

	@Override
	public int completeMission(int memberId, int missionId)
		throws BaseExceptionHandler, IOException {

		// 스케쥴링 시간과 겹치지는 않는지 체크
		if (!isValidRequestTime()) {
			log.error("putMyTableMissions method failed with request-time:{}", LocalDateTime.now());

			throw new BaseExceptionHandler("12시~12시5분은 수정,삭제,추가 요청이 불가합니다.",
				ErrorCode.INVALID_REQUEST_TIME_EXCEPTION);
		}

		DailyMission dailyMission = dailyMissionRepository.findByMemberIdAndMission_MissionId(
			memberId, missionId).orElseThrow(
			() -> {
				log.error("completeMission method memberId:{} missionId:{} 의 레코드가 없습니다.",
					memberId, missionId);

				return new BaseExceptionHandler(
					"해당 멤버:" + memberId + " mission: " + missionId + "의 레코드가 없습니다.",
					ErrorCode.NOT_FOUND_ERROR);
			}
		);

		log.debug("completeMission method memberId:{} missionId:{} success.", memberId, missionId);

		dailyMission.setFinish(true);

		return dailyMission.getDailyMissionId();
	}


	// TODO 멤버의 마을을 고려한 추천이 이루어져야할 것 + 삽입 최적화 필요
	@Override
	public void dailyMissionRecommend() throws BaseExceptionHandler {
		// daily_mission테이블 missionlog에 append
		List<MissionLog> missionLogList = new ArrayList<>();
		LocalDate yesterday = LocalDate.now().minusDays(1);

		List<DailyMission> yesterdayList = dailyMissionRepository.findAll();
		for (DailyMission dailyMission : yesterdayList) {
			MissionLog missionLog = MissionLog.createMissionLog(
				dailyMission.getMission(),
				dailyMission.getMemberId(),
				yesterday,
				dailyMission.isFinish()
			);

			missionLogList.add(missionLog);
		}

		missionLogRepository.saveAll(missionLogList);
		em.createQuery("DELETE FROM DailyMission where 1=1").executeUpdate();


		// 새로운 미션 3개씩 선정해서 넣기
		List<Integer> memberIdList = memberClient.getMemberIdList().getResult();
		if (memberIdList.size() == 0) {
			return;
		}

		List<Mission> missionList = missionRepository.getMissionList();

		List<DailyMission> scheduledList = new ArrayList<>();

		Random random = new Random();
		Set<Integer> pickedSet = new HashSet();

		for (int i = 0; i < memberIdList.size(); i++) {
			int memberId = memberIdList.get(i);
			pickedSet.clear();
			int cnt = 0;
			while (cnt < 3) {
				int index = random.nextInt(missionList.size());
				if (pickedSet.contains(index)) {
					// 다시 뽑아야 함
					continue;
				}
				// 넣어도 됨
				DailyMission dailyMission = DailyMission.createDailyMission(
					missionList.get(index),
					memberId
				);
				scheduledList.add(dailyMission);
				pickedSet.add(index);
				cnt++;
			}
		}

		if (scheduledList.size() != 0) {
			dailyMissionRepository.saveAll(scheduledList);
		}

	}


	//	----------------------------- 일반 메서드 -------------------------------------
	private boolean isValidRequestTime() {
		LocalDateTime now = LocalDateTime.now();
		if (now.getHour() == 0 &&
			now.getMinute() >= 0 && now.getMinute() < 5) {
//			System.out.println("현재 시각은 12시00분00초와 12시05분00초 미만입니다.");
			return false;
		} else {
//			System.out.println("현재 시각은 12시00분00초와 12시05분00초 미만이 아닙니다.");
			return true;
		}
	}
}
