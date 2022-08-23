drop database if exists argprograma;

create database argprograma;

use argprograma;

/***********************************************************************/

create table persona
(
    id int not null auto_increment,
    usuario varchar(16) not null unique,
    clave varchar(255) not null,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    titulo varchar (40) not null,
    descripcion text not null,
    correo varchar (50) not null,
    direccion varchar (100) not null,
    nacimiento date not null, 
    fotoPerfil varchar(100) not null,

    primary key (id)
);

insert into persona values(1, 'flopina', '1234', 'Flor', 'Arando','Full Stack Developer Jr', 'descripcion sobre mi con años y a q me dedico', 'arandoflorenciaromina@gmail.com', 'CABA, Bs.As, Argentina', '1997-08-07', 'www.google.com');

/***********************************************************************/

create table skill
(
    id int not null auto_increment,
	nombre varchar(32) not null unique,
	nivel int not null, /*agregar foto*/

    primary key (id)
);

insert into skill values(1, 'Inglés', 80);
insert into skill values(2, 'Bootstrap', 60);
insert into skill values(3, 'Angular', 70);
insert into skill values(4, 'MySQL', 60);
insert into skill values(5, 'Spring Boot', 60);
insert into skill values(6, 'Git', 70);
insert into skill values(7, 'Github', 70);
insert into skill values(8, 'Trazabilidad Informática', 60);
insert into skill values(9, 'Visual Estudio Code', 75);
insert into skill values(10, 'Apache NetBeans', 70);
insert into skill values(11, 'HTML', 70);
insert into skill values(12, 'CSS', 60);
insert into skill values(13, 'JAVA', 70);
/************************************************************************/

create table educacion
(
    id int not null auto_increment,
	titulo varchar(200) not null,
    descripcion text not null,
    desde varchar(20) not null, 
    hasta varchar(20) not null,

    primary key (id)
);

insert into educacion values(1,'Bachiller con orientación informática', 'Colegio Domingo Faustino Sarmiento N°2 D.E 1', '2010-01-03', '2015-12-15');
insert into educacion values(2,'Teacher Nivel Superior', 'Instituto Cambridge', '2009-01-03', '2016-12-15');
insert into educacion values(3,'Técnico Superior en ESterilización','Instituto Superior de Tecnicaturas para la Salud del GCBA', '2017-01-03', '2019-12-09');

/**************************************************************************/

create table proyecto
(
    id int not null auto_increment,
	titulo varchar(200) not null,
    descripcion varchar(300) not null,
    desde varchar(20) not null, 
    hasta varchar(20) not null,
    link varchar(300) not null, 
    foto varchar(300) not null, 

    primary key (id)
);

insert into proyecto values(1,'Portfolio Web', 'Desarrollo de una aplicación web full stack donde aprendi a Java, Springboot, MySQL, Angular, Git, Github y otras tecnologias que forman parte de la creación del proyecto a lo largo de su duración de 7 meses', '2022-02-01', '2022-08-31', 'www.google.com', 'https://acortar.link/6yWf7W');

/**************************************************************************/

create table experiencia
(
    id int not null auto_increment,
    empresa varchar(150) not null,
	puesto varchar(100) not null,
    desde varchar(20) not null, 
    hasta varchar(20) not null, 
    descripcion varchar(300) not null,

    primary key (id)
);

insert into experiencia values(1,'Hospital de Odontología Universitario - Facultad de Odontología UBA', 'Técnico Sup. en Esterilización', '2020-08-01', '2027-01-01', 'Recepcíon, decontaminación, acondicionamiento y esterilización de instrumental y productos médicos odontologicos. Uso de trazabilidad informática');
insert into experiencia values(2, 'Sanatorio Otamendi y Miroli', 'Técnico Sup. en Esterilización', '2020-12-01', '2022-03-15', 'seguir desccripcion esta y otras  experiencias más');