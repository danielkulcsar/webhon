create  table  board
(
   no number(10) ,  
   name   varchar2(20),
   email   varchar2(20),
   homepage   varchar2(20),
   regist_date  date ,
   contents    varchar2(2000),
constraint   pk_board  primary key(no)
)
/*  시퀀스 생성 */
create sequence seq_board
increment by 1
start with 1
maxvalue 100000
cycle
nocache


insert into board values(1,'Dominica','Dominica@a.net','http://www.a.com','2010/1/1','언제나 팟팅하는  사이트가 되세요');
insert into board values(2,'Dominico','Dominico@b.net','http://www.b.com','2010/1/2','오늘 저녁 한번 모여요');
insert into board values(3,'puppy','puppy@c.net','http://www.c.com','2010/1/3','과제는 제출했습니다');
insert into board values(4,'ruri','ruri@d.net','http://www.d.com','2010/1/4','메뉴가 변경되었네요');

delete  board;

drop table board;
select * from board;
