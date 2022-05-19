package com.dw.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.VO.BoardVO;

@Mapper
public interface BoardMapper {
	//게시판 저장
	public int insertBoard(BoardVO vo);
	//게시판 조회
	public List<BoardVO> selectAllBoardList();
}
