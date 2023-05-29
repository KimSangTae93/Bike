package org.bicycle.domain;

import java.util.Date;

import lombok.Data;

@Data//코드사용 축소를 위한 LOMBOK사용
public class BicylceBoardVO {
	/*private는 멤버 변수에 값을 설정해줄 때 ,
	조건을 걸어줄 수 있기 때문에 외부에서 데이터를 변경하는 것을 막을수있다.*/
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	
	//댓글수 처리를위해 추가
	private int replyCnt;
}
