CREATE TABLE MEMBER(
	MEMBER_ID VARCHAR(50) NOT NULL,
	MEMBER_PW VARCHAR(50) NOT NULL,
	MEMBER_TEMPPASS VARCHAR(50),
	MEMBER_SETTEMP int,
	MEMBER_EMAIL VARCHAR(50) NOT NULL,
	MEMBER_CHECKED int DEFAULT 0,
	MEMBER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MEMBER_SUSPENDED DATE, 
	PRIMARY KEY(MEMBER_ID)
);

create table room(
    roomname varchar(30) not null,
	roompass varchar(30) not null,
	userid varchar(20) not null,
	roomcurp int default 0,
    indata TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(roomname)
);


select member_pw, member_email from member where member_id = 'qwer';