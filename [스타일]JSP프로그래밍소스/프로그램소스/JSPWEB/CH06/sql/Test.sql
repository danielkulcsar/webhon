

/* TTEST  table create */
CREATE TABLE  TTEST
(NO NUMBER(10) Primary key, NAME VARCHAR(10));

/* AGE 칼럼 추가 */
ALTER TABLE TTEST
	ADD (AGE  number(10));

	/*  NO Number  칼럼 수정 */
	ALTER TABLE TTEST
	MODIFY  (NO varchar(20) NOT NULL);
/* 칼럼 제약 조건 삭제 */
ALTER TABLE TTEST
	DROP CONSTRAINT  SYS_C004043;
/* 칼럼 삭제 */
	ALTER TABLE TTEST
	DROP COLUMN AGE;


	/* 테이블 삭제 */
	Drop table TTest;
	
/* 프로그램 6-1 */ 
	create table addrlist(
 name varchar2(10), age number(3), tel varchar2(16)
);

insert into addrlist values('홍길동',22,'02-111-1234');
insert into addrlist values('이길동',33,'02-222-1234');
insert into addrlist values('정길동',28,'02-333-1234');
insert into addrlist values('박길동',27,'02-444-1234');
insert into addrlist values('최길동',28,'02-555-1234');

SELECT NAME, AGE, TEL
  FROM WEBPRO.ADDRLIST

  /*프로그램 6-2 */
  /* 테이블에 전체 내용 출력하기 */
  select * from ADDRLIST;
  
   /* age가 30 이하인 사람들을  내림차순으로 출력해보자 */
  SELECT * FROM  ADDRLIST
  WHERE AGE <=30 
  ORDER BY NAME desc;

 /*[프로그램 6-3]UPDATE구문을 이용해서 ‘박길동’을  ADDRLIST 테이블에서 ‘지길동’으로 
수행해보자 
*/
  
  UPDATE ADDRLIST
SET NAME='지길동'	
WHERE NAME='박길동';

SELECT * FROM ADDRLIST;
  

/*  UPDATE구문을 이용해서 이름이 ‘홍길동’인 나이를 30으로  갱신하라.*/

 UPDATE ADDRLIST
SET AGE=30	
WHERE NAME='홍길동';

/*DELETE 구문을 이용해서 '홍길동'을 찾아 삭제해보자*/
DELETE FROM ADDRLIST
WHERE NAME='홍길동';
