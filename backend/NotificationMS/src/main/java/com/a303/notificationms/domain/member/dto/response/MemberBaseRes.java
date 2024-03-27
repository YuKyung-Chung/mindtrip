package com.a303.notificationms.domain.member.dto.response;

import com.a303.notificationms.domain.member.Role;
import lombok.Builder;

@Builder
public record MemberBaseRes(
	int memberId,
	String id,
	String password,
	String socialId,
	String nickname,
	Integer villageId,
	int level,
	int missionCount,
	int reportCount,
	Role role
) {

}
