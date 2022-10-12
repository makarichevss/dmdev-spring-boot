create table if not exists flight (
    id serial primary key,
    company_name varchar(128) not null unique,
    destination varchar(256) not null
);

create table if not exists employee (
    id serial primary key,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    birth_day date,
    salary integer not null,
    company_id integer references company(id)
);