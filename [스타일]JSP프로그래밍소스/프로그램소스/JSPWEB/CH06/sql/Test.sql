

/* TTEST  table create */
CREATE TABLE  TTEST
(NO NUMBER(10) Primary key, NAME VARCHAR(10));

/* AGE Į�� �߰� */
ALTER TABLE TTEST
	ADD (AGE  number(10));

	/*  NO Number  Į�� ���� */
	ALTER TABLE TTEST
	MODIFY  (NO varchar(20) NOT NULL);
/* Į�� ���� ���� ���� */
ALTER TABLE TTEST
	DROP CONSTRAINT  SYS_C004043;
/* Į�� ���� */
	ALTER TABLE TTEST
	DROP COLUMN AGE;


	/* ���̺� ���� */
	Drop table TTest;
	
/* ���α׷� 6-1 */ 
	create table addrlist(
 name varchar2(10), age number(3), tel varchar2(16)
);

insert into addrlist values('ȫ�浿',22,'02-111-1234');
insert into addrlist values('�̱浿',33,'02-222-1234');
insert into addrlist values('���浿',28,'02-333-1234');
insert into addrlist values('�ڱ浿',27,'02-444-1234');
insert into addrlist values('�ֱ浿',28,'02-555-1234');

SELECT NAME, AGE, TEL
  FROM WEBPRO.ADDRLIST

  /*���α׷� 6-2 */
  /* ���̺� ��ü ���� ����ϱ� */
  select * from ADDRLIST;
  
   /* age�� 30 ������ �������  ������������ ����غ��� */
  SELECT * FROM  ADDRLIST
  WHERE AGE <=30 
  ORDER BY NAME desc;

 /*[���α׷� 6-3]UPDATE������ �̿��ؼ� ���ڱ浿����  ADDRLIST ���̺��� �����浿������ 
�����غ��� 
*/
  
  UPDATE ADDRLIST
SET NAME='���浿'	
WHERE NAME='�ڱ浿';

SELECT * FROM ADDRLIST;
  

/*  UPDATE������ �̿��ؼ� �̸��� ��ȫ�浿���� ���̸� 30����  �����϶�.*/

 UPDATE ADDRLIST
SET AGE=30	
WHERE NAME='ȫ�浿';

/*DELETE ������ �̿��ؼ� 'ȫ�浿'�� ã�� �����غ���*/
DELETE FROM ADDRLIST
WHERE NAME='ȫ�浿';
