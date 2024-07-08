
--drop database if exists spring;
--create database spring;

drop table if exists priority cascade ;
drop table if exists task cascade ;
drop table if exists users cascade ;


create table priority
(
    id      serial,
    name    character varying(100),
    created timestamp with time zone,
    constraint id_priority_pkey primary key (id)
);


create table users
(
    id         serial,
    username   character varying(50),
    first_name character varying(100),
    last_name  character varying(100),
    password   character varying(255),
    created    timestamp with time zone,
    constraint id_user_pkey primary key (id)
);


create table task
(
    id          serial,
    title       character varying(100),
    description character varying(300),
    priority    integer,
    user_id     integer,
    status      boolean default true,
    created     timestamp with time zone,
    constraint id_task_pkey primary key (id),
    constraint priority_id foreign key (priority) references priority (id),
    constraint user_id foreign key (user_id) references users (id)
);

insert into users(username, first_name, last_name, password, created)
values ('alambrito', 'alam', 'brito', '123', now());

insert into priority(name, created)
values ('high', now());

insert into task(title, description, priority, user_id, created)
values ('Task 1', 'Taks Default', 1, 1, now());

insert into task(title, description, priority, user_id, created)
values ('Task 2', 'Taks Default 2', 1, 1, now())