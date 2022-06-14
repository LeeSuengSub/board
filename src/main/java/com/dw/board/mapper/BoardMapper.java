package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.VO.BoardVO;

@Mapper
public interface BoardMapper {
	//게시판 저장
	public int insertBoard(BoardVO vo);
	//게시판 조회
	public List<Map<String,Object>> selectAllBoardList();
	//게시판 삭제
	public int deleteBoard(int boardId);
	//게시판 수정
	public int updateBoard(BoardVO vo);
	//게시판 상세보기
	public BoardVO selectBoardOne(int boardId);
	//조회수 업데이트
	public int updateBoardViews(BoardVO vo);
	//작성자 검색
	public List<Map<String,Object>> selectSearchBoardList(String studentsName);
	//통계
	public Map<String,Object> selectBoardStatstics();
	
	public int selectAllBoardTotal();
	
	public List<Map<String, Object>> selectSearchBoardListTEST(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
