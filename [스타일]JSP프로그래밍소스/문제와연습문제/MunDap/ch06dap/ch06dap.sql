
/*
 * 
 * �� 4��
 */CREATE TABLE COMPANY
(
	EMPNO INT NOT NULL,
	ENAME VARCHAR(20),
	HOBBY VARCHAR(20) DEFAULT '����'
)
insert into COMPANY values(1111,'Dominico','��ȭ����');
insert into COMPANY values(2222,'Dominica','��ȭ����');
insert into COMPANY values(3333,'Millo','����');
insert into COMPANY values(4444,'Follio','����');
select * from COMPANY;
/* �� 5�� */
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

/* �� 6 */
create table empList
(
empno  number,
ename varchar2(15),
job      varchar2(14),
addr varchar2(50),
tell   varchar2(20),
sal    number);
insert into empList values (1111,'�̱浿','����','����','02-0000-0000',450);
insert into empList values (2222,'���浿','��ȹ','����','02-0000-2222',350);
insert into empList values (3333,'Ȳ�浿','����','�뱸','053-0000-3333',250);
insert into empList values (4444,'�ֱ浿','�λ�','��õ','032-0000-4444',560);
insert into empList values (5555,'��浿','ȫ��','����','02-0000-5555',400);

/*06-1 empList���� ������ �μ��� �̸���  ������ ���Ͽ���*/

   select ename,sal  from EMPLIST  where job='����' 


   /*06-2 ���￡ �ٹ��ϴ� ����� ����϶�*/
   
    select * from emplist where addr='����'

/*06-3 ������ 300���� 400 ���̸� �޴� ����� ����� �̸��� �������*/

select * from emplist where sal between  300 and 400;


/*���չ��� */
create table dept(deptno number(3),
                        dname  varchar2(10),
                        loc       varchar2(10),
                  constraint dept_dname_pk primary key(deptno),
                  constraint dept_dname_uq unique(dname));

 insert into dept values(10,'�ѹ���','����');
insert into dept values(20,'������','����');
insert into dept values(30,'��ȹ��','�λ�');
insert into dept values(40,'�λ��','�뱸');



