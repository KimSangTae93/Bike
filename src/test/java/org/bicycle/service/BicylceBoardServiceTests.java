package org.bicycle.service;


import static org.junit.Assert.assertNotNull;

import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BicylceBoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private BicylceService service;
	
	@Test
	public void testExist(){
		log.info(service);
		assertNotNull(service);
	}
	@Test
	public void testRegister(){
		
		BicylceBoardVO board= new BicylceBoardVO();
		
		board.setTitle("새로 작성하는 게시글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("MASTER");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호"+board.getBno());
	}
	@Test
	public void testGetList(){
		
		//service.getList().forEach(board -> log.info(board));
		service.getList(new BicylceCriteria(2, 10)).forEach(board ->log.info(board));
	}
/*	@Test
	public void testGet(){
		
		log.info(service.get(2L));
	}
	@Test
	public void testDelete(){
		//게시물 번호의 존재 여부 확인후 테스트
		log.info("REMOVE RESULT " +service.remove(2L));
	}
	@Test
	public void testUpdate(){
		
		BicylceBoardVO board= service.get(4L);
		
		if(board == null){
			return;
		}
		
		board.setTitle("제목을 수정합니다.");
		log.info("Modify Result : " + service.modify(board));
	}*/
	
	
}
