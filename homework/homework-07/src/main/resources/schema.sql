create table author
(
    id long identity primary key,
    name varchar(150) not null
);

create table book
(
    id long identity primary key,
    name varchar(150) not null,
    author_id long not null,
    genre_id long not null
);

create table genre
(
    id long identity primary key,
    name varchar(30) not null
);
