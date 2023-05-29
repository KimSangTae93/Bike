package org.bicycle.controller;

import org.bicycle.domain.BicylceBoardVO;
import org.bicycle.domain.BicylceCriteria;
import org.bicycle.domain.BicylcePageDTO;
import org.bicycle.service.BicylceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Log4j
@RequestMapping("/bicylceboard/*")
@AllArgsConstructor
//@Controller어노테이션을 추가하여 스프링의 빈으로 인식하게 한다.
//@RequestMapping을 통해 /board로 시작하는 모든 처리를 
//BicylceBoardController에서 처리 하도록 한다.
//BicylceService타입의 객체와 연동해야 하므로 의존성 처리도 같이 진행한다.
//BicylceBoardController는 BicylceService에 의존적이므로 
//@AllArgsConstructor를 이용하여 생성자를 만들고 자동으로 주입하도록 한다.
public class BicylceController {
	
	private BicylceService service;
	
	@GetMapping("/first")
	public void first(){
		
	}
	//목록
	/*@GetMapping("/list")
	public void list(Model model){
		
		log.info("list");
		
		model.addAttribute("list",service.getList());
	}*/
	//목록 페이징처리
	@GetMapping("/list")
	public void list(BicylceCriteria cri,Model model){
		log.info("list"+cri);
		model.addAttribute("list",service.getList(cri));
		//model.addAttribute("bicylcePageMarker",new BicylcePageDTO(cri,123));
		
		int total=service.getTotal(cri);
		
		log.info("total"+total);
		
		//BicylcePageDTO사용하도록 Model에 담아 화면에 전달
		model.addAttribute("bicylcePageMarker",new BicylcePageDTO(cri,total));
	}
	//등록
	@PostMapping("/register")
	public String register(BicylceBoardVO board,RedirectAttributes rttr){
		
		log.info("register: " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:list";
	}
	@GetMapping("/register")
	public void register() {
		
	}
	//조회 와 수정
	//+BicylceCriteria를 파라미터로 추가하여 받고 페이지 번호와 페이지 양 전달
	//ModelAttribute는 자동으로 Model에 데이터를 지정한 이름으로 담아준다. 없어도 전달은 되지만 좀더 명시적으로 이름을 지정하기 위해 사용
	@GetMapping({"/get","/modify"})					
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") BicylceCriteria cri, Model model)
	{
		
		log.info("/get or modify");
		model.addAttribute("board",service.get(bno));
	}
	//수정
	//BicylceCriteria cri추가하여 modify페이지에서 POST방식의 수정 처리
	@PostMapping("/modify")
	public String modify(BicylceBoardVO board,@ModelAttribute("cri") BicylceCriteria cri, RedirectAttributes rttr){
		log.info("modify : " + board);
		
		if(service.modify(board)){
			
			rttr.addFlashAttribute("result", "success");
		}
/*		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());*/
		return "redirect:list" + cri.getListLink();
	}
	//삭제
	////BicylceCriteria cri추가하여 modify페이지에서 POST방식의 삭제처리
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,@ModelAttribute("cri") BicylceCriteria cri, RedirectAttributes rttr){
		
		log.info("remove : " + bno);
		
		if(service.remove(bno)){
			
			rttr.addFlashAttribute("result","success");
			
		}
		/*rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		*/
		return "redirect:list"+ cri.getListLink();
	}
	

}
