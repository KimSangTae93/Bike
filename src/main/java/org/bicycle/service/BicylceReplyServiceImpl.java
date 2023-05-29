package org.bicycle.service;

import java.util.List;

import org.bicycle.domain.BicylceCriteria;
import org.bicycle.domain.BicylcePageDTO;

import org.bicycle.domain.BicylceReplyPageDTO;
import org.bicycle.domain.BicylceReplyVO;
import org.bicycle.mapper.BicylceBoardMapper;
import org.bicycle.mapper.BicylceReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BicylceReplyServiceImpl implements BicylceReplyService{
	
	@Setter(onMethod_ = @Autowired)
	private BicylceReplyMapper mapper;
	//트랜잭션 처리를 위한 BicylceBoardMapper 추가
	@Setter(onMethod_ = @Autowired)
	private BicylceBoardMapper boardmapper;
	
	//등록
	@Transactional
	@Override
	public int register(BicylceReplyVO vo) {
		
		log.info("[register] : " + vo);
		
		boardmapper.updateReplyCnt(vo.getBno(), 1);
		
		
		return mapper.insert(vo);
	}
	//조회
	@Override
	public BicylceReplyVO get(Long rno) {
		
		log.info("[get] : " + rno);
		
		return mapper.read(rno);
	}
	//수정
	@Override
	public int modify(BicylceReplyVO vo) {
		
		log.info("[modify] : " + vo);
		
		return mapper.update(vo);
	}
	//삭제
	@Transactional
	@Override
	public int remove(Long rno) {
		
		log.info("[remove] : " + rno);
		
		BicylceReplyVO vo= mapper.read(rno);
		
		boardmapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}
	//댓글 목록
	@Override
	public List<BicylceReplyVO> getList(BicylceCriteria cri, Long bno) {
		log.info("[get Reply List] : " + bno);
		return mapper.getListWithPaging(cri, bno);
	}
	//댓글 전체수 처리
	@Override
	public BicylceReplyPageDTO getListPage(BicylceCriteria cri, Long bno){
		
		return new BicylceReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}

}
