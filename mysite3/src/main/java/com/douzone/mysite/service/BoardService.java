package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public Map<String, Object> list(String keyword, String pageNum) {

		String kwd = keyword;
		String page = pageNum;
		if (page == null)
			page = "1";
		if (kwd == null)
			kwd = "";

		List<BoardVo> list = boardDao.getList(kwd, Long.parseLong(page));

		Paging paging = new Paging();
		paging.makeBlock((int) Long.parseLong(page));
		paging.makeLastPageNum(boardDao.getCount(kwd));
		Integer blockStartNum = paging.getBlockStartNum();
		Integer blockLastNum = paging.getBlockLastNum();
		Integer lastPageNum = paging.getLastPageNum();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd", kwd);
		map.put("curPageNum", (int) Long.parseLong(page));
		map.put("blockStartNum", blockStartNum);
		map.put("blockLastNum", blockLastNum);
		map.put("lastPageNum", lastPageNum);
		map.put("list", list);

		return map;

	}

	public Map<String, Object> view(String BoardNo, String pageNum) {
		// TODO Auto-generated method stub
		long no = Long.parseLong(BoardNo);
		long page = Long.parseLong(pageNum);
		boardDao.updateHit(no);
		BoardVo vo = boardDao.get(no);

		// 데이터를 request범위에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		map.put("page", page);
		return map;
	}
	
	
	public void write(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
	public void delete(String no) {
		boardDao.delete(Long.parseLong(no));
	}
	public BoardVo modifyFrom(String no) {
		return boardDao.get(Long.parseLong(no));
	}

	public boolean modify(BoardVo vo) {
		return boardDao.update(vo);
	}

	public BoardVo replyForm(String no) {
		return boardDao.get(Long.parseLong(no));
	}

	public boolean reply(BoardVo boardVo) {
		
		if(!boardDao.priorityCheck(boardVo))
		{
			boardDao.updatePriority(boardVo);
		}
		
		return boardDao.reply(boardVo);
	}

}
