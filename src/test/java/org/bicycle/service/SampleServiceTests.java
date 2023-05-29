package org.bicycle.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {

	@Setter(onMethod_ =@Autowired)
	private SampleService service;
	
	@Test
	public void testClass(){
		//toString()의 결과물
		log.info(service);
		//위의 결과보다 세밀하게 파악하려면 getClass()를 이용해서 파악해야함.
		log.info(service.getClass().getName());
	}
	@Test
	public void testAdd() throws Exception{
		//SampleServiceImpl의 doAdd를 실행하면 LogAdvice의 설정이 같이 적용되어 아래와 같은 로그가 기록된다.
		log.info(service.doAdd("123", "456"));
	}
	@Test
	public void testAddError() throws Exception{
		//SampleServiceImpl의 doAdd를 실행하면 LogAdvice의 설정이 같이 적용되어 아래와 같은 로그가 기록된다.
		log.info(service.doAdd("123", "789"));
}
}
