create table users(
    id bigserial primary key,
    username varchar(20) not null unique,
    password varchar(100) not null,
    role varchar(10) not null
);