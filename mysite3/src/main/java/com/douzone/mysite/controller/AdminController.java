package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping({"", "/main"})
	public String main() {
		
		
		return "admin/main";
	}
	
	
	
	@RequestMapping("/update")
	public String update(
			@ModelAttribute SiteVo vo,
			@RequestParam(value="file") MultipartFile file) {
		String profile=fileuploadService.restore(file);
		vo.setProfile(profile);
		siteService.updateSite(vo);
		
		return "redirect:/admin";
	}
	
	
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
}