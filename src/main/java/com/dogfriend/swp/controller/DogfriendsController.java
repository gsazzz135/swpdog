package com.dogfriend.swp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogfriend.swp.domain.tempHumiVO;

@RestController
@RequestMapping("/appctrl")
public class DogfriendsController {
	
	private static final Logger logger = LoggerFactory.getLogger(DogfriendsController.class);

	@GetMapping("/view/{temp}/{humi}")	//온습도 requestMapping + mothod get
	public tempHumiVO tempHumi(@PathVariable("temp") String temp,
							   @PathVariable("humi") String humi ) {
		tempHumiVO temHum = new tempHumiVO();
		temHum.setTemp(temp);
		temHum.setHumi(humi);
		return temHum;
	}
	
	@RequestMapping("/temp")
	public String hello(){
		logger.info("temp....");
		return "hello";	//페이지 못찾음..
	}
	
	/*	
	 * 	ResponseEntity : JSON 데이터와 HTTP 상태 메세지 반환
	 * 
	 * @RequestMapping : 특정 uri매칭되는 클래스나 메소드 명시
	 * @PathVariable : url에서 원하는 정보 추출할때
	 * @RequestBody : 요청 문자열이 그대로 파라메터로 전달(매개변수로 JSON 데이터를 받는다고 생각)
	 * @ResponseBody : 리턴타입이 http의 응답 메시지로 전송
	 * @ModelAttribute : 자동으로 해당 객체를 뷰까지 전달하게끔
	 * @RequestParam : 요청에서 특정한 파라메터 값을 찾아낼 때
	 */

}
