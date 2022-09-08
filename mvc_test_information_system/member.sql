-- test member
CREATE TABLE IF NOT EXISTS test_member(
	userid VARCHAR(50) NOT NULL,
	userpw VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(50) NULL,
	regdate TIMESTAMP NOT NULL default now(),
	updatedate TIMESTAMP NOT NULL default now()	
);

SELECT * FROM test_member;