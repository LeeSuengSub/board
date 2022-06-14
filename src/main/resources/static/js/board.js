getPageNum();//페이지 번호 알아내는 함수 호출
getBoardStatistics();//통계함수 호출

// 게시판 조회수 증감 함수
function getBoardStatistics() {
	$.ajax({
		url: '/api/v1/board/Statistics',
		type: 'Get',
		dataType: 'json',
		success: (data) => {
			// text() or html() input을 제회한 태그를 컨트롤할 때 사용.
			//val()은 input 컨트롤할 때 사용.
			console.log(data);
			$('#boardCnt').text(data.boardCnt);
			$('#studentsCnt').text(data.studentsCnt);
			$('#writerCnt').text(data.writerCnt);
			$('#viewsCnt').text(data.viewsCnt);
		},
	});
}

	function getPageNum(){
		var pageNum = $('#nowPageNum').val(); // 현재 페이지번호
		$('#pageNum'+pageNum).css('backgroundColor', '#287bff');
		$('#pageNum'+pageNum).css('color', '#fff');
	}

function getBoardList(pageNum, pageSize) {
	location.href = "/board?pageNum=" + pageNum + "&pageSize=" + pageSize;
}

function getBoard(boardId) {
	//클릭한 게시물 확인하는 함수

	console.log(boardId);
	$('.update-popup').css('display', 'block');

	//AJAX 작성
	$.ajax({
		url: '/api/v1/board/boardId/' + boardId,
		type: 'Get',
		dataType: 'json',
		success: (response) => {
			//3. input에 데이터 set해주기
			$('#upt-title').val(response.title);
			$('#upt-content').val(response.content);
			$('#boardIdHidden').val(boardId);
			setBoardViews(boardId); //조회수 함수
		},
	});
}

function setBoardViews(boardId) {
	$.ajax({
		url: '/api/v1/board/views/boardId/' + boardId,
		type: 'PATCH',
		dataType: 'json',
		success: (response) => {
			if (response > 0) {
				//추후 로직 예정
			}
		},
	});
}

//게시판 작성, 게시판 리스트 호출
$('#contentSubmit').click(function() {
	//   if (confirm('게시글을 작성하시겠습니까?')) {
	var title = $('#title').val();
	var content = $('#content').val();
	var studentsId = $('#studentsId').val();

	//로직 구현
	if (title == '') {
		alert('제목을 입력해주세요');
		return 0;
	}
	if (content == '') {
		alert('내용을 작성해주세요');
		return 0;
	}

	var jsondata = {
		title: title,
		content: content,
		studentsId: studentsId,
	};

	$.ajax({
		url: '/api/v1/board',
		type: 'POST',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(jsondata),
		success: function(response) {
			console.log(response);
			if (response > 0) {
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10)
			}
		},
	})
});

//게시물 수정하는 함수
$('#contentUpdate').click(function() {
	//1. 게시판 번호 확인
	var boardId = $('#boardIdHidden').val(); // hidden에 숨겨둔 boardId 가져오기
	//2. json 생성
	var title = $('#upt-title').val();
	var content = $('#upt-content').val();

	if (title == '' || content == '') {
		alert('공백은 안되요.');
		return false;
	}

	var jsondata = {
		boardId: boardId,
		title: title,
		content: content,
	};
	$.ajax({
		url: '/api/v1/board/boardId/' + boardId,
		type: 'PATCH',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(jsondata),
		success: (response) => {
			if (response > 0) {
				alert("수정되었습니다.")
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10)
			}
		}
	});
});

//게시물 삭제하는 함수
$('#contentDelete').click(function() {
	let boardId = $('#boardIdHidden').val();

	if (confirm("해당 게시물을 정말 삭제하기겠습니까?")) {

		$.ajax({
			url: '/api/v1/board/boardId/' + boardId,
			type: 'DELETE',
			dataType: 'json',
			success: (response) => {
				if (response > 0) {
					alert("삭제되었습니다.")
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10)
				}
			},
		});
	}
});

function getSearchFirstPage(pageNum, pageSize) {
	var search = $('#searchBar').val().trim();
	$('#keyword').val(search);
	var keyword = $('#keyword').val();
	location.href = "/board/search?writer=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
}

function getSearchPage(writer, pageNum, pageSize) {
	location.href = "/board/search?writer=" + writer + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
}



$('#searchBar').keyup(function(key) {
	//13은 엔터를 의미
	var pageNum = 1;
	var pageSize = 10;

	if (key.keyCode == 13) {
		var search = $('#searchBar').val().trim(); //input에 작성한 작성자를 가져옴.
		if (search != '') {
			location.href = "/board/search?writer=" + search + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
		}

	}
});