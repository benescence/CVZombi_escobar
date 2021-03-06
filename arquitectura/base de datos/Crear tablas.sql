-- NUEVA BASE DE DATOS.
DROP DATABASE IF EXISTS revivir_escobar;
CREATE DATABASE revivir_escobar;
USE revivir_escobar;

-- ESQUEMA ROLES
CREATE TABLE rev_roles (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMAS USUARIOS
CREATE TABLE rev_usuarios (
	ID INT(10) AUTO_INCREMENT,
	rol INT(1) NOT NULL,
	usuario VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (rol) REFERENCES rev_roles(ID)
);

-- ESQUEMAS SECTOR
CREATE TABLE rev_sector (
	ID INT(10) NOT NULL,
	descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID)
);

-- ESQUEMAS SUBSECTOR
CREATE TABLE rev_subsector (
	ID INT(10) NOT NULL,
	sector INT(10) NOT NULL,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID),
	FOREIGN KEY (sector) REFERENCES rev_sector(ID)
);

-- ESQUEMAS UBICACIONES
CREATE TABLE rev_ubicaciones (
	ID INT(10) AUTO_INCREMENT,
	subsector INT(10) NOT NULL,
	boveda INT(10) NULL,
	seccion VARCHAR(10) NULL,
	macizo INT(10) NULL,
	parcela INT(10) NULL,
	fila INT(10) NULL,
	unidad INT(10) NULL,
	nicho INT(10) NULL,
	mueble INT(10) NULL,
	sepultura INT(10) NULL,
	pozo INT(10) NULL,
	cementerio VARCHAR(11) NULL,
	vencimiento DATE NULL,
	bis_macizo BOOLEAN NULL,
	bis BOOLEAN NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (subsector) REFERENCES rev_subsector(ID)
);

-- ESQUEMA TIPO DE FALLECIMIENTO
CREATE TABLE rev_tipo_fallecimiento (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMAS FALLECIDOS
CREATE TABLE rev_fallecidos (
	ID INT(10) AUTO_INCREMENT,
	tipo_fallecimiento INT(1) NOT NULL,
	cod_fallecido INT(11) NOT NULL,
	ubicacion INT(10) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	DNI VARCHAR(11) NULL,
	cocheria VARCHAR(20) NULL,
	fecha_fallecimiento DATE NULL,
	fecha_ingreso DATE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (tipo_fallecimiento) REFERENCES rev_tipo_fallecimiento(ID),
	FOREIGN KEY (ubicacion) REFERENCES rev_ubicaciones(ID)
);

-- ESQUEMAS MOVIMIENTOS
CREATE TABLE rev_movimientos (
	ID INT(10) AUTO_INCREMENT,
	fallecido INT(10) NOT NULL,
	antigua_ubicacion VARCHAR(150) NOT NULL,
	causa_traslado VARCHAR(150) NOT NULL,
	observaciones VARCHAR(50) NULL,
	fecha_movimiento DATE NOT NULL,
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID),
	PRIMARY KEY (ID)
);

-- ESQUEMA CLIENTES
CREATE TABLE rev_clientes (
	ID INT(10) AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	DNI VARCHAR(10) NULL,
	telefono VARCHAR(20) NULL,
	email VARCHAR(50) NULL,
	domicilio VARCHAR(100) NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMAS RESPONSABLES
CREATE TABLE rev_responsables (
	ID INT(10) AUTO_INCREMENT,
	cliente INT(10) NOT NULL,
	fallecido INT(10) NOT NULL,
	observaciones VARCHAR(100) NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (cliente) REFERENCES rev_clientes(ID),
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID)
);

-- ESQUEMAS SERVICIOS
CREATE TABLE rev_servicios (
	ID INT(10) AUTO_INCREMENT,
	codigo VARCHAR(50) NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	descripcion VARCHAR(100) NULL,
	importe FLOAT(10) NOT NULL,
	historico BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMAS CARGOS
CREATE TABLE rev_cargos (
	ID INT(10) AUTO_INCREMENT,
	fallecido INT(10) NOT NULL,
	servicio INT(10) NOT NULL,
	observaciones VARCHAR(100) NULL,
	pagado BOOLEAN NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (servicio) REFERENCES rev_servicios(ID),
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID)
);

-- ESQUEMAS PAGOS
CREATE TABLE rev_pagos (
	ID INT(10) AUTO_INCREMENT,
	cargo INT(10) NOT NULL,
	importe DOUBLE(10, 2) NOT NULL,
	observaciones VARCHAR(100) NULL,
	fecha DATE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (cargo) REFERENCES rev_cargos(ID)
);

-- ESQUEMA PERIODO
CREATE TABLE rev_periodos (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);

-- ESQUEMAS EXPENSAS
CREATE TABLE rev_expensas (
	ID INT(10) AUTO_INCREMENT,
	responsable INT(10) NOT NULL,
	periodo INT(10) NOT NULL,
	ubicacion INT(10) NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	importe DOUBLE(10, 2) NOT NULL,
	observaciones VARCHAR(100) NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (responsable) REFERENCES rev_clientes(ID),
	FOREIGN KEY (periodo) REFERENCES rev_cargos(ID),
    FOREIGN KEY (ubicacion) REFERENCES rev_ubicaciones(ID)
);




-- INSERT TIPOS DE FALLECIMIENTO
INSERT INTO rev_tipo_fallecimiento (ID, descripcion) VALUES
(1, "TRAUMATICO"),
(2, "NO TRAUMATICO");

