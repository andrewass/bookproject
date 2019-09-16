-- Create tables
create table t_user
(
    user_id  bigint(20) auto_increment not null ,
    username varchar(50),
    password varchar(50),
    primary key (user_id)
);


-- Insert data

insert into t_user(username, password)
values ('JohnDoe','1234password');

