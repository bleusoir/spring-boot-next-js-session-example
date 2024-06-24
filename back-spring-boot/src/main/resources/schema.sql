create table PUBLIC.MEMBER
(
    ID       BIGINT auto_increment,
    NAME     CHARACTER VARYING(100) not null,
    PASSWORD CHARACTER VARYING(100) not null,
    constraint MEMBER_PK
        primary key (ID)
);