package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> getGalleryList() {
		return galleryDao.getList();
	}

	public boolean uploadImage(GalleryVo vo) {	
		return galleryDao.insert(vo);
	}
	public boolean deleteImage(GalleryVo vo) {	
		return galleryDao.delete(vo);
	}

}
