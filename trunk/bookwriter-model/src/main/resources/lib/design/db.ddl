create table part (bo_id int, pa_id int not null auto_increment, pa_datetime timestamp, pa_text text, us_id int, primary key (pa_id)) type=InnoDB CHARACTER SET UTF8;
create table user (us_id int not null auto_increment, us_name varchar(30) not null, us_pass varchar(100) not null, us_status int, us_login varchar(30) not null unique, primary key (us_id)) type=InnoDB CHARACTER SET UTF8;
create table comment (co_id int not null auto_increment, us_id int, co_datetime timestamp, co_text text, primary key (co_id)) type=InnoDB CHARACTER SET UTF8;
create table book (bo_id int not null auto_increment, bo_title varchar(255) not null unique, bo_about text, author varchar(255), primary key (bo_id)) type=InnoDB CHARACTER SET UTF8;
alter table comment add index FKcomment144187 (us_id), add constraint FKcomment144187 foreign key (us_id) references user (us_id);
alter table part add index FKpart178140 (us_id), add constraint FKpart178140 foreign key (us_id) references user (us_id);
alter table part add index FKpart276408 (bo_id), add constraint FKpart276408 foreign key (bo_id) references book (bo_id);
