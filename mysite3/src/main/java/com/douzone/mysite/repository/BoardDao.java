package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList(String kwd, long page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd", kwd);
		map.put("page", (page * 5) - 5);
		return sqlSession.selectList("board.getlist", map);
	}

	public int getCount(String kwd) {
		return sqlSession.selectOne("board.getcount", kwd);
	}

	public boolean insert(BoardVo boardVo) {
		return 1 == sqlSession.insert("board.insert", boardVo);
	}

	public boolean delete(long no) {
		return 1 == sqlSession.delete("board.delete", no);
	}

	public boolean updateHit(long no) {
		return 1 == sqlSession.update("board.updatehit", no);
	}

	public BoardVo get(long no) {
		return sqlSession.selectOne("board.get", no);
	}

	public boolean update(BoardVo vo) {
		return 1 == sqlSession.update("board.update", vo);
	}

	public boolean priorityCheck(BoardVo vo) {
		return sqlSession.selectOne("board.prioritycheck", vo);
	}

	public boolean updatePriority(BoardVo vo) {
		return 1 == sqlSession.update("board.updatepriority", vo);

	}

	public boolean reply(BoardVo vo) {
		return 1 == sqlSession.update("board.reply", vo);
	}

}
