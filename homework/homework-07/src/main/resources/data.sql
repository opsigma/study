insert into author (name) values ('author1');
insert into author (name) values ('author2');
insert into author (name) values ('author3');

insert into book (name, author_id, genre_id) values ('book', 1, 1);
insert into book (name, author_id, genre_id) values ('book2', 2, 1);
insert into book (name, author_id, genre_id) values ('book2', 3, 2);

insert into genre (name) values ('genre1');
insert into genre (name) values ('genre2');

insert into comment (book_id, comment) values ( 1, 'comment1_1' );
insert into comment (book_id, comment) values ( 2, 'comment2_1' );
insert into comment (book_id, comment) values ( 2, 'comment2_2' );
insert into comment (book_id, comment) values ( 3, 'comment3_1' );
insert into comment (book_id, comment) values ( 3, 'comment3_3' );
insert into comment (book_id, comment) values ( 3, 'comment3_3' );
