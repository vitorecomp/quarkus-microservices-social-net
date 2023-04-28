    create table user_service.Follow (
        id bigint not null,
        createdAt timestamp(6) not null,
        updatedAt timestamp(6),
        follower_id bigint,
        following_id bigint,
        primary key (id)
    );

    create table user_service.users (
        id bigint not null,
        createdAt timestamp(6) not null,
        name varchar(255),
        updatedAt timestamp(6),
        primary key (id)
    );

    create sequence user_service.Follow_SEQ start with 1 increment by 50;

    create sequence user_service.users_SEQ start with 1 increment by 50;

    alter table if exists user_service.Follow 
       add constraint FK3yn6y0n1i54of1kgo27opjk62 
       foreign key (follower_id) 
       references user_service.users;

    alter table if exists user_service.Follow 
       add constraint FKj727spsmu9s04cl6vfqg6r6a9 
       foreign key (following_id) 
       references user_service.users;
