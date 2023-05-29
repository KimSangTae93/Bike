package org.bicycle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bicycle.domain.BicylceCriteria;
import org.bicycle.domain.BicylceReplyVO;

public interface BicylceReplyMapper {
	
	//등록
	public int insert(BicylceReplyVO vo);
	
	//조회
	public BicylceReplyVO read(Long rno);
	
	//삭제
	public int delete(Long rno);
	
	//수정
	public int update(BicylceReplyVO vo);
	
	//댓글 목록	
	public List<BicylceReplyVO> getListWithPaging(
			@Param("cri") BicylceCriteria cri,
			@Param("bno") Long bno);
	//댓긋의 숫자 파악
	public int getCountByBno(Long bno);
}
 