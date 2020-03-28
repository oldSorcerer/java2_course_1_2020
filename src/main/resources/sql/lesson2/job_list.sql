create table users (
	id serial primary key,
	name varchar(100) not null,
	last_name varchar(100) not null,
	passport varchar(50) not null unique
);

create table companies (
	id serial primary key,
	name varchar(100) not null,
	ein varchar(40) not null unique,
	address text not null
);

create table positions (
	id serial primary key,
	name varchar not null unique
);

create table job_list (
	user_id integer not null,
	company_id integer not null,
	position_id integer not null,
	start_date date not null,
	end_date date,
	constraint job_list_pkey primary key (user_id, company_id, position_id),
	constraint job_list_user_id_fkey foreign key (user_id) references users(id),
	constraint job_list_company_id_fkey foreign key (company_id) references companies(id),
	constraint job_list_position_id_fkey foreign key (position_id) references positions(id)
);

insert into users (name, last_name, passport) values
	((nextval('users_id_seq'), 'Oleg', 'Olegov', '5453 469764'),
	('Ivan', 'Ivanov', '5075 545665');
insert into companies (name, ein, address) values
	('R&K', '5398694056', 'SPb'),
	('Lll', '5603545653', 'Moscow');
insert into positions (name) values
	('Developer'),
	('QA'),
	('DevOps');

insert into job_list (user_id, company_id, position_id, start_date, end_date) values
	(1, 1, 1, '2015-05-12', '2017-09-18'),
	(1, 2, 3, '2017-09-19', null),
	(2, 1, 2, '2016-03-26', '2017-05-08'),
	(2, 1, 1, '2017-05-09', '2018-08-29'),
	(2, 2, 1, '2018-08-30', null);

select jl.start_date from job_list jl;

select * from users u, companies c, job_list jl
where u.id = 1;

select distinct(c.name), u.name from users u
inner join job_list jl on u.id = jl.user_id
inner join companies c on c.id = jl.company_id
where u.id = 2;

-- comapany name - количество позиций
-- aggregation functions
-- count, sum, avg, min, max
select count(u.name) from users u;
select count(jl.end_date) from job_list jl;

select c.name, count(jl.company_id) from users u
inner join job_list jl on u.id = jl.user_id
inner join companies c on c.id = jl.company_id
where u.id = 2
group by c.name
having count(jl.company_id) > 1
order by c.name
limit 10
offset 10;
