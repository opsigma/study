insert into author (name) values ('author1');
insert into author (name) values ('author2');


insert into book (name, author_id, genre_id) values ('book1', 1, 1);
insert into book (name, author_id, genre_id) values ('book2', 2, 1);

insert into genre (name) values ('genre1');
insert into genre (name) values ('genre2');

insert into comment (book_id, comment) values ( 1, 'comment1_1' );
insert into comment (book_id, comment) values ( 1, 'comment1_2' );
insert into comment (book_id, comment) values ( 1, 'comment1_3' );
insert into comment (book_id, comment) values ( 2, 'comment2_1' );