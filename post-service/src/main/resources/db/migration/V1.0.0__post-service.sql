    create table Comment (
        id bigint not null,
        content varchar(255),
        userId bigint,
        post_id bigint,
        primary key (id)
    );

    create table Post (
        id bigint not null,
        content varchar(255),
        createdAt timestamp(6) not null,
        likes bigint,
        updatedAt timestamp(6),
        userId bigint,
        primary key (id)
    );

    create table post_likes (
        id bigint not null,
        postId bigint,
        userId bigint,
        primary key (id)
    );

    create sequence Comment_SEQ start with 1 increment by 50;

    create sequence post_likes_SEQ start with 1 increment by 50;

    create sequence Post_SEQ start with 1 increment by 50;

    alter table if exists Comment 
       add constraint FKqb0rnht649ifuh6gev5lwvx8x 
       foreign key (post_id) 
       references Post;

    alter table if exists Post 
       add column title varchar(255);
