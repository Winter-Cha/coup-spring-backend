CREATE DATABASE db_coup ;

create user 'admin'@'%' identified by 'qwer1234';
grant all privileges on db_coup.* to 'admin'@'%' identified by 'qwer1234';
flush privileges;