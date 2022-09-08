-- AOP TEST TABLE
CREATE TABLE tbl_user(
	uno INT PRIMARY KEY auto_increment,
    uid VARCHAR(50) NOT NULL UNIQUE,
    upw VARCHAR(50) NOT NULL,
    uname VARCHAR(50) NOT NULL,
    upoint INT NOT NULL default 0
);

CREATE TABLE tbl_message(
	mno INT PRIMARY KEY auto_increment,			
    targetid VARCHAR(50) NOT NULL,				-- 수신자
    sender VARCHAR(50) NOT NULL,				-- 발신자
    message TEXT NOT NULL,						-- 메세지
    opendate TIMESTAMP NULL,					-- 메세지 읽은 시간
    senddate TIMESTAMP NOT NULL default now(),	-- 발신시간
    FOREIGN KEY(targetid) REFERENCES tbl_user(uid),
    FOREIGN KEY(sender) REFERENCES tbl_user(uid)
);

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id001','pw001','ppp');

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id002','pw002','www');

INSERT INTO tbl_user(uid, upw, uname)
VALUES('id003','pw003','zzz');

commit;

SELECT * FROM tbl_user;

SELECT * FROM tbl_message;

rollback;

CREATE TABLE ban_ip(
	ip VARCHAR(50) primary key,
    cnt INT DEFAULT 1,
    bandate TIMESTAMP DEFAULT now()
);

commit;

-- 답변형 게시판 table
CREATE TABLE re_tbl_board(
	bno INT PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    origin INT NOT NULL DEFAULT 0,
    depth INT NOT NULL DEFAULT 0,
    seq INT NOT NULL DEFAULT 0,
    regdate TIMESTAMP NOT NULL DEFAULT NOW(),
    updatedate TIMESTAMP NOT NULL DEFAULT NOW(),
    viewcnt INT NOT NULL DEFAULT 0,
    showboard char(1) DEFAULT 'y',
    uno INT NOT NULL,
    CONSTRAINT fk_re_tbl_uno FOREIGN KEY(uno)
    REFERENCES tbl_user(uno)
);

SELECT * FROM re_tbl_board;

-- uname을 우린 writer라고 지정했기때문에 별칭으로 writer를 달아줘야 알아먹음
SELECT R.*, U.uname AS writer FROM 
re_tbl_board AS R NATURAL JOIN tbl_user AS U
ORDER BY R.origin DESC, R.seq ASC limit 0, 15;

-- 첨부파일 목록 table
CREATE TABLE tbl_attach(
	fullName VARCHAR(200) NOT NULL,
    bno INT NOT NULL,
    regdate TIMESTAMP default now(),
    CONSTRAINT fk_tbl_attach FOREIGN KEY(bno)
    REFERENCES tbl_board(bno)
);

commit;

SELECT * FROM tbl_attach;
SELECT * FROM re_tbl_board ORDER by bno DESC;

-- 
SELECT now();
SELECT sysdate();
SELECT curdate();
SELECT curtime();

-- date_add(기준시간, interval x, 계산시간) : 기준시간 기준으로 계산시간에 x만큼 더해줌(x가 음수면 빼줌)
SELECT date_add(now(), interval -1 day);

SELECT fullName FROM tbl_attach
WHERE regdate >= (CURDATE() - interval 1 day);

-- regdate를 년-월-일 만 표시하게 바꿈
SELECT DATE_FORMAT(regdate, "%Y-%m-%d") FROM tbl_attach;

SELECT fullName FROM tbl_attach
WHERE
DATE_FORMAT(regdate,"%Y-%m-%d")
= date_format(date_sub(now(), interval 1 day), "%Y-%m-%d");



