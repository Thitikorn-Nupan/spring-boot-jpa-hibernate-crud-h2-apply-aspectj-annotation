package com.ttknpdev.springbootcrudh2aopbasic.aspect;

import com.ttknpdev.springbootcrudh2aopbasic.entity.Robot;
import com.ttknpdev.springbootcrudh2aopbasic.exception.handler.ResourceNotFound;
import com.ttknpdev.springbootcrudh2aopbasic.log.Logging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// Aspect for logging execution.
@Aspect
@Component
public class RobotLoggingAspect extends Logging {

    // when use @Around
    // in browser can't show data
    // but console can show data
    //    @Around("execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.*(..))") // accesses all method
    //    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    //        robotLoggingAspect.debug("***** logAround running ..... **** before invokation of the method " + joinPoint.getSignature().getName()+" *****");
    //        joinPoint.proceed();
    //        robotLoggingAspect.debug("***** logAround ending ..... **** after invokation of the method " + joinPoint.getSignature().getName()+" *****");
    //    }

    @Before("execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.create(..))")
    public void logBeforeCreate(JoinPoint joinPoint) {
        robotLoggingAspect.debug("logBeforeCreate running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+" , Method: {}  "+ joinPoint.getSignature().getName()+" , Return: {} "+Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.read(..))")
    public void logBeforeRead(JoinPoint joinPoint) {
        robotLoggingAspect.debug("logBeforeRead running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+" , Method: {}  "+ joinPoint.getSignature().getName()+" , Return: {} "+Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.update(..))")
    public void logBeforeUpdate(JoinPoint joinPoint) {
        robotLoggingAspect.debug("logBeforeUpdate running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+" , Method: {}  "+ joinPoint.getSignature().getName()+" , Return: {} "+Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.reads())", returning = "robots")
    public void logAfterReads(JoinPoint joinPoint,List<Robot> robots) {
        robotLoggingAspect.debug("logAfterReads running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+" , Method: {}  "+ joinPoint.getSignature().getName()+" , Return: {} "+robots);
    }

    @After("execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.create(..))")
    public void logAfterCreate(JoinPoint joinPoint) {
        robotLoggingAspect.debug("logAfterCreate running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+
                " , Method: {}  "+ joinPoint.getSignature().getName()+
                " , Return: {} "+Arrays.toString(joinPoint.getArgs()));
        //  Enter: {} com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao ,
        //  Method: {}  creaet ,
        //  Return: {} [Robot{id=null, name='RX-91', status='enable', price=999999.0, build='2566-07-14 19:01:39'}]
    }

    /*
    *** when @Before logging dbms is not worked
    2566-07-14 19:01:39 [http-nio-8080-exec-1] DEBUG RobotLoggingAspect:36 - logBefore running .....
    2566-07-14 19:01:39 [http-nio-8080-exec-1] DEBUG RobotLoggingAspect:37 - Enter: {} com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao , Method: {}  creaet , Return: {} [Robot{id=null, name='RX-91', status='enable', price=999999.0, build='2566-07-14 19:01:39'}]
    *** look at attribute id it still null
    *** when @After logging dbms was working
    2566-07-14 19:01:39 [http-nio-8080-exec-1] DEBUG RobotDao:23 - robot {} : valid data
    2566-07-14 19:01:39 [http-nio-8080-exec-1] DEBUG RobotLoggingAspect:42 - logAfter running .....
    2566-07-14 19:01:39 [http-nio-8080-exec-1] DEBUG RobotLoggingAspect:43 - Enter: {} com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao , Method: {}  creaet , Return: {} [Robot{id=4, name='RX-91', status='enable', price=999999.0, build='2566-07-14 19:01:39'}]
    *** look at attribute id it is 4
    */

    @AfterReturning(value = "execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.read(..))",returning = "robot")
    public void logAfterRead(JoinPoint joinPoint , Robot robot) {
        robotLoggingAspect.debug("logAfterRead running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+
                " , Method: {}  "+ joinPoint.getSignature().getName()+
                " , Return: {} "+robot);
        // Again @Before annotation work before your method will work
        // @After annotation work after your method done
    }

    @AfterReturning(value = "execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.update(..))",returning = "robot")
    public void logAfterUpdate(JoinPoint joinPoint , Robot robot) {
        robotLoggingAspect.debug("logAfterUpdate running .....");
        robotLoggingAspect.debug("Enter: {} "+joinPoint.getSignature().getDeclaringTypeName()+
                " , Method: {}  "+ joinPoint.getSignature().getName()+
                " , Return: {} "+robot);
    }

    @AfterThrowing(value = "execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.reads())",throwing = "throwable") // @AfterThrowing – Run after the method throws an exception
    public void logAfterReads(JoinPoint joinPoint , Throwable throwable) {
        robotLoggingAspect.debug("logAfterReads running .....");
        robotLoggingAspect.debug("Exception in : {} "+joinPoint.getSignature().getDeclaringTypeName()+
                " , Method: {}  "+ joinPoint.getSignature().getName()+
                " , Return: {} "+throwable);
    }

    @AfterThrowing(value = "execution(* com.ttknpdev.springbootcrudh2aopbasic.dao.RobotDao.delete(..))",throwing = "throwable")
    public void logAfterDelete(JoinPoint joinPoint , Throwable throwable) {
        robotLoggingAspect.debug("logAfterDelete running .....");
        robotLoggingAspect.debug("Exception in : {} "+joinPoint.getSignature().getDeclaringTypeName()+
                " , Method: {}  "+ joinPoint.getSignature().getName()+
                " , Return: {} "+throwable);
    }
}
