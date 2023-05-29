package org.bicycle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;


public interface BicylceBoardMapper {
	//제네릭 사용 List<?> ?는 와일드 카드로 메스드를 호출할때 ?가 정해진다.
		/*@Select("select * from tbl_board where bno > 0")*/
		//CREATE
		public List<BicylceBoardVO> getList();
		
		//paging
		public List<BicylceBoardVO> getListWithPaging(BicylceCriteria cri);
		
		
		
		public void insert(BicylceBoardVO board);
		
		public void insertSelectKey(BicylceBoardVO board);
		
		//READ
		public BicylceBoardVO read(Long bno);
		
		//DELETE
		public int delete(Long bno);
		
		//UPDATE
		public int update(BicylceBoardVO board);

		//데이터베이스의 실제 모든 게시물수 계산  
		public int getTotalCount(BicylceCriteria cri);
		
		//updateReplyCnt() 메서드는 해당게시물의 번호의 bno와 증가나 감소를 의미하는 amount변수에 파라미터를 받을수있도록 처리한다.
		//Mybatis의 SQL을 처리하기 위해서는 기본적으로 하나의 파라미터 타입을 사용하기 때문에,
		//2개이상의 데이터를 전달하려면 @Param이라는 어노테이션을 이용해서 처리할수있다.
		public void updateReplyCnt(@Param("bno")Long bno, @Param("amount")int amount);
}
