package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dw.board.VO.StudentsVO;
import com.dw.board.mapper.StudentsMapper;

@Service
public class StudentsService {

	@Autowired
	private StudentsMapper studentsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//학생저장
	public int setStudents(StudentsVO vo) {
		//학생 비밀번호 암호화
		String password = vo.getStudentsPassword();
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
		return studentsMapper.insertStudents(vo);
	}
	//학생 조회(List)
	public List<StudentsVO> getAllStudentsList(){
		return studentsMapper.selectAllStudentsList();
	}
	//학생 조회(Map)
	public List<Map<String,Object>> selectAllStudentsMap(){
		return studentsMapper.selectAllStudentsMap();
	}
	//특정 학생조회
	public StudentsVO selectStudentsId(int StudentsId) {
		return studentsMapper.selectStudents(StudentsId);
	}
	//학생 데이터 삭제
	public int deleteStudents(int studentsId) {
		return studentsMapper.deleteStudents(studentsId);
	}
	//학생 정보 Update
	public int getUpdateStudents(StudentsVO vo, int studentsId) {
		vo.setStudentsId(studentsId);
		return studentsMapper.updateStudents(vo);
	}
	//가입된 학생인지 아닌지 여부 체크
	public boolean isStudents(StudentsVO vo) {
		StudentsVO students = studentsMapper.selectStudentsOne(vo);
		if(students == null) { //쿼리결과가 null 리턴
			return false;
		}
		String inputPassword = vo.getStudentsPassword(); //HTML에서 가져온 비밀번호
		String password = students.getStudentsPassword(); //DB에서 가져온 비밀번호
		
		if(!passwordEncoder.matches(inputPassword, password)) { //비밀번호 체크
			return false;
		}
		
		return true;
	}
}
