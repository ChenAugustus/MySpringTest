package com.example.aop.aopxml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cyx
 * @date 2020/12/16
 */

// 增强类
@Component
@Aspect  // 生成代理对象，无论是用xml配置还是用完全注解方式，写了通知方法的增强类必须加上@Aspect
public class UserProxy {

    // 在增强类的里面，在作为通知方法上面添加通知类型注解，
    // 使用切入点表达式execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )配置
    // 切入点表达式作用：知道对哪个类里面的哪个方法进行增强


    // 相同的切入点抽取，相当于将包路径用函数名代替简化
    @Pointcut(value = "execution(public * com.example.aop.aopxml.User.add(..))")
    public void pointdemo() {

    }

    // 前置通知
    @Before(value = "pointdemo()")
    public void before() {
        System.out.println("before......");
    }

    // 后置通知（返回通知）
    @AfterReturning(value = "execution(public * com.example.aop.aopxml.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning.........");
    }

    // 最终通知
    @After(value = "execution(public * com.example.aop.aopxml.User.add(..))")
    public void after() {
        System.out.println("after.........");
    }

    // 异常通知
    @AfterThrowing(value = "execution(public * com.example.aop.aopxml.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }

    // 环绕通知
    @Around(value = "execution(public * com.example.aop.aopxml.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");

        // 被增强的方法执行
        proceedingJoinPoint.proceed();

        System.out.println("环绕之后.........");
    }
}
