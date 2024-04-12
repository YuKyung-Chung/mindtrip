package com.a303.htpms.domain.htp.controller;

import com.a303.htpms.domain.htp.service.HtpService;
import com.a303.htpms.global.api.response.BaseResponse;
import com.a303.htpms.global.exception.code.SuccessCode;
import io.micrometer.core.annotation.Timed;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/htps")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class HtpController {
    private final HtpService htpService;

	//    @Operation(summary = "health check")
	@GetMapping("/welcome")
	public ResponseEntity<BaseResponse<String>> welcome() throws IOException {

		log.info("Entering the htp-controller  ");

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its htpms");
	}

    @GetMapping("/v1/analysis")
    public ResponseEntity<BaseResponse<String>> register() {
        String testResult = htpService.htpTestAnalysis();

        return BaseResponse.success(
            SuccessCode.SELECT_SUCCESS,
            testResult
        );
    }
//------------------------- 다른 msa와 통신 -------------------------------

}

