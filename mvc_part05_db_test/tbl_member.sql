DROP TABLE tbl_member; 

CREATE TABLE IF NOT EXISTS tbl_member(
	userid VARCHAR(50),
    userpw VARCHAR(45),
    username VARCHAR(45),
    regdate timestamp default now(),
    updatedate timestamp default now()
);

DESC tbl_member;
commit;

SELECT * FROM tbl_member;
rollback;

CREATE TABLE tbl_board(
	bno int PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT NULL,
    writer VARCHAR(20) NOT NULL,
    regdate TIMESTAMP NOT NULL default now(),
    viewcnt INT default 0
);

DESC tbl_board;
commit;

SELECT * FROM tbl_board;
rollback;

INSERT INTO tbl_board(title, content, writer)
SELECT title, content, writer FROM tbl_board;

INSERT INTO notice_board(notice_category, notice_author, notice_title, notice_content )
SELECT notice_category, notice_author, notice_title, notice_content  FROM notice_board;


SELECT * FROM tbl_board
WHERE title LIKE '%배고파%'
ORDER BY bno DESC
limit 0, 30;

SELECT * FROM tbl_board
ORDER BY bno DESC;
DROP TABLE tbl_comment;
CREATE TABLE tbl_comment(
	cno INT PRIMARY KEY auto_increment,
    bno INT NOT NULL default 0,
    commentText TEXT NOT NULL,
    commentAuth VARCHAR(50) NOT NULL,
    regdate TIMESTAMP NOT NULL default now(),
    updatedate TIMESTAMP NOT NULL default now(),
	CONSTRAINT fk_tbl_bno FOREIGN KEY(bno) REFERENCES tbl_board(bno),
    INDEX(bno)				-- 검색 속도 향상을 위해서 index 지정해주는거임 
);

SELECT * FROM tbl_board WHERE bno = 1;

commit;

SELECT * FROM tbl_comment WHERE bno = 1;

-- 제약 조건 확인
SELECT * FROM information_schema.table_constraints
WHERE table_name = 'tbl_comment';

ALTER TABLE tbl_comment DROP FOREIGN KEY fk_tbl_bno;

-- 해당 원본 게시글(bno)가 삭제되면 거기에 있는 댓글들도 같이 삭제 해줘라
ALTER TABLE tbl_comment ADD CONSTRAINT fk_tbl_bno
FOREIGN KEY(bno) REFERENCES tbl_board(bno)
ON DELETE CASCADE;

DELETE FROM tbl_board WHERE bno = 1;





