-- // -------------------------------------------------------------
-- 게시판 테이블 생성 쿼리
create table mfboard(
    number int unsigned not null auto_increment primary key,
    name varchar(12) not null,
    password varchar(16) not null,
    email varchar(50) not null,
    homepage varchar(60) not null,
    subject varchar(60) not null,
    memo text not null,
    count smallint unsigned not null,
    ip varchar(15) not null,
    writetime int not null
);

-- // -------------------------------------------------------------
-- 회원 테이블 생성 쿼리
create table member(
    memberid varchar(12) not null primary key,
    membername varchar(12) not null,
    memberpass varchar(12) not null,
    membermail varchar(50) ,
    memberurl varchar(50),
    lastlogin int not null
);
