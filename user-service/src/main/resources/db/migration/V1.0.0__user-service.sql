create sequence hibernate_sequence start 1 increment 1;

    create table Follow (
       id int8 not null,
        createdAt timestamp not null,
        updatedAt timestamp,
        follower_id int8,
        following_id int8,
        primary key (id)
    );

    create table users (
       id int8 not null,
        createdAt timestamp not null,
        name varchar(255),
        updatedAt timestamp,
        primary key (id)
    );

    alter table if exists Follow 
       add constraint FK3yn6y0n1i54of1kgo27opjk62 
       foreign key (follower_id) 
       references users;

    alter table if exists Follow 
       add constraint FKj727spsmu9s04cl6vfqg6r6a9 
       foreign key (following_id) 
       references users;


   alter table if exists Follow 
       alter column createdAt set data type timestamp(6);

   alter table if exists Follow 
       alter column updatedAt set data type timestamp(6);

   alter table if exists users 
       alter column createdAt set data type timestamp(6);

   alter table if exists users 
       alter column updatedAt set data type timestamp(6);

   create sequence Follow_SEQ start with 1 increment by 50;

   create sequence users_SEQ start with 1 increment by 50;
