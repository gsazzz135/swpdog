package com.dogfriend.swp.controller;



import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.domain.PageMaker;
import com.dogfriend.swp.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject 
	private BoardService service;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void registerGET(Board board, Model model) throws Exception{
		logger.info("regist GET..............");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registPost(Board board, RedirectAttributes rttr) throws Exception{
		logger.info("regist POST............{}", board.toString());
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") Integer bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info("read GET..............");
		Board board = service.read(bno);
		model.addAttribute(board);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("bno") Integer bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("remove GET..............");
		service.remove(bno);
		rttr.addFlashAttribute("msg", "remove-ok");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateGET(@RequestParam("bno") Integer bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info("update GET..............");
		Board board = service.read(bno);
		model.addAttribute(board);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updatePOST(Board board, Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("update POST..............");
		service.modify(board);
		rttr.addFlashAttribute("msg", "SAVE");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perpageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/read?bno="+board.getBno();
	}
	
	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void registerGET(Model model) throws Exception{
		logger.info("show all list.......");
		List<Board> boards = service.listAll();
		model.addAttribute("list", boards);
	}
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
	
		int totalCount = service.countPaging(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		
		logger.info(pageMaker.toString());
		
	}
}
