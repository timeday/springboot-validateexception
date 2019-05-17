package com.springboot.validate.springbootvalidateexception.constant;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.validate.springbootvalidateexception.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;

/**
 * 此处本来准备 动态修改 @JsonView 注解中的value 值 但是修改后还是不生效
 */
@Aspect
@Component
public class HttpRequestAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestAspect.class);

    public static long startTime;
    public static long endTime;

    private  Class<?>[] valueinfo;

    /*@PointCut注解表示表示横切点，哪些方法需要被横切*/
    /*切点表达式*/
    @Pointcut("execution(public * com.springboot.validate.springbootvalidateexception.controller.*.*(..))")
    /*切点签名*/
    public void print() {

    }

    /*@Before注解表示在具体的方法之前执行*/
    @Before("print()")
    public void before(JoinPoint joinPoint) {
        log.info("前置切面before……");
        startTime = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();   //这个方法取客户端ip"不够好"
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求url=" + requestURI + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
        Class<?>[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass()
                    .getMethod(joinPoint.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        JsonView ma = method.getAnnotation(JsonView.class);
        valueinfo=ma.value();
        System.out.println(ma.value());
    }

    /*@After注解表示在方法执行之后执行*/
    @After("print()")
    public void after() {
        endTime = System.currentTimeMillis() - startTime;
        log.info("后置切面after……");
    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) throws Exception {

        if(Objects.nonNull(object) && object instanceof RestResultWrapper){
            RestResultWrapper restResultWrapper=(RestResultWrapper)object;
            Class<?> aClass = restResultWrapper.getClass();
            Method getResult = aClass.getMethod("getResult");
            JsonView annotation = getResult.getAnnotation(JsonView.class);
            //获取 annotation 这个代理实例所持有的 InvocationHandler
            InvocationHandler h = Proxy.getInvocationHandler(annotation);
            // 获取 AnnotationInvocationHandler 的 memberValues 字段
            Field hField = h.getClass().getDeclaredField("memberValues");
            // 因为这个字段事 private final 修饰，所以要打开权限
            hField.setAccessible(true);
            // 获取 memberValues
            Map memberValues = (Map) hField.get(h);
            // 修改 value 属性值
            memberValues.put("value", valueinfo);
        }
        log.info("本次接口耗时={}ms", endTime);
        log.info("afterReturning={}", object.toString());
    }
}