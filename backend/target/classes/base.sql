drop database if exists portfolio;

create database portfolio;

use portfolio;

/***********************************************************************/

create table persona
(
    id int not null auto_increment,
    usuario varchar(16) not null unique,
    clave varchar(255) not null,
    nombre varchar(32) not null,
    apellido varchar(32) not null,

    primary key (id)
);

insert into persona values(1, 'flopina', '1234', 'Flor', 'Gomez');

/***********************************************************************/

create table skill
(
    id int not null auto_increment,
	nombre varchar(32) not null unique,
	nivel int not null,

    primary key (id)
);

insert into skill values(1, 'Inglés', 80);
insert into skill values(2, 'Bootstrap', 60);
insert into skill values(3, 'Angular', 70);
insert into skill values(4, 'MySQL', 60);
insert into skill values(5, 'Spring Boot', 60);
