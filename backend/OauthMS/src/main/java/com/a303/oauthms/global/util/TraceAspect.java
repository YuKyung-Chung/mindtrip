package com.a303.oauthms.global.util;

import io.micrometer.tracing.ScopedSpan;
import io.micrometer.tracing.Tracer;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {
    private final Tracer tracer;

    public TraceAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Pointcut("execution(* com.a303.oauthms.domain..*.*(..))")
    public void domainMethods() {}

//    @Pointcut("execution(* com.a303.memberms.global.client..*.*(..))")
//    public void globalMethods() {}

//    @Around("domainMethods() || globalMethods()")
@Around("domainMethods()")
    public Object traceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//		ScopedSpan span = tracer.startScopedSpan("." + joinPoint.getSignature().getName());
        ScopedSpan span = tracer.startScopedSpan(joinPoint.getSignature().getDeclaringTypeName());
        // 메서드 인자 가져오기
        Object[] args = joinPoint.getArgs();
        span.tag("method", joinPoint.getSignature().getName());
        try {
            span.tag("args", Arrays.toString(args));
        }catch (RuntimeException ignored) {
            // string으로 형변환하지 못하는 경우는 예외 ignored 처리
        }
        span.tag("package name", joinPoint.getSignature().getDeclaringTypeName());
        try {
            span.event("Start method");
            // 메서드 실행
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            span.error(throwable); // 예외 정보를 트레이스에 기록
            throw throwable;
        } finally {
            span.event("End method");
            span.end();
        }
    }
}

