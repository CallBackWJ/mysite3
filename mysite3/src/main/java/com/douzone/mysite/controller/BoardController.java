package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.security.Auth;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/list")
	public String list(String kwd, String page, Model model) {
		model.addAllAttributes(boardService.list(kwd, page));
		return "/board/list";

	}

	@RequestMapping("/view")
	public String view(String no, String page, Model model) {
		model.addAllAttributes(boardService.view(no, page));
		return "/board/view";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(String no, Model model) {
		model.addAttribute("vo", boardService.modifyFrom(no));
		return "/board/modify";
	}
	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVo vo) {
		boardService.modify(vo);
		return "redirect:/board/list";
	}

	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(String no, Model model) {

		model.addAttribute("board", boardService.replyForm(no));
		return "/board/reply";

	}

	@Auth
	@Transactional
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(BoardVo boardVo) {
		
		boardService.reply(boardVo);
		return "redirect:/board/list";

	}
	
	@Auth
	@RequestMapping("/delete")
	public String delete(String no)
	{
		
		boardService.delete(no);
		return "redirect:/board/list";
		
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "/board/write";
	}
	
	@Auth
	@Transactional
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		boardService.write(boardVo);
		return "redirect:/board/list";

	}

}
