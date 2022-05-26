package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.VO.BoardVO;
import com.dw.board.mapper.BoardMapper;
import com.dw.board.mapper.StudentsMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private StudentsMapper studentsMapper;
	
	//게시판 저장
	public int setBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}
	//게시판 조회
	public List<Map<String,Object>> getAllBoardList(){
		return boardMapper.selectAllBoardList();
	}
	//게시판 삭제
	@Transactional(rollbackFor = Exception.class)
	public int deleteBoard(int boardId) {
		return boardMapper.deleteBoard(boardId);
	}
	//게시판 수정
	@Transactional(rollbackFor = Exception.class)
	public int updateBoard(int boardId, BoardVO vo) {
		vo.setBoardId(boardId);
		return boardMapper.updateBoard(vo);
	}
	//게시글 상세조회
	public BoardVO selectBoard(int boardId) {
		return boardMapper.selectBoard(boardId);
	}
}
