package org.bicycle.service;

import java.util.List;

import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;
import org.bicycle.mapper.BicylceBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
//BicylceService를 구현하는 클래스
//@Service 어노테이션은 계층 구조상 비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용한다.
//@AllargsConstructor는 모든 파라미터를 이용하는 생성자를 만들기때문에,
//실제코드는 BicylceBoardMapper를 주입받는 생성자가 만들어진다.
public class BicylceServiceImpl implements BicylceService{
	
	@Setter(onMethod_ = @Autowired)
	private BicylceBoardMapper mapper;
	
	//insertSelectKey를 이용한 생성된 게시물 번호 확인
	@Override
	public void register(BicylceBoardVO board) {
		
		log.info("[[register]]"+ board);
		
		mapper.insertSelectKey(board);
		
	}
	//조회
	@Override
	public BicylceBoardVO get(Long bno) {
		
		log.info("[[get]]"+bno);
		
		return mapper.read(bno);
	}
	//수정
	//수정과 삭제가 이루어지면 1이라는 값이 반환 되기때문에 ==연산자이용으로 true/false처리가능
	@Override
	public boolean modify(BicylceBoardVO board) {
		log.info("[[modify]]"+ board);
		return mapper.update(board) == 1;
	}
	//삭제
	@Override
	public boolean remove(Long bno) {
		log.info("[[remove]]"+bno);
		return mapper.delete(bno) == 1;
	}
	
	/*///목록 가져오기
	@Override
	public List<BicylceBoardVO> getList() {
		log.info("[[getList]]");
		return mapper.getList();
	}*/
	//목록가져오기+페이징처리
	@Override
	public List<BicylceBoardVO> getList(BicylceCriteria cri) {
		
		log.info("get List with BicylceCriteria " + cri);
		
		return mapper.getListWithPaging(cri);
	}
	@Override
	public int getTotal(BicylceCriteria cri) {
		
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
	
	

	

}
