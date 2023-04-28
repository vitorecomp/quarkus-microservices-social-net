create table post_service.Comment (
    id bigint not null,
    content varchar(255),
    userId bigint,
    post_id bigint,
    primary key (id)
);

create table post_service.Post (
    id bigint not null,
    content varchar(255),
    createdAt timestamp(6) not null,
    likes bigint,
    title varchar(255),
    updatedAt timestamp(6),
    userId bigint,
    primary key (id)
);

create table post_service.post_likes (
    id bigint not null,
    postId bigint,
    userId bigint,
    primary key (id)
);

create sequence post_service.Comment_SEQ start with 1 increment by 50;

create sequence post_service.post_likes_SEQ start with 1 increment by 50;

create sequence post_service.Post_SEQ start with 1 increment by 50;

alter table if exists post_service.Comment 
    add constraint FKqb0rnht649ifuh6gev5lwvx8x 
    foreign key (post_id) 
    references post_service.Post;
