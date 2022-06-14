package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.VO.StudentsVO;
import com.dw.board.service.StudentsService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1") //중복되는 URL을 간소화(전역변수 개념)
public class StudentsRestController {
	
	@Autowired
	private StudentsService studentsService;
	
	//중요한 정보를 서버에 전송할 때 POST 사용!
	@CrossOrigin
	@PostMapping("/login")
	public boolean callLogin(@RequestBody StudentsVO vo, HttpSession httpSession) {
		boolean isLogin = studentsService.isStudents(vo, httpSession);
		return isLogin;
	}
	
	//학생 저장
		//post 는 body로 데이터를 받음 (보안)
		@CrossOrigin
		@PostMapping("/students")
		public int callSaveStudents(@RequestBody StudentsVO vo) {
			return studentsService.setStudents(vo);
		}
		
		//학생 조회
		@CrossOrigin
		@GetMapping("/students")
		public PageInfo<Map<String,Object>> callStudentsList(@RequestParam("pageNum") int pageNum,
				@RequestParam("pageSize")int pageSize){
			List<Map<String,Object>> list = studentsService.getAllStudentsList(pageNum, pageSize);
			return new PageInfo<Map<String,Object>>(list);
		}
		
		//학생 조회(Map으로 리턴해보기)
		@GetMapping("/students/map")
		public List<Map<String, Object>> callStudentsMap(HttpSession httpSession){
			//세션 데이터 가져오기 (추후 로직구현 예정)
//			String name = (String)httpSession.getAttribute("name");
//			if(name == null) {
//				return null;
//			}
//			System.out.println("세션에서 가져온 이름은 ===>"+name);
			return studentsService.selectAllStudentsMap();
		}
		
		//특정 학생 조회(PK로 조회)
		@CrossOrigin
		@GetMapping("/students/id/{id}")
		public StudentsVO callStudents(@PathVariable("id") int studentsId) {
			return studentsService.selectStudentsId(studentsId);
		}
		
		//학생 삭제
		@CrossOrigin
		@DeleteMapping("/students/id/{id}")
		public int callRemoveStudents(@PathVariable("id") int studentsId) {
			return studentsService.deleteStudents(studentsId);
		}
		
		//학생 수정
		@CrossOrigin
		@PatchMapping("/students/id/{id}")
		public int callUpdateStudents(@PathVariable("id") int studentsId, @RequestBody StudentsVO vo) {
			return studentsService.getUpdateStudents(vo, studentsId);
		}
		//학생 검색
		@CrossOrigin
		@GetMapping("/students/search")
		public PageInfo<Map<String, Object>> callStudentsSearch(@RequestParam("studentsName")String studentsName,@RequestParam("pageNum")int pageNum, 
				@RequestParam("pageSize")int pageSize){
			List<Map<String, Object>> list = studentsService.getSearchStudents(studentsName,pageNum, pageSize); 
//			return studentsservice.getSearchStudents(studentsName);
			return new PageInfo<Map<String, Object>>(list);
		}
	}
