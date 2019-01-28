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
    indata TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(roomname)
);


select member_pw, member_email from member where member_id = 'qwer';


create table calendar(
	calnum int auto_increment not null,
	roomname varchar(30) not null,
	member_id varchar(50) not null,
    caldate1 timestamp not null,
    caldate2 timestamp not null,
    calevent varchar(100) not null,
    calcode int(1) default 1,
    primary key(calnum),
    constraint calendar_roomname_fk foreign key (roomname) 
    references room(roomname) on delete cascade,
    constraint calendar_member_id_fk foreign key(member_id)
    references member(member_id) on delete cascade    
    );
	
	
	create table chatlist(
	roomname varchar(30) not null,
    MEMBER_ID VARCHAR(50) NOT NULL,
    primary key (member_id),
	constraint chatlist_roomname_fk foreign key (roomname) 
    references room(roomname) on delete cascade,
    constraint chatlist_member_id_fk foreign key(member_id)
    references member(member_id) on delete cascade    
    );
	
	
	CREATE TABLE BOARD(
	MEMBER_ID VARCHAR(50) NOT NULL,
	BOARD_NUM INT auto_increment not null,
	BOARD_SUBJECT VARCHAR(100) NOT NULL,
	BOARD_CONTENT VARCHAR(2000) NOT NULL,
	BOARD_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	BOARD_READCOUNT int default 0 not null,
	roomname varchar(30) not null,
	PRIMARY KEY(BOARD_NUM),
	CONSTRAINT BOARD_MEMBER_ID_FK FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID) ON DELETE CASCADE,
	constraint board_roomname_fk foreign key(roomname)
	references room(roomname) on delete cascade
	);
	
	insert into board(member_id, board_subject, board_content, roomname ) values('aa','title','content','1234');
	
	CREATE TABLE BOARD_COMMENT (
	BOARD_NUM int NOT NULL,
	MEMBER_ID VARCHAR(50) NOT NULL,
	COMMENT_NUM int auto_increment not null,
	COMMENT_CON VARCHAR(1000) NOT NULL,
	COMMENT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(COMMENT_NUM),
	CONSTRAINT FK_COMMENT_MEMBER FOREIGN KEY(MEMBER_ID)
	REFERENCES MEMBER(MEMBER_ID) ON DELETE CASCADE,
	CONSTRAINT FK_COMMENT_ARTICLE FOREIGN KEY(BOARD_NUM)
	REFERENCES BOARD(BOARD_NUM) ON DELETE CASCADE
);

insert into board_comment(board_num, member_id, comment_con) values('70','aa','asdf');
select max(comment_num) from board_comment;



