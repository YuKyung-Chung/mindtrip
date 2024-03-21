package com.a303.memberms.domain.member.service;

import com.a303.memberms.domain.member.dto.request.MemberStandardLoginReq;
import com.a303.memberms.domain.member.dto.request.MemberStandardRegisterReq;
import com.a303.memberms.domain.member.dto.response.MemberBaseRes;
import com.a303.memberms.global.exception.BaseExceptionHandler;
import java.io.IOException;

public interface MemberService {

	MemberBaseRes getMemberByMemberId(int memberId)
		throws BaseExceptionHandler, IOException;

    String standardLogin(MemberStandardLoginReq memberStandardLoginReq);

    boolean standardRegister(MemberStandardRegisterReq memberStandardRegisterReq);
}
