package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * AOP
 * Aspect Oriented Programming
 * 공통 관심 사항 (cross-cutting concern) vs 핵심 관심 사항 (core concern) 분리
 * - 공통 관심 사항 : 시간 측정 로직
 * - 핵심 관심 사항 : 비즈니스 로직 (로그인, 회원 조회 등)
 *
 * 시간 측정 로직을 한 군데에 다 모아놓고, 시간 측정 로직이 필요한 핵심 관심 사항에 각각 적용
 *
 * 메소드 호출할 때마다 인터셉트를 해서 데이터, 파라미터, 상태 등을 조회 및 수정까지 가능함
 *
 * AOP 적용 전
 * Controller -> Service 로 직접 요청을 주는 방식
 *
 * AOP 적용 후
 * Controller -> 가짜 Service (= 프록시 Service) - ( joinPoint.proceed() ) -> 실제 Service
 * 가짜 Service 스프링빈을 실제 Service 스프링빈 앞에 세워두고 나서, 가짜 스프링빈 동작이 끝나면 joinPoint.proceed 를 통해서 실제 호출이 일어남
 * DI 를 사용하기 때문에 프록시 AOP 가 가능해짐
 *
 */

@Aspect
public class TimeTraceAop {
    // @Around 를 통해서 해당 execute 가 적용될 곳을 지정할 수 있음
    @Around("execution(* hello.hellospring..*(..))" )
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("end: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
