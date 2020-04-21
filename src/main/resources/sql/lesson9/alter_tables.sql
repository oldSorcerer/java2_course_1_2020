create table auth_details (
 id serial primary key,  -- serial -> biging default nextval('<seq_name>')
 login varchar not null unique,
 password varchar not null
);

-- nextval('users_id_seq') -> next ID
alter table users
 -- alter column
 -- drop column
 -- add column
 -- drop constraint <constraint name>
 alter column id type integer;

alter table users
 alter column id drop default;
-- drop table
-- drop database
drop sequence users_id_seq;

insert into auth_details (id, login, password) select id, concat(id, name), last_name from users;

-- max(id) users
-- table_name_field_name_seq
select setval('auth_details_id_seq', (select max(id) from users) + 1);

alter table users add constraint users_auth_details_id_fkey foreign key (id) references auth_details(id);

-- nextval
-- setval
-- currval
select currval('auth_details_id_seq')