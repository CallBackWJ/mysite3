package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;

	public GuestBookVo get(long GuestBookNo) {
		return sqlSession.selectOne("guestbook.getByNo", GuestBookNo);
	}

	public List<GuestBookVo> getList() {
		return sqlSession.selectList("guestbook.getlist");
	}

	public List<GuestBookVo> getList(int page) {
		return sqlSession.selectList("guestbook.getlistByPage", (page - 1) * 5);
	}

	public long insert(GuestBookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
		return vo.getNo();
	}

	public boolean delete(GuestBookVo guestBookVo) {
		return 1 == sqlSession.delete("guestbook.delete", guestBookVo);
	}

}
