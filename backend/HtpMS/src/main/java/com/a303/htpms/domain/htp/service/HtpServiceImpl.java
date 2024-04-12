package com.a303.htpms.domain.htp.service;


import org.springframework.stereotype.Service;

@Service
public class HtpServiceImpl implements HtpService {

    @Override
    public String htpTestAnalysis() {
        String[] villages = new String[] {
            "빨강",
            "주황",
            "노랑",
            "초록",
            "파랑",
            "군청",
            "보라"
        };

        return villages[((int) (Math.random() * 7))];
    }
}
