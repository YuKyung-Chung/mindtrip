package com.a303.memberms.domain.member.controller;

import com.a303.memberms.domain.member.dto.response.MemberBaseRes;
import com.a303.memberms.domain.member.repository.MemberRepository;
import com.a303.memberms.domain.member.service.MemberService;
import com.a303.memberms.global.api.response.BaseResponse;
import com.a303.memberms.global.exception.code.SuccessCode;
import io.micrometer.core.annotation.Timed;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class MemberController {

	private final MemberService memberService;

	//    @Operation(summary = "health check")
	@GetMapping("/welcome")
	public ResponseEntity<BaseResponse<String>> welcome() throws IOException {

		log.info("Entering the member-controller  ");


		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its memberms");
	}


//------------------------- 다른 msa와 통신 -------------------------------
	//    @Operation(summary = "멤버아이디로 멤버 조회")
	@GetMapping("/v1/{memberId}")
	@Timed(value = "get-memberdto")
	public ResponseEntity<BaseResponse<MemberBaseRes>> getMemberDtoByMemberId(
		@PathVariable("memberId") int memberId
	) throws IOException {

		MemberBaseRes memberBaseRes = memberService.getMemberByMemberId(memberId);

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, memberBaseRes);
	}


}
