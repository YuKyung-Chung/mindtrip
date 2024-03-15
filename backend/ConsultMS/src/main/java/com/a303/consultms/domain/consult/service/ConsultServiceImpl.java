package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.consult.repository.ConsultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService{

    private final ConsultRepository consultRepository;
}
