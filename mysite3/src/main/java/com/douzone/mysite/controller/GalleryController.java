package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	@Autowired
	private FileuploadService fileuploadService;

	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getGalleryList();
		model.addAttribute("list", list);
		return "gallery/index";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/upload")
	public String upload(@ModelAttribute GalleryVo vo, @RequestParam(value = "file") MultipartFile file) {
		String url = fileuploadService.restore(file);
		vo.setUrl(url);
		galleryService.uploadImage(vo);

		return "redirect:/gallery";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GalleryVo vo) {
		galleryService.deleteImage(vo);

		return "redirect:/gallery";
	}
}
