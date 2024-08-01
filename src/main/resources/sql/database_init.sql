create table accounts(
                         id bigserial primary key,
                         money int,
                         is_active bool
);

create table users(
    id bigserial primary key,
    username varchar(20) not null unique,
    password varchar(100) not null,
    role varchar(10) not null,
    account_id bigint,
    constraint fk_account_id
        foreign key(account_id) references accounts(id)
);