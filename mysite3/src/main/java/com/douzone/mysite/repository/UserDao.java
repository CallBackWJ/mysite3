package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo userVo) {
		return 1 == sqlSession.insert("user.insert", userVo);

	}

	
	public UserVo get(String email, String password) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}

	
	
	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
		
	}

	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}

	public boolean update(UserVo vo) {
		return 1 == sqlSession.update("user.update", vo);
	}

}
