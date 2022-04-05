/*
- Codigo completo para crear las vistas del sistema
- Ejecutar primero el codigo para crear tablas
- Nomesclatura rev_v_[nombre de vista]
*/



-- VISTA UBICACIONES LIBRES
-- Devuelve lo que hay en ubicaciones totales restando lo que hay en ubicaciones (ubicaciones Ocupadas)

DROP VIEW rev_v_ubicaciones_libres;

CREATE VIEW rev_v_ubicaciones_libres AS
	SELECT 
		UT.ID,
		UT.subsector,
		UT.pozo,
		UT.seccion,
		UT.fila,
		UT.nicho,
		UT.sepultura,
		UT.boveda
	FROM rev_ubicaciones_totales UT
		LEFT JOIN rev_ubicaciones U ON IFNULL(UT.fila, -1) = IFNULL(U.fila, -1)
			AND IFNULL(UT.subsector, -1) = IFNULL(U.subsector, -1)
			AND IFNULL(UT.pozo, -1) = IFNULL(U.pozo, -1)
			AND IFNULL(UT.seccion, -1) = IFNULL(U.seccion, -1)
			AND IFNULL(UT.nicho, -1) = IFNULL(U.nicho, -1)
			AND IFNULL(UT.sepultura, -1) = IFNULL(U.sepultura, -1)
			AND IFNULL(UT.boveda, -1) = IFNULL(U.boveda, -1)
	WHERE U.ID IS NULL
;



-- VISTA FALLECIDOS
-- Devuelve a los fallecidos con los datos de su ubicacion

DROP VIEW rev_v_fallecidos; 

CREATE VIEW rev_v_fallecidos AS 
	SELECT
		F.id,
		F.tipo_fallecimiento,
		F.cod_fallecido,
		F.ubicacion,
		F.nombre,
		F.apellido,
		F.DNI,
		F.cocheria,
		F.fecha_fallecimiento,
		F.fecha_ingreso,
		U.vencimiento,
		U.subsector,
		U.pozo,
		U.seccion,
		U.fila,
		U.nicho,
		U.sepultura,
		U.boveda,
		U.cementerio
	FROM rev_fallecidos F
		LEFT JOIN rev_ubicaciones U ON F.ubicacion = U.id
;



-- VISTA CLIENTE NOTIFICACIONES
-- Devuelve a los clientes con los datos de los fallecidos a su cargo
-- Si un cliente tiene mas de un fallecido a su cargo devolvera a ese cliente una vez por cada uno
-- Se anexan tambien los datos de la ubicacion del fallecido

DROP VIEW rev_v_cliente_notificaciones; 

CREATE VIEW rev_v_cliente_notificaciones AS 
	SELECT
		C.ID AS cli_ID,
		C.nombre AS cli_nombre,
		C.apellido AS cli_apellido,
		C.DNI AS cli_dni,
		C.telefono AS cli_telefono,
		C.email,
		C.domicilio,
		F.id,
		F.tipo_fallecimiento,
		F.cod_fallecido,
		F.ubicacion,
		F.nombre,
		F.apellido,
		F.DNI,
		F.cocheria,
		F.fecha_fallecimiento,
		F.fecha_ingreso,
		U.vencimiento,
		U.subsector,
		U.pozo,
		U.seccion,
		U.fila,
		U.nicho,
		U.sepultura,
		U.boveda,
		U.cementerio
	FROM rev_fallecidos F
		LEFT JOIN rev_responsables R ON F.ID = R.fallecido 
		LEFT JOIN rev_clientes C ON C.ID = R.cliente
		LEFT JOIN rev_ubicaciones U ON F.ubicacion = U.id
;



-- VISTA REPORTE PAGOS 
-- Devuelve los pagos junto con los datos vinculados adecuados para mostrar en el reporte

DROP VIEW rev_v_reporte_pagos;

CREATE VIEW rev_v_reporte_pagos AS 
	SELECT
		PA.id AS pago_id,
	    PA.importe AS pago_importe,
	    PA.observaciones AS pago_observaciones,
	    PA.fecha AS pago_fecha,
	    CA.id AS cargo_id,
	    CA.observaciones AS cargo_observaciones,
	    CA.pagado AS cargo_pagado,
	    FA.id AS fallecido_id,
	    FA.cod_fallecido AS fallecido_codigo,
	    FA.nombre AS fallecido_nombre,
	    FA.apellido AS fallecido_apellido,
	    FA.dni AS fallecido_dni,
	    SE.id AS servicio_id,
	    SE.nombre AS servicio_nombre,
	    SE.historico AS servicio_historico,
		UB.ID AS ubicacion_ID,
		UB.subsector AS ubicacion_subsector,
		UB.pozo AS ubicacion_pozo,
		UB.seccion AS ubicacion_seccion,
		UB.fila AS ubicacion_fila,
		UB.nicho AS ubicacion_nicho,
		UB.sepultura AS ubicacion_sepultura,
		UB.boveda AS ubicacion_boveda,
		UB.cementerio AS ubicacion_cementerio,
		UB.vencimiento AS ubicacion_vencimiento
	FROM rev_pagos PA
		LEFT JOIN rev_cargos CA ON PA.cargo = CA.id
		LEFT JOIN rev_fallecidos FA ON CA.fallecido = FA.id
		LEFT JOIN rev_servicios SE ON SE.id = CA.servicio
		LEFT JOIN rev_ubicaciones UB ON UB.id = FA.ubicacion
;

