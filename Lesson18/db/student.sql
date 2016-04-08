create database if not exists students;
use students;
create table if not exists Students
(
	Id int auto_increment primary key,
    Email nvarchar(255) not null,
    UserName varchar(255) not null,
    Password varchar(255) not null,
    FirstName nvarchar(255) not null,
    LastName nvarchar(255) not null,
    Gender set('Male', 'Female', 'Unknown') not null,
    DateOfBirth date,
    RegistationData timestamp,
    LastLoginData date not null
)DEFAULT CHARACTER SET = utf8;
alter table Students modify UserName varchar(255) not null unique;

insert into Students(UserName, Email, Password, FirstName, LastName, Gender, DateOfBirth, LastLoginData) values
(	
	'Katuha', 'Kathy@mail.com', '12345', 'Kathy' ,'Smith', 'Female', '2001/01/05', curdate()
);

insert into Students(UserName, Email, Password, FirstName, LastName, Gender, DateOfBirth, LastLoginData) values
(	
	'John', 'John@mail.com', 'qwerty', 'John' ,'Doe', 'Male', '2003/05/11', curdate()
);

insert into Students(UserName, Email, Password, FirstName, LastName, Gender, DateOfBirth, LastLoginData) values
(	
	'Sue', 'Sue@mail.com', '54321', 'Sue' ,'Black', 'Female', '1998/10/01', curdate()
);

insert into Students(UserName, Email, Password, FirstName, LastName, Gender, DateOfBirth, LastLoginData) values
(	
	'Jane', 'Jane@mail.com', 'purple', 'Jane' ,'White', 'Female', '2000/05/07', curdate()
);

insert into Students(UserName, Email, Password, FirstName, LastName, Gender, DateOfBirth, LastLoginData) values
(	
	'Joe', 'Joe@mail.com', 'tornado', 'Joe' ,'Brown', 'Unknown', '2001/04/06', curdate()
);
select * from students;
alter table Students
modify RegistationData timestamp;
describe Students;

create table if not exists Subjects
(
	Id int auto_increment primary key,
    Name nvarchar(255) not null
);

alter table Subjects modify Name nvarchar(255) not null unique;

create table if not exists Tutors
(
	Id int auto_increment primary key,
    Email nvarchar(255) not null,
    UserName varchar(255) not null,
    Password varchar(255) not null,
    FirstName nvarchar(255) not null,
    LastName nvarchar(255) not null,
    Gender set('Male', 'Female', 'Unknown') not null
);

create table if not exists Courses
(
	Id int auto_increment primary key,
    Name nvarchar(255) not null,
    Duration date not null,
    Description text,
    SubjectID int not null,
    TutorID int not null,
    foreign key (SubjectID) references Subjects(Id),
    foreign key (TutorID) references Tutors(Id)
);

create table if not exists Enrollments
(
	Id int auto_increment primary key,
    EnrollmentDate timestamp,
    CourseID int not null,
    StudentID int not null,
    foreign key (CourseID) references Courses(Id),
    foreign key (StudentID) references Students(Id)
);

select * from students;

alter table students
modify LastLoginData varchar(256);