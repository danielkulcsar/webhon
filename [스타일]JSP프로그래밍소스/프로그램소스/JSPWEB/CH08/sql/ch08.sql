create table AddressList(
 name varchar2(20) not null,tel varchar2(20) not null, addr varchar2(20) not null
);

insert into AddressList values('Dominico','02-111-1234','seoul korea');
insert into AddressList values('Dominica','02-222-1234','seoul korea');
insert into AddressList values('Teresa','1-403-1234','Calgary');
insert into AddressList values('Milo','1-403-1234','Calgary');
insert into AddressList values('Pollio ','1-403-1234','Calgary');

select * from addresslist;
delete ADDRESSLIST;
drop table Addresslist;