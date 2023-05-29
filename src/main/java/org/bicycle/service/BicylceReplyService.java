package org.bicycle.service;

import java.util.List;

import org.bicycle.domain.BicylceCriteria;
import org.bicycle.domain.BicylcePageDTO;

import org.bicycle.domain.BicylceReplyPageDTO;
import org.bicycle.domain.BicylceReplyVO;

public interface BicylceReplyService {
	
	//등록
	public int register(BicylceReplyVO vo);
	
	//조회
	public BicylceReplyVO get(Long rno);
	
	//수정
	public int modify(BicylceReplyVO vo);
	
	//삭제
	public int remove(Long rno);
	//댓글 목록
	public List<BicylceReplyVO> getList(BicylceCriteria cri,Long bno);
	
	//댓글 전체수 처리
	public BicylceReplyPageDTO getListPage(BicylceCriteria cri,Long bno);
	
}
