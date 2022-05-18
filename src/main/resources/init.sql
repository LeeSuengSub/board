<<<<<<< HEAD
-- DW라는 데이터베이스가 없으면 만드는 방법.
=======
-- DW라는 데이터베이스가 없으면 만드는 방법
>>>>>>> refs/remotes/origin/main
-- CREATE database IF NOT EXISTS DW DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- USE DW;

-- 학생 테이블
CREATE TABLE IF NOT EXISTS students(
    students_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '학생 아이디',
    students_name VARCHAR(20) COMMENT '학생 이름',
    students_password VARCHAR(200) COMMENT '학생 비밀번호',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입 날짜'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 게시판 테이블
CREATE TABLE IF NOT EXISTS board
(
    board_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 아이디',
    students_id INTEGER(4) COMMENT '학생 아이디',
    title VARCHAR(50) COMMENT '제목',
    content VARCHAR(100) COMMENT '글 내용',
    update_at DATETIME COMMENT '수정 날짜',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성 날짜',
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;