insert into company (id, company_name)
values (1, 'Apple'),
        (2, 'Google');

select setval('company_id_seq', (select max(id) from company));


