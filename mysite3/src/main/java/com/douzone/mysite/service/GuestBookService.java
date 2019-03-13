package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestBookDao;
	
	public  Map<String, Object> list(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", guestBookDao.getList());
		return map;
		
	}

	public long insert(GuestBookVo vo) {
		// TODO Auto-generated method stub
		return guestBookDao.insert(vo);
	}

	public boolean delete(GuestBookVo vo) {
		// TODO Auto-generated method stub
		return guestBookDao.delete(vo);
	}

	public List<GuestBookVo> list(int page) {
		return guestBookDao.getList(page);
	}

	public GuestBookVo getByNo(long no) {
		// TODO Auto-generated method stub
		return guestBookDao.get(no);
	}
	
	
}
