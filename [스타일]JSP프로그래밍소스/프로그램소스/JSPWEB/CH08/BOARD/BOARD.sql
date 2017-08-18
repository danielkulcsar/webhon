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
/*  ������ ���� */
create sequence seq_board
increment by 1
start with 1
maxvalue 100000
cycle
nocache


insert into board values(1,'Dominica','Dominica@a.net','http://www.a.com','2010/1/1','������ �����ϴ�  ����Ʈ�� �Ǽ���');
insert into board values(2,'Dominico','Dominico@b.net','http://www.b.com','2010/1/2','���� ���� �ѹ� �𿩿�');
insert into board values(3,'puppy','puppy@c.net','http://www.c.com','2010/1/3','������ �����߽��ϴ�');
insert into board values(4,'ruri','ruri@d.net','http://www.d.com','2010/1/4','�޴��� ����Ǿ��׿�');

delete  board;

drop table board;
select * from board;
