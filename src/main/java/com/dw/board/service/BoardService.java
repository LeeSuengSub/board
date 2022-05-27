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
	public int updateBoard(int boardId, BoardVO vo) {
		vo.setBoardId(boardId);
		return boardMapper.updateBoard(vo);
	}
	//게시글 상세조회
	public BoardVO selectBoard(int boardId) {
		return boardMapper.selectBoardOne(boardId);
	}
	//게시물 조회수 증가
	public int getUpdateBoardViews(int boardId) {
		// 1. 게시판 번호를 이용해서 조회수 컬럼을 select
		BoardVO vo =  boardMapper.selectBoardOne(boardId);
		int views = vo.getCnt();
		++views; //2. 조회수를 1증가함.
		vo.setCnt(views);
		vo.setBoardId(boardId);
		return boardMapper.updateBoardViews(vo);
	}
	//작성자가 작성한 게시물 조회
	public List<Map<String,Object>> getSearchBoardList(String studentsName){
		return boardMapper.selectSearchBoardList(studentsName);
	}
}
