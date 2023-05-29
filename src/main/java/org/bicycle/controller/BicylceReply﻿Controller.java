package org.bicycle.controller;

import java.util.List;

import org.bicycle.domain.BicylceCriteria;

import org.bicycle.domain.BicylceReplyPageDTO;
import org.bicycle.domain.BicylceReplyVO;
import org.bicycle.service.BicylceReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/bicylce/replie/")
@RestController
@Log4j
@AllArgsConstructor
public class BicylceReply﻿Controller {

	private BicylceReplyService service;
	
	//등록
	//create는 @postMapping으로 POST방식으로만 동작하도록 설계한다.
	//create()의 파라미터는 @RequestBody를 적용해서 Json을 BicylceReplyVO타입으로 변환하도록 지정한다.
	//create()는 내부적으로 BicylceReplyServiceImpl을 호출해서 register을 호출하고
	//댓글이 추가된 숫자를 확인해서 200ok 또는 500 Internal Service Error을 반환하도록 한다.
	//consumes,produces를 이용하여 JSON방식의 데이터만 처리하도록 하고,문자열을 반환하도록 한다.
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody BicylceReplyVO vo) {
		
		log.info("BicylceReplyVO"+vo);
		
		int insertCount =service.register(vo);
		
		log.info("Reply Insert Count"+insertCount);
		
		//삼항 연산자 처리
		return insertCount == 1
		? new ResponseEntity<>("success", HttpStatus.OK)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//특정 게시물의 댓글 목록 확인
	//getList는 BicylceCriteria를 이용하여 파라미터를 수집한다.
	//{bno}와{page}의 page값은 Criteria를 생성해서 직접처리해야한다.
	//@@PathVariable을 이용해서 파라미터로 처리하고 브라우저에 테스트를 하면된다.
	//ReplyService에 새롭게 추가된 getListPage()호출 하고 데이터 전송하는 형태로 수정
	@GetMapping(value= "/pages/{bno}/{page}",
	produces={
			MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<BicylceReplyPageDTO> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") Long bno){
		
		
		BicylceCriteria cri = new BicylceCriteria(page,10);
		
		log.info("Get ReplyList bno :" + bno);
		
		log.info("cri"+cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}
	//댓글 조회
	@GetMapping(value = "/{rno}",
			produces={
					MediaType.APPLICATION_ATOM_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<BicylceReplyVO> get(@PathVariable ("rno") Long rno) {
		
		log.info("get :" + rno);
		
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}
	//댓글 삭제
	@DeleteMapping(value="/{rno}",
			produces ={//텍스트 타입만 리턴
					MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable ("rno") Long rno){
		
		
		log.info("remove :" + rno);
		 return service.remove(rno) == 1
			? new ResponseEntity<>("succes",HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//댓글 수정
	//RequestBody 클라이언트가 API로 데이터를 보낼때 사용되는 데이터
	//value는 요청받을 url을 설정하게 된다.
    //method는 어떤 요청으로 받을지 정의하게 된다.(GET, POST, PUT, DELETE 등)
	// produces 속성을 이용해 Response의 Content-Type을 제어
	//consumes 속성은 요청헤더 Content-type의 값을 제한하겠다는 의미이다.
	//여기선 json으로 제어했지만,xml로도 제어가 가능하다.
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{rno}",
			consumes="application/json",
			produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
		@RequestBody BicylceReplyVO vo,
		@PathVariable("rno") Long rno){
			
		vo.setRno(rno);
		
		log.info("rno"+rno);
		log.info("modify"+vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
			
}