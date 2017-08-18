/* 이절의 목표 테이블 */
create table Score(
 name varchar2(20) not null,kor number(4), eng number(4), mat number(4)
);

insert into Score values('Dominico',100,70,80);
insert into Score values('Dominica',100,50,60);
insert into Score values('Dell',90,80,100);
insert into Score values('Ben',100,70,80);
insert into Score values('su',100,70,80);

select * from Score;


