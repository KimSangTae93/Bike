package org.bicycle.service;

import java.util.List;

import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;

public interface BicylceService {
		
	//service메서드 설계시 메서드 이름은 현실적인 로직의 이름을 붙이는게 관례이다.
	//특정 게시물을 가져오는 get(),getList()메서드는 처음부터 리턴타입을 결정하는것이 가능하다. 
	public void register(BicylceBoardVO board);
	
	public  BicylceBoardVO get(Long bno);
	
	public boolean modify(BicylceBoardVO board);
	
	public boolean remove(Long bno);
	
	//public List<BicylceBoardVO> getList();
	
	public List<BicylceBoardVO> getList(BicylceCriteria cri);
	//전체 데이터량 출력
	public int getTotal(BicylceCriteria cri);
}
