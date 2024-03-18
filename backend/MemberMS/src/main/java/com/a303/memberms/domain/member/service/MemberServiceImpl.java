package com.a303.memberms.domain.member.service;

import com.a303.memberms.domain.member.Member;
import com.a303.memberms.domain.member.dto.response.MemberBaseRes;
import com.a303.memberms.domain.member.repository.MemberRepository;
import com.a303.memberms.global.exception.BaseExceptionHandler;
import com.a303.memberms.global.exception.code.ErrorCode;
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
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public MemberBaseRes getMemberByMemberId(int memberId)
		throws BaseExceptionHandler, IOException {

		Member member = memberRepository.findByMemberId(memberId).orElseThrow(
			() -> new BaseExceptionHandler(
				ErrorCode.NOT_FOUND_USER_EXCEPTION
			)
		);

		return MemberBaseRes.builder()
			.memberId(memberId)
			.id(member.getId())
			.password(member.getPassword())
			.socialId(member.getSocialId())
			.nickname(member.getNickname())
			.villageId(member.getVillageId())
			.level(member.getLevel())
			.missionCount(member.getMissionCount())
			.reportCount(member.getReportCount())
			.role(member.getRole())
			.build();
	}
}