-- INSERT  ROLES
INSERT INTO rev_roles (ID, descripcion) VALUES
(1, "SUPERVISOR"),
(2, "ADMINISTRATIVO");

-- INSERT SECTORES
INSERT INTO rev_sector (ID, descripcion) VALUES
(1, 'SEPULTURAS'),
(2, 'PALMERAS'),
(3, 'NICHERA'),
(4, 'CENIZARIO'),
(5, 'BOVEDA'),
(6, 'DEPOSITO'),
(7, 'OTRO CMENTERIO');

-- INSERT PERIODOS
INSERT INTO rev_periodos(ID, descripcion) VALUES
(1, 'UNMES'),
(2,'TRESMESES'),
(3,'SEISMESES'),
(4,'DOCEMESES'),
(5,'VEINTICUATROMESES'),
(6,'TTREINTAYSEISMESES'),
(7,'CUARENTAYOCHOMESES');

-- INSERT SUB SECTORES
INSERT INTO rev_subsector (ID, sector, descripcion) VALUES
(1, 1, 'ADULTOS'),
(2, 1, 'ANGELITOS'),
(3, 1, 'COMPRADA'),
(4, 1, 'INDIGENTES'),
(5, 2, 'PALMERAS ATAUD'),
(6, 2, 'PALMERAS CENIZAS'),
(7, 2, 'PALMERAS RESTOS'),
(8, 2, 'PALMERAS SEPULTURA'),
(9, 3, 'NICHERA'),
(10, 4, 'CENIZARIO'),
(11, 5, 'BOVEDA'),
(12, 6, 'DEPOSITO 1'),
(13, 6, 'DEPOSITO 2'),
(14, 6, 'DEPOSITO 3'),
(15, 7, 'OTRO CMENTERIO'),
(16, 1, 'SECCION_A'),
(17, 1, 'SECCION_B'),
(18, 1, 'SECCION_C'),
(19, 3, 'SECCION_A'),
(20, 3, 'SECCION_B'),
(21, 3, 'SECCION_C'),
(22, 5, 'SECCION_A'),
(23, 5, 'SECCION_B'),
(24, 5, 'SECCION_C');

INSERT INTO rev_ubicaciones (subsector, boveda, seccion, macizo, parcela, fila, unidad, nicho, mueble, sepultura,pozo, cementerio, vencimiento, bis_macizo, bis) VALUES
(1, NULL, NULL, null, NULL, null, null, NULL, null, null, null, NULL, '1600-01-10', null, NULL);

INSERT INTO rev_fallecidos (tipo_fallecimiento,cod_fallecido, ubicacion, nombre, apellido, dni, cocheria, fecha_fallecimiento, fecha_ingreso) VALUES
(1, 20000 ,  1, "base_codigo", "base_codigo", "1", "base_codigo", '0001-01-1', '0001-01-1');

insert into rev_usuarios (rol, usuario, password) values 
(1, 'admin', 'admin');


CREATE VIEW rev_v_ubicaciones_libres AS
	SELECT 
		UT.ID,
		UT.subsector,
		UT.pozo,
		UT.seccion,
		UT.macizo,
		UT.parcela,
		UT.fila,
		UT.unidad,
		UT.nicho,
		UT.mueble,
		UT.sepultura,
		UT.boveda,
		UT.bis_macizo,
		UT.bis
	FROM rev_ubicaciones_totales UT
		LEFT JOIN rev_ubicaciones U ON IFNULL(UT.fila, -1) = IFNULL(U.fila, -1)
			AND IFNULL(UT.subsector, -1) = IFNULL(U.subsector, -1)
			AND IFNULL(UT.seccion, -1) = IFNULL(U.seccion, -1)
			AND IFNULL(UT.macizo, -1) = IFNULL(U.macizo, -1)
			AND IFNULL(UT.pozo, -1) = IFNULL(U.pozo, -1)
			AND IFNULL(UT.boveda, -1) = IFNULL(U.boveda, -1)
			AND IFNULL(UT.parcela, -1) = IFNULL(U.parcela, -1)
			AND IFNULL(UT.unidad, -1) = IFNULL(U.unidad, -1)
			AND IFNULL(UT.nicho, -1) = IFNULL(U.nicho, -1)
			AND IFNULL(UT.mueble, -1) = IFNULL(U.mueble, -1)
			AND IFNULL(UT.sepultura, -1) = IFNULL(U.sepultura, -1)
			AND IFNULL(UT.bis_macizo, -1) = IFNULL(U.bis_macizo, -1)
			AND IFNULL(UT.bis, -1) = IFNULL(U.bis, -1)
	WHERE U.ID IS null
;

CREATE TABLE rev_ubicaciones_totales (
	ID INT(10) AUTO_INCREMENT,
	subsector INT(10) NOT NULL,
	seccion VARCHAR(10) NULL,
	macizo INT(10) NULL,
	pozo INT(10) NULL,
	parcela INT(10) NULL,
	fila INT(10) NULL,
	unidad INT(10) NULL,
	nicho INT(10) NULL,
	mueble INT(10) NULL,
	sepultura INT(10) NULL,
	boveda INT(10) NULL,
	bis_macizo BOOLEAN NULL,
	bis BOOLEAN NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (subsector) REFERENCES rev_subsector(ID)
);


