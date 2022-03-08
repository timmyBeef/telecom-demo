insert into customer (id, name, email, created_at)
values (1, 'Tim', 'tim@gmail.com', current_timestamp);
insert into customer (id, name, email, created_at)
values (2, 'Evans', 'evans@gmail.com', current_timestamp);
insert into customer (id, name, email, created_at)
values (3, 'Darden', 'darden@gmail.com', current_timestamp);

insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (1, 1, '0415999888', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (2, 1, '0491570156', '', false, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (3, 2, '0491570157', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (4, 2, '0491510156', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (5, 3, '0491570152', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (6, 3, '0491870156', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (7, 3, '0491970156', '', false, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (8, 3, '0491570150', '', true, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (9, 3, '0411570156', '', false, current_timestamp);
insert into phone (id, customer_id, phone_number, phone_ext, activated, created_at)
values (10, 3, '0415570156', '', false, current_timestamp);

