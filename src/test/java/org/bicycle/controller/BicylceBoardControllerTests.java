package org.bicycle.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// Test for Controller [아래 어노테이션이 없으면 동작안함]
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BicylceBoardControllerTests {

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	// Before이 적용된 메서드는 모든 테스트 전에 매번 실행되는 메서드가 된다.
	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

	}/*

	@Test
	public void testBicylceList() throws Exception {

		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/bicylceboard/list")).andReturn().getModelAndView()
				.getModelMap());
	}

	@Test
	public void testRegister() throws Exception {

		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/bicylceboard/register").param("title", "테스트 새로운 제목")
						.param("content", "테스트 새로운 내용").param("writer", "ExMaster"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);

	}

	@Test
	public void testGet() throws Exception {

		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/bicylceboard/get").param("bno", "21")).andReturn()
				.getModelAndView().getModelMap());
	}

	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/bicylceboard/modify").param("bno", "8")
				.param("title", "수정된 테스트 새로운 제목").param("content", "수정된 테스트 새로운 내용").param("writer", "MOMaster"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/bicylceboard/remove").param("bno", "6"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}*/
	@Test
	public void testLisgPaging() throws Exception{
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/bicylceboard/list")
				.param("pageNum", "2")
				.param("amount", "50"))
				.andReturn().getModelAndView().getModelMap());
	}
}