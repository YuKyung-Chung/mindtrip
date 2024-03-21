package com.a303.memberms.domain.member.service;

import com.a303.memberms.domain.member.dto.response.MemberBaseRes;
import com.a303.memberms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface MemberService {

	MemberBaseRes getMemberByMemberId(int memberId)
		throws BaseExceptionHandler, IOException;


}
