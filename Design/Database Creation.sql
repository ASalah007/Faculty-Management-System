drop database if exists fms;
create database fms;
use fms;

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    name VARCHAR(256),
    email VARCHAR(512) unique,
    password char(32),
    address VARCHAR(512),
    birthdate DATE,
    PRIMARY KEY (id)
);

CREATE TABLE students (
    id INT,
    gpa Decimal(3,2),
    facultyId VARCHAR(16) unique,
    PRIMARY KEY (id),
    FOREIGN KEY (id)
        REFERENCES users (id)
);

CREATE TABLE instructors (
    id INT,
    title VARCHAR(32),
    PRIMARY KEY (id),
    FOREIGN KEY (id)
        REFERENCES users (id)
);

CREATE TABLE advises (
    student_id INT,
    instructor_id INT,
    PRIMARY KEY (student_id , instructor_id),
    FOREIGN KEY (student_id)
        REFERENCES students (id),
    FOREIGN KEY (instructor_id)
        REFERENCES instructors (id)
);

create table courses(
course_id int auto_increment,
course_code varchar(8),
name varchar(512),
credit_hours tinyint,
course_description varchar (1024),

primary key(course_id)
);

CREATE TABLE prerequisites (
    course_id INT,
    prerequisite_id INT,
    PRIMARY KEY (course_id , prerequisite_id),
    FOREIGN KEY (course_id)
        REFERENCES courses (course_id),
    FOREIGN KEY (prerequisite_id)
        REFERENCES courses (course_id)
);

CREATE TABLE course_offerings (
    course_offering_id INT,
    year INT,
    semester ENUM('Spring', 'Fall', 'Summer', 'Winter'),
    course_id INT,
    PRIMARY KEY (course_offering_id),
    FOREIGN KEY (course_id)
        REFERENCES courses (course_id)
);

create table teaches(
id int,
course_offering_id int,
primary key(course_offering_id, id),
foreign key(id) references instructors(id),
foreign key(course_offering_id) references course_offerings(course_offering_id)
);

CREATE TABLE takes (
    id INT,
    course_offering_id INT,
    grade VARCHAR(2),
    approved BOOLEAN,
    PRIMARY KEY (id , course_offering_id),
    FOREIGN KEY (id)
        REFERENCES students (id),
    FOREIGN KEY (course_offering_id)
        REFERENCES course_offerings (course_offering_id)
);

CREATE TABLE grades_credits (
    grade VARCHAR(2),
    credits NUMERIC(2 , 1 )
);

insert into grades_credits values ('A+', 4);
insert into grades_credits values ('A', 4);
insert into grades_credits values ('A-', 3.7);
insert into grades_credits values ('B+', 3.3);
insert into grades_credits values ('B', 3);
insert into grades_credits values ('B-', 2.7);
insert into grades_credits values ('C+', 2.3);
insert into grades_credits values ('C', 2);
insert into grades_credits values ('C-', 1.7);
insert into grades_credits values ('D+', 1.3);
insert into grades_credits values ('D', 1);
insert into grades_credits values ('D-', 0.7);
insert into grades_credits values ('F', 0);