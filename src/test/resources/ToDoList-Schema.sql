drop table if exists to_do_list;
create table to_do_list 
	(id integer not null auto_increment,
	description varchar(255), due_date date, 
	name varchar(255), 
	priority integer not null, 
	time_estimate_minutes integer not null, 
	
	primary key (id))