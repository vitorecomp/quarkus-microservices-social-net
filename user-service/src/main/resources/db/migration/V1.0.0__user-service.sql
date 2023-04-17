create sequence hibernate_sequence start with 1 increment by 1;

    create table Follow (
       id bigint not null,
        createdAt timestamp not null,
        me bigint,
        updatedAt timestamp,
        who bigint,
        primary key (id)
    );

    create table User (
       id bigint not null,
        createdAt timestamp not null,
        name varchar(255),
        updatedAt timestamp,
        primary key (id)
    );
