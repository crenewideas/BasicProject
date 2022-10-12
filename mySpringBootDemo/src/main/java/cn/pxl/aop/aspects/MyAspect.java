package cn.pxl.aop.aspects;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class MyAspect {

    @Pointcut("execution(* cn.pxl.aop.entity.TargetEntity.sayHello())")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println("before 方法执行");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after 方法执行");
    }

    @SneakyThrows
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp){
        System.out.println("around before 方法执行");
        Object proceed = pjp.proceed();
        System.out.println("around after 方法执行");
        return proceed;
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("after 方法执行");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing 方法执行");
    }
}
