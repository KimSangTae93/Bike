package org.bicycle.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.bicycle.domain.BicylceCriteria;
import org.bicycle.domain.BicylceReplyVO;
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
public class BicylceReplyMapperTests {
	
	//테스트 전 해당 번호의 게시물이 존재하는지 반드시 확인해야함
	private Long[] bnoArr = {1900584L,1900585L,1900586L,1900587L,1900588L};
	
	@Setter(onMethod_ = @Autowired )
	
	private BicylceReplyMapper mapper;
	
	/*@Test
	public void testCreate(){
		
		IntStream.rangeClosed(1,100).forEach(i -> {
			
			BicylceReplyVO vo = new BicylceReplyVO();
			
			//게시물 번호
			vo.setBno(bnoArr[i %  5]);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead(){
		
		Long targetRno = 5L;
		
		BicylceReplyVO vo= mapper.read(targetRno);
		
		log.info(targetRno);
		
	}
	@Test
	public void testDelete(){
		
		Long targetRno = 200L;
		
		mapper.delete(targetRno);
	}
	@Test
	public void testUpdate(){
		
		Long targetRno = 199L;
		
		BicylceReplyVO vo=mapper.read(targetRno);
		
		//reply 이름 변경
		vo.setReply("update reply");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT : " + count);
	}
	@Test
	public void testList(){
		BicylceCriteria cri = new BicylceCriteria();
		
		List<BicylceReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
				
				replies.forEach(reply -> log.info(reply));		
	}
	
	
	@Test
	public void testMapper(){
		log.info(mapper);
	}*/
	@Test
	public void testList2(){
		
		BicylceCriteria cri = new BicylceCriteria(2,10);
		
		List<BicylceReplyVO> replies = mapper.getListWithPaging(cri,1900723L);
		
		replies.forEach(reply -> log.info(reply));
		
	}
}
