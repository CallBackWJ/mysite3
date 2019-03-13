package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteDao {

	@Autowired
	private SqlSession sqlSession;

	public SiteVo getSite() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("site.getsite");
	}

	public void update(SiteVo vo) {
		// TODO Auto-generated method stub
		sqlSession.update("site.update",vo);
	}

}
