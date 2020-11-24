CREATE DATABASE board;
use board;

CREATE TABLE board (
	id bigint(20) unsigned primary key auto_increment not null,
	name varchar(32) not null,
	content text not null	
);