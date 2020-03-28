-- users: id, login, name, password
-- text - для строк, у которых длина больше 255
-- primary key (первичный ключ)
-- serial - bigint unsigned auto_increment
-- primary key - not null unique
create table if not exists users (
    id serial primary key,
    login varchar(30) not null unique,
    name varchar(100) not null,
    password varchar(100) not null
);
-- 2018-06-08T10:26:26
insert into users (login, name, password) values
    ('admin', 'Oleg', 'admin');

insert into users (name, login, password) values
    ('Ivan', 'ivanroot', 'root'),
    ('Petr', 'petya', 'qwerty'),
    ('Dazdranagon', 'dazdranagon', 'dazdranagon');

update users set password = 'admin2' where login = 'admin' and id = 1;

delete from users where name = 'Petr';

select * from users order by id, name desc, login;

create table user_details (
    -- id serial primary key,
    user_id integer primary key,
    last_name varchar(100),
    age integer not null,
    email varchar(100) not null,
    constraint unique_email unique (email),
    constraint user_id_fkey foreign key (user_id) references users(id)
);

create table job_list (
    id serial primary key,
    user_id integer not null,
    company_name varchar(100) not null,
    company_address varchar not null,
    position varchar not null,
    constraint user_id_fkey foreign key (user_id) references users(id)
);

insert into user_details (user_id, last_name, age, email) values
    (1, 'Olegov', 43, 'oleg-admin@yandex.ru');
