package org.bicycle.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BicylceCriteria {
	
	private int pageNum;
	private int amount;
	
	//검색조건
	private String type;
	private String keyword;
	
	
	//생성자를 이용해 기본값을 1페이지,10개로 처리
	public BicylceCriteria(){
		this(1,10);
	}

	public BicylceCriteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	//검색조건 
	//getTypeArr은 각 글자(T,W,C)로 구성되어 있으므로 검색 조건을 배열로 만들어 한번에 처리하기 위함
	//getTypeArr()을 이용해 Mybatis 동적 태그를 활용할수 있다.
	public String[] getTypeArr() {
	
	return type == null? new String[] {}: type.split("");
}	
	public String getListLink(){
	
	//여러개의 파라미터들을 연결해서 URL형태로 만들어준다.UriComponentsBuilder
	UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
								.queryParam("pageNum", this.pageNum)
								.queryParam("amount", this.getAmount())
								.queryParam("type", this.getType())
								.queryParam("keyword", this.getKeyword());
	
	return builder.toUriString();
								
	
	
}
}
