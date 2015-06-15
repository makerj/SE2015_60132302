CREATE USER 'sogongAdmin' IDENTIFIED BY 'mju12345';
GRANT ALL PRIVILEGES ON *.* TO 'sogongAdmin'@'%' WITH GRANT OPTION;

CREATE DATABASE sogongDB;
CREATE USER 'sogong2015' IDENTIFIED BY 'mju12345';
GRANT ALL PRIVILEGES ON sogongDB.* TO 'sogong2015'@'%';

CREATE TABLE sogongDB.USER (
	ID VARCHAR(12) PRIMARY KEY,
	PW VARCHAR(128),
	name VARCHAR(12),
	authority VARCHAR(12)
);

CREATE TABLE sogongDB.LECTURE (
	ID int PRIMARY KEY,
	name VARCHAR(12),
	year int,
	school_year int,
	max_student int,
	credit int,
	prof_id VARCHAR(12),
	FOREIGN KEY (prof_id) REFERENCES sogongDB.USER (ID)
);

CREATE TABLE sogongDB.LECTURE_GRADE (
	student_ID VARCHAR(12),
	lecture_ID int,
	grade VARCHAR(2),
	FOREIGN KEY (student_ID) REFERENCES sogongDB.USER (ID),
	FOREIGN KEY (lecture_ID) REFERENCES sogongDB.LECTURE (ID),
	PRIMARY KEY(student_ID, lecture_ID)
);

insert into sogongDB.USER values('admin', 'admin', '이DBA', 'admin');
insert into sogongDB.USER values('yunha', '486', '윤하', 'student');
insert into sogongDB.USER values('james', '007', '제임스본드', 'prof');
insert into sogongDB.USER values('newbal', '974', '뉴발란스', 'student');
insert into sogongDB.USER values('sparta', '300', '아르테미시아', 'prof');
insert into sogongDB.USER values('choi', 'ilovehimsomuch', '최성운짱짱', 'prof');

insert into sogongDB.LECTURE values(1, '소프트웨어공학', 2015, 3, 40, 3, 'choi');
insert into sogongDB.LECTURE values(2, '운영체제', 2015, 3, 40, 3, 'james');
insert into sogongDB.LECTURE values(3, '아키텍쳐', 2015, 3, 40, 3, 'sparta');

insert into sogongDB.LECTURE_GRADE values('yunha', 1, 'A0');
insert into sogongDB.LECTURE_GRADE values('newbal', 1, 'F');
