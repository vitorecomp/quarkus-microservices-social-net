create sequence hibernate_sequence start 1 increment 1;

    create table Post (
       id int8 not null,
        createdAt timestamp not null,
        text varchar(255),
        updatedAt timestamp,
        userId int8,
        primary key (id)
    );
