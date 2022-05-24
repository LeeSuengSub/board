package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.VO.BoardVO;
import com.dw.board.service.BoardService;

@RestController
@RequestMapping("/api/v1")
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	
	//게시판 저장
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardService.setBoard(vo);
	}
	//게시판 전체조회
	@CrossOrigin
	@GetMapping("/board")
	public List<Map<String,Object>> callBoardList(){
		return boardService.getAllBoardList();
	}
	
}
