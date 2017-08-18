CREATE TABLE db_log (
  request_uri varchar(50),
  remote_address varchar(50) ,
  server_name varchar(60) ,
  session_id varchar(80)

 )

 select * from db_log;
 
 drop table db_log;