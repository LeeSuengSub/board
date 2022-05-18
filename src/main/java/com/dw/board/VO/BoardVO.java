package com.dw.board.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {

	private int boardId;
	private String title;
	private String content;
	private String updateAt;
	private String createAt;
}
