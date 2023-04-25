create sequence hibernate_sequence start 1 increment 1;

    create table Post (
       id int8 not null,
        createdAt timestamp not null,
        likes int8,
        text varchar(255),
        updatedAt timestamp,
        userId int8,
        primary key (id)
    );

    create table post_likes (
       id int8 not null,
        postId int8,
        userId int8,
        primary key (id)
    );
