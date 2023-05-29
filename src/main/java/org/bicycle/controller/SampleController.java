package org.bicycle.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bicycle.domain.SampleVO;
import org.bicycle.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
//단순 문자열 반환을 하기위한 RestController작성
@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
			//기존의 @Controller는 문자열을 반환하는 경우 JSP파일의 이름으로 반환하지만
			//@RestController의 경우 순수한데이터가 된다.
			//@GetMapping에 사용된 produces속서은 해당 메서드가 생산하는 MIME타입을 의미
			//문자열로 직접 지정할수도 있고,메서드내의 MediaType이라는 클래스를 이용할 수 도 있다.
		@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
			public String getText(){
			
			log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
			
			return "단순 문자열 반환 Test";
		}
		//JSON,XML방식의 데이터 생성할수있도록 작성됌.
		@GetMapping(value = "/getSample",
					produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
							     MediaType.APPLICATION_ATOM_XML_VALUE })
		public SampleVO getSample(){
			
			return new SampleVO(31,"김","상태");
		}
		//컬렉션 타입의 객체 반환[List,Set,Map,Deck]
		@GetMapping(value="/getList")
		public List<SampleVO> getList(){
			return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"first",i+"Last"))
					.collect(Collectors.toList());
		}
		//맵의 경우 '키','값'을 가지는 하나의 객체로 간주
		@GetMapping(value="/getMap")
		public Map<String,SampleVO> getMap(){
			Map<String,SampleVO> map= new HashMap<>();
			map.put("First",new SampleVO(31,"김","상태"));
			return map;
			
		}
		//ResponseEntity타입
		//Rest방식으로 호출시 데이터 자체를 전송하는방식으로 처리
		//데이터를 요청한 쪽에서 정상적인지,비정상적인 데이터인지 구분할수있는 방법을 제공해야함
		//ResponseEntity은 데이터와 함꼐 HTTP헤더등의 상태메시지를 같이전달하는 용도로 사용
		//HTTP의 상태 코드와 에러메시지등을 함꼐 데이터를 전달 할 수있기떄문에 받는입장에서 확실히 알수있음
		
		@GetMapping(value="/checkResponseEntity", params ={"height","weight"})
		public ResponseEntity<SampleVO> checkResponseEntity(Double height,Double weight){
			
			SampleVO vo = new SampleVO(0, ""+height, "" +weight);
			
			 ResponseEntity<SampleVO> result = null;
			 
			 if(height < 150){
				 //150보다 작으면 502
				 result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			 }else{
				 //그렇지않다면 200(ok)코드와 데이터 전송
				 result =ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
		
		}
		@PostMapping("/ticket")
		public Ticket convert(@RequestBody Ticket ticket) {
			
			log.info("convert---------ticket"+ticket);
			
			return ticket;
		}
}
