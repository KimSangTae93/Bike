package org.bicycle.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

//해당 객체가 Aspect구현한 것임을 나타내기위한 어노테이션
@Aspect
@Log4j
//스프링 빈으로 인식하기 위한 어노테이션
@Component
public class LogAdvice {
	//Before import시 Junit이아닌 aspectj로 해야함
	//Before 내부의 표현식은 AspectJ의 표현식이다.execution의 경우 접근제한자와 특정 클래스의 메서드를 지정할 수 있다.
	/*@Before( "execution(* org.aopex.service.SampleService*.*(..))")
	public void logBefore(){
		log.info("=======================");
	}*/
	
	//pointcut설정에 doAdd()메서드를 명시하고,파라미터의 타입을 지정했다. 뒤쪽의 &&args는 변수명을 지정해서 logBeforeWithParam 메서드의 파라미터를 설정한다.
	@Before( "execution(* org.bicycle.service.SampleService*.doAdd(String,String))&& args(str1,str2)")
	public void logBeforeWithParam(String str1,String str2){
		log.info("=======================");
		log.info("str1 = "+ str1);
		log.info("str2 = "+ str2);
	
		
	}//지정된 대상이 예뢰를 발생한 후에 동작하면서 문제를 찾을수있도록 도와주는 어노테이션
	@AfterThrowing(pointcut = "execution(* org.bicycle.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception){
		
		log.info("Exception !!!!!!! "+exception);
		
	}//ProceedingJoinPoint는 AOP의 대상이되는 Target이나 파라미터등을 파악할 뿐만 아니라,직접 실행을 결정할수 있다.
	//@Before등과 달리 @Around가 적용되는 메서드의 경우에는 리턴타입이 void가 아닌 타입으로 설정하고,메서드의 실행 결과 역시 직접 반환하는 형태로 작성해야한다.
	@Around("execution(* org.bicycle.service.SampleService*.*(..))")
		public Object logTime( ProceedingJoinPoint pjp){
		
		long start = System.currentTimeMillis();
		
		log.info("Target" + pjp.getTarget());
		log.info("Param" + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME : " + (end - start));
		
		return result;
	}
}
