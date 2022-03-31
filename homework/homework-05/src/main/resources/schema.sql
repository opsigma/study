drop table  if exists author;
create table author
(
    id long identity primary key,
    name varchar(150) not null
);

drop table  if exists book;
create table book
(
    id long identity primary key,
    name varchar(150) not null,
    author_id long not null,
    genre_id long not null
);
drop table  if exists genre;
create table genre
(
    id long identity primary key,
    name varchar(30) not null
);
