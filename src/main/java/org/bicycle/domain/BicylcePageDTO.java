package org.bicycle.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BicylcePageDTO {
	
	//시작페이지
	private int startPage;
	//끝페이지
	private int endPage;
	//이전,다음
	private boolean prev,next;
	
	//총합
	private int total;
	//페이징 처리를 위한 페이지 번호값과 양 가져오기
	private  BicylceCriteria cri;
	
	//페이징 처리를 위한 페이지 번호 1~10페이지 까지 출력
	public BicylcePageDTO(BicylceCriteria cri,int total){
		
		this.cri = cri;
		this.total=total;
		//끝페이지
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//시작페이지
		this.startPage =this.endPage - 9;
		
		int realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());
		
		if(realEnd < this.endPage){
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage <realEnd;
	}
	
}
