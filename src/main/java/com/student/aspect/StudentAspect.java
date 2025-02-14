package com.student.aspect;


import com.student.core.Student;
import jakarta.inject.Named;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Named
public class StudentAspect {
    private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Pointcut("execution(* com.student.service.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        LOG.info("before " + joinPoint.getSignature().getName());
    }
    @After("log()")
    public void after(JoinPoint joinPoint) {
        LOG.info("after " + joinPoint.getSignature().getName());
    }

    @Around("log() && args(id)")
    public Object around(ProceedingJoinPoint joinPoint, long id) throws Throwable {
        LOG.info("around after " + joinPoint.getSignature().getName() + " with id " + id);
        Student student = (Student) joinPoint.proceed();
        LOG.info("around before " + student.getFirstName() + " " + student.getSurname() + " with id " + id);
        return student;
    }

}
