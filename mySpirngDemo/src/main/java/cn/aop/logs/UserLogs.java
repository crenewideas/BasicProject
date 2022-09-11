package cn.aop.logs;

//记录日志工具类
import cn.aop.service.ValidateService;
import cn.aop.service.impl.ValidateServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserLogs {

    //@DeclareParents用于给被增强的类添加新的方法
    //value 表示某个类及其子类。defaultImpl表示默认使用的实现类。
    @DeclareParents(value = "cn.aop.service.UserService+",defaultImpl = ValidateServiceImpl.class)
    private ValidateService validateService;

    @Pointcut("execution(* cn.aop.service.*.*(..))")
    public void onePointCut(){

    }

    @Before("onePointCut() && this(validateService)")
    public void doBefore(ValidateService validateService){
        System.out.println("前置方法执行！");
        validateService.checkUser();

    }

//    @Before("onePointCut() && this(validateService)")
//    public void doBefore(ValidateService validateService){
//        validateService.checkUser();
//        System.out.println("前置方法执行！");
//    }

    @AfterReturning("onePointCut()")
    public void doAfter(){
        System.out.println("后置方法执行！");
    }

    //最终通知
    @After("onePointCut()")
    public void doAfterReturning(){
        System.out.println("最终通知执行！");
    }

    @Around("onePointCut()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("执行切入点方法前记录日志");
        //获取方法执行的参数
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println(args);
        try {
            proceedingJoinPoint.proceed(args);
            System.out.println("正常执行切入点方法后记录日志");
        } catch (Throwable throwable) {
            System.out.println("执行切入点方法产生异常后记录日志");
            throwable.printStackTrace();
        }finally {
            System.out.println("无论切入点方法执行是否有异常都记录日志");
        }


    }


}
