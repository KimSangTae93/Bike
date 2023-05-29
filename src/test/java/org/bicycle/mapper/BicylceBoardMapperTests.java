package org.bicycle.mapper;




import java.util.List;

import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;
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
public class BicylceBoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BicylceBoardMapper mapper;
	
	/*@Test
	public void testGetList(){
		mapper.getList().forEach(board ->log.info(board));
	}
	
	@Test
	public void testInsert(){
		
		BicylceBoardVO board =new BicylceBoardVO();
		
		board.setTitle("새로운 게시글");
		board.setContent("내용이지롱");
		board.setWriter("MASTER");
		
		mapper.insert(board);
		
		log.info(board);
		
	}
	@Test
	public void testSelectKey(){
		
		BicylceBoardVO board = new BicylceBoardVO();
		
		board.setTitle("새로운 게시글 selectKey");
		board.setContent("selectKey 내용이지롱");
		board.setWriter("MASTER");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
		
	}
	@Test
	public void testRead(){
		//존재하는 게시물 번호로 테스트
		BicylceBoardVO board = mapper.read(1L);
		
		log.info(board);
	}*/
	/*@Test
	public void testDelete(){
		
		
		log.info("DELETE COUNT"+mapper.delete(1L));
	}*/
	/*@Test
	public void testUpdate(){
		
		BicylceBoardVO board = new BicylceBoardVO();
		
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("CUSTOMER");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT"+count);
		
	}*/
	@Test
	public void testPaging(){
	
		BicylceCriteria cri = new BicylceCriteria();
		//3페이지 출력
		cri.setPageNum(3);
		//10개씩 출력
		cri.setAmount(10);
		
		List<BicylceBoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board.getBno()));
	}
	@Test
	public void testSerch(){
		
		BicylceCriteria cri = new BicylceCriteria();
		cri.setKeyword("새로 만들기");
		cri.setType("TC");
		
		List<BicylceBoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	}
