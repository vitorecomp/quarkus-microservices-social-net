create sequence hibernate_sequence start 1 increment 1;

    create table Follow (
       id int8 not null,
        createdAt timestamp not null,
        me int8,
        updatedAt timestamp,
        who int8,
        primary key (id)
    );

    create table users (
       id int8 not null,
        createdAt timestamp not null,
        name varchar(255),
        updatedAt timestamp,
        primary key (id)
    );
