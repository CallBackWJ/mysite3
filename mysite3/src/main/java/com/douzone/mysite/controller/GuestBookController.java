package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService service;
	
	@RequestMapping({"/list",""})
	public String list(Model model)
	{
		model.addAllAttributes(service.list());
		return "/guestbook/list";
		
	}
	@RequestMapping("/insert")
	public String insert(GuestBookVo vo)
	{
		service.insert(vo);
		return "redirect:/guestbook/list";
		
	}
	
	@RequestMapping(value="/delete" ,method = RequestMethod.GET)
	public String delete(String no,Model model)
	{
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}
	@RequestMapping(value="/delete" ,method = RequestMethod.POST)
	public String delete(GuestBookVo vo)
	{
		service.delete(vo);
		return "redirect:/guestbook/list";
		
	}
	
	
}
