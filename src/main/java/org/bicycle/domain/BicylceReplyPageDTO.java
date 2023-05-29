package org.bicycle.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class BicylceReplyPageDTO {
	
	private int replyCnt;
	private List<BicylceReplyVO> list;
}
