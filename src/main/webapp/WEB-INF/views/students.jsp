<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/static/css/board.css" />
  </head>
  <body>
    <div class="container">
      <!-- 학생 정보 등록 -->
      <div class="write-popup">
        <div class="editor">
          <div class="input-box">
            <label for="studentsName">학생 이름 : </label>
            <input id="Name" type="text" placeholder="이름을 입력하세요..." />
          </div>
          <div class="input-box">
            <label for="Password">학생 비밀번호 : </label>
            <input
              id="Password"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="btn-area">
            <a href="#" class="btn-cancel">취소</a>
            <a id="contentSubmit" href="#" class="btn-success">등록</a>
          </div>
        </div>
      </div>
      <div class="update-popup">
        <div class="editor">
          <div class="close">
            <a href="#" class="btn-close">닫기</a>
          </div>
          <div class="input-box">
            <label for="studentsId">학생 아이디 : </label>
            <input
              id="studentsId"
              type="text"
              placeholder="아이디 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsName">학생 이름 : </label>
            <input
              id="studentsName"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsPassword">학생 비밀번호 : </label>
            <input
              id="studentsPassword"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="btn-area">
            <input id="boardIdHidden" type="hidden" />
            <a id="contentUpdate" href="#" class="btn-update">수정</a>
            <a id="contentDelete" href="#" class="btn-delete">삭제</a>
          </div>
        </div>
      </div>
      <div class="navigation">
        <ul>
          <li>
            <a href="#">
              <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
              <span class="title">DW Board</span>
            </a>
          </li>
          <li>
          	<a href="/board?pageNum=1&pageSize=10">
              <span class="icon"
                ><ion-icon name="home-outline"></ion-icon
              ></span>
              <span class="title">Dashboard</span>
            </a>
          </li>
          <li>
          	<a href="/students">
              <span class="icon"
                ><ion-icon name="person-outline"></ion-icon
              ></span>
              <span class="title">Students</span>
             </a>
          </li>
          <li>
            <a href="/logs">
              <span class="icon"
                ><ion-icon name="lock-closed-outline"></ion-icon
              ></span>
              <span class="title">logs</span>
            </a>
          </li>
          <li>
            <a href="#">
              <span class="icon"
                ><ion-icon name="log-out-outline"></ion-icon
              ></span>
              <span class="title">Sign Out</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
    <!-- main -->
    <div class="main">
      <div class="topbar">
        <div class="toggle">
          <!-- toggle은 나중에 만들기 -->
          <ion-icon name="menu-outline"></ion-icon>
        </div>
        <!-- search -->
        <div class="search">
          <label>
            <input
              id="searchBar"
              type="text"
              placeholder="학생이름을 검색하세요..."
            />
          </label>
        </div>
        <div>
          <a href="#" class="logout">Logout</a>
        </div>
      </div>
      <!-- table -->
      <div class="details">
        <div class="recentOrders">
          <div class="cardHeader">
            <h2>학생 명단</h2>
            <a href="#" class="btn">학생 추가</a>
          </div>
          <table>
            <thead>
              <tr>
                <th>학생 아이디</th>
                <th>학생 이름</th>
                <th>가입 날짜</th>
              </tr>
            </thead>
            <tbody id="boardData">
              <!-- <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr>
              <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr>
              <tr>
                <td>hyunsangwon</td>
                <td>현상원</td>
                <td>2022-05-19</td>
              </tr> -->
            </tbody>
          </table>
          <div class="pagination">
            <!-- <a href="#">Previous</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">Next</a> -->
          </div>
        </div>
      </div>
    </div>
  </body>
  <script
    type="module"
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
  ></script>
  <script
    nomodule
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
  ></script>
  <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"
  ></script>
  <script>
    let list = document.querySelectorAll('.navigation li');
    function activeLink() {
      list.forEach((item) => {
        item.classList.remove('hovered');
      });
      this.classList.add('hovered');
    }
    list.forEach((item) => {
      item.addEventListener('mouseover', activeLink);
    });
  </script>
  <script>
    $('.btn-close').click(function () {
      $('.update-popup').css('display', 'none');
    });

    getStudentsList(1, 10);

    function getStudentsList(pageNum, pageSize) {
      $.ajax({
        url:
          'http://localhost:8080/api/v1/students?pageNum=' +
          pageNum +
          '&pageSize=' +
          pageSize,
        type: 'GET',
        dataType: 'json',
        success: (response) => {
          console.log(response);
          let html = '';
          let len = response.list.length;
          if (len > 0) {
            for (let i = 0; i < len; i++) {
              html +=
                '<tr onclick="getStudents(' +
                response.list[i].studentsId +
                ')"><td>' +
                response.list[i].studentsId +
                '</td><td>' +
                response.list[i].studentsName +
                '</td><td>' +
                response.list[i].createAt +
                '</td></tr>';
            }

            let paginationHtml = '';
            if (response.hasPreviousPage) {
              paginationHtml +=
                '<a onclick="getStudentsList(' +
                (response.pageNum - 1) +
                ',' +
                pageSize +
                ')" href="#">Previous</a>';
            }
            for (let i = 0; i < response.navigatepageNums.length; i++) {
              paginationHtml +=
                '<a id="pageNum' +
                response.navigatepageNums[i] +
                '" onclick="getStudentsList(' +
                response.navigatepageNums[i] +
                ',' +
                pageSize +
                ')" href="#">' +
                response.navigatepageNums[i] +
                '</a>';
            }
            if (response.hasNextPage) {
              paginationHtml +=
                '<a onclick="getStudentsList(' +
                (response.pageNum + 1) +
                ',' +
                pageSize +
                ')" href="#">Next</a>';
            }
            $('.pagination').children().remove();
            $('.pagination').append(paginationHtml);

            $('#pageNum' + pageNum).css('backgroundColor', '#287bff');
            $('#pageNum' + pageNum).css('color', '#fff');
          } else {
            html +=
              '<tr><td colspan=3 style="text-align:center">게시글이 없습니다.</td></tr>';
          }
          $('tbody').children().remove();
          $('tbody').append(html);
        },
      });
    }
    //학생정보 불러오기
    function getStudents(studentsId) {
      console.log(studentsId);
      $('.update-popup').css('display', 'block');

      $.ajax({
        url: 'http://localhost:8080/api/v1/students/id/' + studentsId,
        type: 'Get',
        dataType: 'json',
        success: (response) => {
          $('#studentsId').val(response.studentsId);
          $('#studentsName').val(response.studentsName);
          $('#studentsPassword').val(response.studentsPassword);
          $('#boardIdHidden').val(studentsId);
        },
      });
    }

    //학생 정보 수정
    $('#contentUpdate').click(function () {
      if (confirm('수정하시겠습니까?')) {
        let studentsId = $('#boardIdHidden').val();
        let studentsName = $('#studentsName').val();
        let studentsPassword = $('#studentsPassword').val();

        let jsondata = {
          studentsId: studentsId,
          studentsName: studentsName,
          studentsPassword: studentsPassword,
        };
        $.ajax({
          url: 'http://localhost:8080/api/v1/students/id/' + studentsId,
          type: 'PATCH',
          contentType: 'application/json',
          dataType: 'json',
          data: JSON.stringify(jsondata),
          success: (response) => {
            if (response > 0) {
              alert('수정 완료하였습니다.');
              location.reload();
              $('.update-popup').css('display', 'none');
            }
          },
        });
      }
    });

    //학생 삭제(탈퇴)
    $('#contentDelete').click(function () {
      if (confirm('정말 탈퇴하시겠습니까?')) {
        let studentsId = $('#boardIdHidden').val();

        $.ajax({
          url: 'http://localhost:8080/api/v1/students/id/' + studentsId,
          type: 'DELETE',
          dataType: 'json',
          success: (response) => {
            if (response > 0) {
              alert('삭제하였습니다.');
              location.reload();
              getStudentsList(1, 10);
              $('.update-popup').css('display', 'none'); // 게시물 상세 화면 감추기
            }
          },
        });
      }
    });

    getStudentsSearch(1, 5);
    //검색 페이징 구현(1)
    function getStudentsSearch(pageNum, pageSize) {
      $('#searchBar').keyup((key) => {
        if (key.keyCode == 13) {
          let search = $('#searchBar').val();
          console.log(search);
          getStudentsClick(1, 5);
        }
      });
    }
    // 검색 페이징 구현(2)
    function getStudentsClick(pageNum, pageSize) {
      let search = $('#searchBar').val();
      $.ajax({
        url:
          'http://localhost:8080/api/v1/students/search?studentsName=' +
          search +
          '&pageNum=' +
          pageNum +
          '&pageSize=' +
          pageSize,
        type: 'Get',
        dataType: 'json',
        success: (response) => {
          console.log(response);
          let html = '';
          if (response.list.length > 0) {
            for (let i = 0; i < response.list.length; i++) {
              html +=
                '<tr onclick="getStudents(' +
                response.list[i].studentsId +
                ')"><td>' +
                response.list[i].studentsId +
                '</td><td>' +
                response.list[i].studentsName +
                '</td><td>' +
                response.list[i].createAt +
                '</td></tr>';
            }
            let paginationHtml = '';
            if (response.hasPreviousPage) {
              paginationHtml +=
                '<a onclick="getStudentsClick(' +
                (response.pageNum - 1) +
                ',' +
                pageSize +
                ')" href="#">Previous</a>';
            }
            for (let i = 0; i < response.navigatepageNums.length; i++) {
              paginationHtml +=
                '<a id="Num' +
                response.navigatepageNums[i] +
                '" onclick="getStudentsClick(' +
                response.navigatepageNums[i] +
                ',' +
                pageSize +
                ')" href="#">' +
                response.navigatepageNums[i] +
                '</a>';
            }
            if (response.hasNextPage) {
              paginationHtml +=
                '<a onclick="getStudentsClick(' +
                (response.pageNum + 1) +
                ',' +
                pageSize +
                ')" href="#">Next</a>';
            }
            $('.pagination').children().remove();
            $('.pagination').append(paginationHtml);

            $('#Num' + pageNum).css('backgroundColor', '#287bff');
            $('#Num' + pageNum).css('color', '#fff');
          } else {
            html +=
              '<tr><td colspan=3 style="text-align:center">게시글이 없습니다.</td></tr>';
          }
          $('tbody').children().remove();
          $('tbody').append(html);
        },
      });
    }

    $('.btn').click(function () {
      $('.write-popup').css('display', 'block');
    });
    $('.btn-cancel').click(function () {
      $('.write-popup').css('display', 'none');
      // $('#studentsId').val('');
      $('#Name').val('');
      $('#Password').val('');
    });
    $('.btn-success').click(function () {
      let studentsName = $('#Name').val();
      let studentsPassword = $('#Password').val();

      let jsonData = {
        studentsName: studentsName,
        studentsPassword: studentsPassword,
      };
      $.ajax({
        url: 'http://localhost:8080/api/v1/students',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: (data) => {
          console.log(data);
          if (data > 0) {
            $('.write-popup').css('display', 'none');
            $('#Name').val('');
            $('#Password').val('');
            $('tbody').children().remove();
            getStudentsList(1, 10);
          }
        },
      });
    });
  </script>
</html>
