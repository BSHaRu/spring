DROP TABLE IF EXISTS test_board;

-- TEST BOARD
CREATE TABLE IF NOT EXISTS test_board(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(50),
	content TEXT,
	writer VARCHAR(50),
	regdate TIMESTAMP default now(),
	viewcnt int default 0
);

INSERT INTO test_board 
SELECT title,content,writer FROM test_board;

SELECT * FROM test_board;

DROP TABLE IF EXISTS test_sboard;

-- SEARCH BOARD
CREATE TABLE IF NOT EXISTS test_sboard(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(200) NOT NULL,
	content text NOT NULL,
	writer VARCHAR(50) NOT NULL,
	regdate TIMESTAMP default now(),
	updatedate TIMESTAMP default now(),
	viewcnt int default 0
);

INSERT INTO test_sboard(title,content,writer)
select title,content,writer FROM test_sboard;

SELECT * FROM test_sboard;


CREATE TABLE IF NOT EXISTS test_member(
	userid VARCHAR(50) NOT NULL,
	userpw VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(50) NULL,
	regdate TIMESTAMP NOT NULL default now(),
	updatedate TIMESTAMP NOT NULL default now()	
);

SELECT * FROM test_member;


CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,
    u_id VARCHAR(50) NOT NULL UNIQUE,
    u_pw VARCHAR(200) NOT NULL,
    u_profile VARCHAR(200) NULL,
    u_phone VARCHAR(20) NOT NULL,
    u_name VARCHAR(20) NOT NULL,
    u_birth VARCHAR(20) NOT NULL,
    u_post VARCHAR(50),
    u_addr VARCHAR(100),
    u_addr_detail VARCHAR(100),
    u_point INT default 0,
    u_info char(1) default 'y',
    u_date TIMESTAMP NOT NULL default now(),
    u_visit TIMESTAMP NOT NULL default now(),
    u_withdraw char(1) default 'n'
);

-- 권한 table
CREATE TABLE validation_member_auth(
	u_id VARCHAR(50) NOT NULL,
    u_auth VARCHAR(50) NOT NULL,
    constraint fk_member_auth FOREIGN KEY(u_id)
    REFERENCES validation_member(u_id)
);


CREATE TABLE mvc_member(
	num INT primary key auto_increment,
    id VARCHAR(50) UNIQUE NOT NULL,
    pass VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    age INT(3) default 0,
    gender VARCHAR(10),
    joinYN char(1) default 'Y',
    regdate TIMESTAMP default now(),
    updatedate TIMESTAMP default now()
);

SELECT * FROM mvc_member;

commit;
SELECT * FROM validation_member;

-- 관리자
INSERT INTO validation_member_auth
VALUES('as@as.as', 'ROLE_ADMIN');
-- 매니저
INSERT INTO validation_member_auth
VALUES('as@as.as', 'ROLE_MEMBERSHIP');
-- 일반 회원
INSERT INTO validation_member_auth
VALUES('as@as.as', 'ROLE_USER');

SELECT * FROM validation_member;
commit;

SELECT u_id, u_withdraw FROM validation_member;

