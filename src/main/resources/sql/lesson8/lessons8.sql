create table company_positions (
	company_id integer not null,
	position_id integer not null,
	constraint company_positions_pkey primary key (company_id, position_id),
	constraint company_positions_company_id_fkey foreign key (company_id) references companies(id),
	constraint company_positions_position_id_fkey foreign key (position_id) references positions(id)
);