create table avatars(
    id bigserial auto_increment,
    photo_url varchar(8000),
    primary key (id)
);

create table courses(
    id bigserial auto_increment,
    name varchar(255),
    primary key (id)
);

create table otus_students(
    id bigserial auto_increment,
    name varchar(255),
    avatar_id bigint references avatars (id),
    primary key (id)
);

create table emails(
    id bigserial auto_increment,
    student_id bigint references otus_students(id) on delete cascade,
    email varchar(255),
    primary key (id)
);

create table student_courses(
    student_id bigint references otus_students(id) on delete cascade,
    course_id bigint references courses(id),
    primary key (student_id, course_id)
);