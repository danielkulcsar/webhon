
/*
 * 
 * 평가 4번
 */CREATE TABLE COMPANY
(
	EMPNO INT NOT NULL,
	ENAME VARCHAR(20),
	HOBBY VARCHAR(20) DEFAULT '없음'
)
insert into COMPANY values(1111,'Dominico','영화관람');
insert into COMPANY values(2222,'Dominica','영화관람');
insert into COMPANY values(3333,'Millo','공부');
insert into COMPANY values(4444,'Follio','게임');
select * from COMPANY;
/* 평가 5번 */
CREATE TABLE Region
(
	RegionID INT CONSTRAINT PK_R_RID PRIMARY KEY,
	Region VARCHAR(20)
)

CREATE TABLE Customer
(
	OrderNumber INT CONSTRAINT UK_C_ORNUM UNIQUE,
	Customer_Name VARCHAR(20),
	RegionID INT CONSTRAINT FK_C_RID FOREIGN KEY REFERENCES Region.RegionID
)
select * from Customer;

/* 평가 6 */
create table empList
(
empno  number,
ename varchar2(15),
job      varchar2(14),
addr varchar2(50),
tell   varchar2(20),
sal    number);
insert into empList values (1111,'이길동','영업','서울','02-0000-0000',450);
insert into empList values (2222,'정길동','기획','서울','02-0000-2222',350);
insert into empList values (3333,'황길동','영업','대구','053-0000-3333',250);
insert into empList values (4444,'최길동','인사','인천','032-0000-4444',560);
insert into empList values (5555,'김길동','홍보','서울','02-0000-5555',400);

/*06-1 empList에서 영업부 부서의 이름과  봉급을 구하여라*/

   select ename,sal  from EMPLIST  where job='영업' 


   /*06-2 서울에 근무하는 사람을 출력하라*/
   
    select * from emplist where addr='서울'

/*06-3 봉급이 300에서 400 사이를 받는 사람의 사번과 이름을 출력하자*/

select * from emplist where sal between  300 and 400;


/*종합문제 */
create table dept(deptno number(3),
                        dname  varchar2(10),
                        loc       varchar2(10),
                  constraint dept_dname_pk primary key(deptno),
                  constraint dept_dname_uq unique(dname));

 insert into dept values(10,'총무부','서울');
insert into dept values(20,'영업부','대전');
insert into dept values(30,'기획부','부산');
insert into dept values(40,'인사부','대구');



