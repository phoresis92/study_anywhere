CREATE TABLE member(
	member_id VARCHAR(50) NOT NULL,
	member_pw VARCHAR(50) NOT NULL,
	member_temppass VARCHAR(50),
	member_settemp int,
	member_email VARCHAR(50) NOT NULL,
	member_checked int DEFAULT 0,
	member_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	member_suspended DATE, 
	PRIMARY KEY(member_id)
);

insert into member(member_id, member_pw, member_email, member_checked) values('qwer', 'qwer', 'qwer@qwer.com', 1);

create table room(
    roomname varchar(30) not null,
	roompass varchar(30) not null,
	userid varchar(20) not null,
    indata TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(roomname)
);

insert into room values('lobby','1234','lobby',now());


create table calendar(
calnum int auto_increment not null,
roomname varchar(30) not null,
member_id varchar(50) not null,
caldate1 timestamp not null default CURRENT_TIMESTAMP,
caldate2 timestamp not null default CURRENT_TIMESTAMP,
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
    member_id VARCHAR(50) NOT NULL,
	chief varchar(50),
    primary key (member_id),
    constraint chatlist_member_id_fk foreign key(member_id)
    references member(member_id) on delete cascade    
    );
	
	
	CREATE TABLE board(
	member_id VARCHAR(50) NOT NULL,
	board_num INT auto_increment not null,
	board_subject VARCHAR(100) NOT NULL,
	board_content VARCHAR(2000) NOT NULL,
	board_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	board_readcount int default 0 not null,
	roomname varchar(30) not null,
	PRIMARY KEY(board_num),
	CONSTRAINT board_member_id_fk FOREIGN KEY(member_id)
	REFERENCES member(member_id) ON DELETE CASCADE,
	constraint board_roomname_fk foreign key(roomname)
	references room(roomname) on delete cascade
	);
	
	insert into board(member_id, board_subject, board_content, roomname ) values('aa','title','content','1234');
	
	CREATE TABLE board_comment (
	board_num int NOT NULL,
	member_id VARCHAR(50) NOT NULL,
	comment_num int auto_increment not null,
	comment_con VARCHAR(1000) NOT NULL,
	comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(comment_num),
	CONSTRAINT FK_COMMENT_MEMBER FOREIGN KEY(member_id)
	REFERENCES member(member_id) ON DELETE CASCADE,
	CONSTRAINT FK_COMMENT_ARTICLE FOREIGN KEY(board_num)
	REFERENCES board(board_num) ON DELETE CASCADE
);

insert into board_comment(board_num, member_id, comment_con) values('70','aa','asdf');
select max(comment_num) from board_comment;



