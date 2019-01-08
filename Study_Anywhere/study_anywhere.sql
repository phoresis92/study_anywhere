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
    room_name varchar(30) not null,
	room_pass varchar(30) not null,
	user_id varchar(20) not null,
    indata TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(room_name)
);


select member_pw, member_email from member where member_id = 'qwer';