create table company_legal_details (
	company_id integer primary key,
	bank_name varchar not null,
	bic varchar not null,
	constraint cimpany_id_fkey foreign key (company_id) references companies(id)
);

create table user_addresses (
id serial primary key,
	user_id integer not null,
	address varchar not null,
	constraint user_addreess_user_id_fkey foreign key (user_id) references users(id)
);