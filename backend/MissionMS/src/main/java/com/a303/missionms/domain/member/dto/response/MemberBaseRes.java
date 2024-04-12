package com.a303.missionms.domain.member.dto.response;

import com.a303.missionms.domain.member.Role;
import lombok.Builder;

@Builder
public record MemberBaseRes(
	int memberId,
	String id,
	String password,
	String socialId,
	String nickname,
	Integer villageId,
	String villageName,
	int level,
	int missionCount,
	int reportCount,
	Role role
) {

}
