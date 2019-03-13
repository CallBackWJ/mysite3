package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean existEmail( String email ) {
		UserVo userVo = userDao.get( email );
		return userVo != null;
	}
	public void join(UserVo userVo) {
		userDao.insert(userVo);

	}

	public UserVo getUser(String email, String password) {
		return userDao.get(email, password);

	}

	public UserVo getUser(long no) {
		return userDao.get(no);

	}

	public void modify(UserVo userVo) {
		userDao.update(userVo);
	}

}
