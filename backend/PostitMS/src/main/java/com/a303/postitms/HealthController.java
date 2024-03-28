package com.a303.postitms;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/api/postits")
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
