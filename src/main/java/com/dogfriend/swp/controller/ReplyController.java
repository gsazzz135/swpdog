package com.dogfriend.swp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.domain.PageMaker;
import com.dogfriend.swp.domain.ReplyVO;
import com.dogfriend.swp.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply){		//@RequestBody: ajax body?�� ?��?�� json?�� 받아?��?��.
		logger.debug("ReplyRegister>>{}",reply);
		try {
			service.register(reply);
			return new ResponseEntity<>("ReplyRegisterOK", HttpStatus.OK); //
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") Integer rno,
									  	 @RequestBody ReplyVO reply){
		logger.debug("ReplyUpdate>>{}",rno, reply);
		try {
			reply.setRno(rno);
			service.modify(reply);
			return new ResponseEntity<>("ReplyUpdateOK", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") Integer rno){
		logger.debug("ReplyDelete>>{}", rno);
		try {
			service.remove(rno);
			return new ResponseEntity<>("ReplyDeleteOK", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno,
														@PathVariable("page") Integer page){
		logger.info("ReplyList>>{}", bno);
		try {
			Map<String, Object> map = new HashMap<>();
			Criteria cri = new Criteria();
			cri.setPage(page);
			PageMaker pagemaker = new PageMaker();
			pagemaker.setCri(cri);
			List<ReplyVO> list = service.listReplyPage(bno, cri);
			map.put("list", list);
			
			int replyCount = service.getTotalCount(bno);
			pagemaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pagemaker);
			
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//EEE
	@RequestMapping(value = "/{rno}", method = RequestMethod.GET)
	public ResponseEntity<ReplyVO> read(@PathVariable("rno") Integer rno){
		try {
				ReplyVO reply = service.ReadRno(rno);
			
				return new ResponseEntity<>(reply, HttpStatus.OK);
		} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
