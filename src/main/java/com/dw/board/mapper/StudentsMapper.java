package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.VO.BoardVO;
import com.dw.board.VO.StudentsVO;

@Mapper
public interface StudentsMapper {
	//학생 등록
	public int insertStudents(StudentsVO vo);
	//학생 조회

	/**
	 * @return
	 * @author :  Lee SuengSub
	 * @date : 2022. 5. 18.
	 * comment : Alt + Shift + J
	 */
	public List<StudentsVO> selectAllStudentsList();
	//학생 정보 조회(Map)
	public List<Map<String,Object>> selectAllStudentsMap();
	//특정 학생 조회
	public StudentsVO selectStudents(int studentsId);
	//학생 정보 삭제
	public int deleteStudents(@Param("studentsId")  int studentsId);
	//학생 정보 Update
	public int updateStudents(StudentsVO vo);
	//학생 이름으로 학생정보 조회
	/**
	 * @param vo
	 * @return
	 * @author :  Lee SuengSub
	 * @date : 2022. 5. 19.
	 * comment : 학생 이름으로 학생정보 조회
	 */
	public StudentsVO selectStudentsOne(StudentsVO vo);
	
	//게시판 저장
	public int insertBoard(BoardVO vo);
	//게시판 조회
	public List<BoardVO> selectAllBoardList();
}
