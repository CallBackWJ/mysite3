package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;


@Controller("ajax_guestbook")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
	private GuestBookService service;
	
	@RequestMapping("")
	public String index_ajax(){
		
		return "/guestbook/index-ajax";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value="p", required=true, defaultValue="") String p)
	{
		int page=(("".equals(p))||(p.matches("\\d*")==false))? 1:Integer.parseInt(p);
		return JSONResult.success(service.list(page));
		
	}
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert(GuestBookVo vo)
	{
		long no = service.insert(vo);
		GuestBookVo newVo = service.getByNo(no); 
		return JSONResult.success(newVo);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete" ,method = RequestMethod.GET)
	public JSONResult delete(GuestBookVo vo)
	{
		return (service.delete(vo))?JSONResult.success("success"):JSONResult.fail("fail");
	}
	
	
}
