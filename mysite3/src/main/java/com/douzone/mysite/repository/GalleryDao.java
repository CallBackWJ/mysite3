package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList() {
		return sqlSession.selectList("gallery.getgallery");
	}

	public boolean insert(GalleryVo vo) {
		// TODO Auto-generated method stub
		return 1==sqlSession.insert("gallery.insert", vo);
	}
	
	public boolean delete(GalleryVo vo) {
		// TODO Auto-generated method stub
		return 1==sqlSession.delete("gallery.delete", vo);
	}
	
	
	
}
