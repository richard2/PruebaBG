package com.richard.asencio.pruebabg.Utils

   const val MILLISECONDS_SPLASH = 1500L
   const val TAG_ERROR = "TAG_ERROR"
   const val IP_API_URL = "http://ip-api.com/"
   const val CODE_ECUADOR = "EC"
   const val CODE_PERU = "PE"
   const val APP = "4"
   const val ORIGEN = "2"
   const val BASE_URL_EC = "https://rolimapp.com:3000/"

   const val MAX_PHOTO = 10

   const val BASE_URL_EC_HMMOVIL= "http://app.huntermonitoreo.com:8080/"
   const val BASE_URL_PE_HMMOVIL = "http://app.huntermonitoreoperu.com:8080/"

   const val FILTER_VALUES_ALL = "filter_values_all"
   const val FILTER_VALUES_PENDIENTE = "filter_values_pendiente"
   const val FILTER_VALUES_TERMINADO = "filter_values_terminado"

   const val LOCATION_PERMISSION_REQUEST_CODE = 5689
   const val LOCATION_PERMISSION_REQUEST_CODE_CAMERA = 4568
   const val PREFERENCE_NAME = "PREFERENCE_CHECKPOINT"
   const val PREFERENCE_LOCATION = "PREFERENCE_LOCATION"
   const val PREFERENCE_CAMERA = "PREFERENCE_CAMERA"
   const val EXTRA_DETALLE_DESPACHO = "detalle_despacho"
   const val EXTRA_PATH_PICTURE = "path_picture"
   const val EXTRA_ID_DESPACHO = "id_despacho"
   const val EXTRA_STATUS = "extra_status"
   const val EXTRA_COMENTARIO = "extra_comentario"
   const val EXTRA_ID_PICTURE = "extra_id_picture"
   const val CATEGORIA_VISITAS = 5
   const val CATEGORIA_SUPERVISOR = 3
   const val EXTRA_TIPO = "tipo"
   const val EXTRA_CLIENTE = "cliente"
   const val EXTRA_MOTIVOS = "motivos"
   const val PREFS_FILENAME = "com.hunter.www.hmlogistic.prefs"
   const val EXTRA_MIPUNTO = "punto"
   const val prefDatosLoign = "datosLogin"
   const val EXTRA_SESION = "sesion"
   const val EXTRA_PAIS = "pais"
   const val PREFERENCE_SUPERVISOR = "PREFERENCE_SUPERVISOR"

   //MARK: DATABASE
   const val DATA_BASE_NAME = "DB_CHECK_POINT.db"

   //MARK: TABLE/FIELD USUARIO
   const val TABLE_USER = "USUARIO"
   const val TABLE_PRODUCTOS = "PRODUCTOS"
   const val FIELD_USER = "USER"
   const val FIELD_CLAVE = "CLAVE"
   const val FIELD_ID_DEVICE = "ID_DEVICE"
   const val FIELD_IMEI = "IMEI"
   const val FIELD_PUSH_ID = "PUSH_ID"
   const val FIELD_ID_USUARIO = "IdUsuario"
   const val FIELD_ID_SUBUSUARIO = "IdSubUsuario"
   const val FIELD_PAIS = "pais"
   const val FIELD_UID = "Uid"
   const val FIELD_TIPO_USUARIO = "TipoUsuario"
   const val FIELD_CATEGORIA_USUARIO = "CategoriaUsuario"
   const val FIELD_DESCRIPCION = "Descripcion"

   //MARK: TABLE/FIELD COUNTRY
   const val TABLE_COUNTRY = "COUNTRY"
   const val TABLE_DETALLE_PLANIFICADOS = "DETALLE_PLANIFICADOS"
   const val TABLE_DETALLE_NO_PLANIFICADOS = "DETALLE_NO_PLANIFICADOS"
   const val TABLE_DETALLE_REAL = "DETALLE_REAL"
   const val FIELD_CITY = "city"
   const val FIELD_COUNTRY = "country"
   const val FIELD_COUNTRY_CODE = "countryCode"
   const val FIELD_ISP = "isp"
   const val FIELD_LAT = "lat"
   const val FIELD_LON = "lon"
   const val FIELD_ORG = "org"
   const val FIELD_QUERY = "query"
   const val FIELD_REGION = "region"
   const val FIELD_REGION_NAME = "regionName"
   const val FIELD_STATUS = "status"
   const val FIELD_TIME_ZONE = "timezone"
   const val FIELD_ZIP = "zip"

   //MARK: TABLE/FIELD MOTIVO
   const val TABLE_MOTIVO = "MOTIVO"
   const val FIELD_MOTIVO = "Motivo"
   const val FIELD_ID = "Id"


   //MARK: TABLE/FIELD MOTIVO
   const val TABLE_SUBMOTIVO = "SUBMOTIVO"
   const val FIELD_SUB_MOTIVO = "SubMotivo"
   const val FIELD_ID_SUBMOTIVO = "IdSubMotivo"
    const val FIELD_ID_MOTIVO_MERGE = "IdMotivo"


   //MARK: TABLE/FIELD PICTURE
   const val TABLE_PICTURE = "PICTURE"
   const val FIELD_BASE_64 = "base64"
   const val FIELD_ID_DESPACHO_PICTURE = "idDespacho"
   const val FIELD_DESCRIPTION = "DESCRIPCION"

   //MARK: TABLE/FIELD DESPACHOS
   const val TABLE_DESPACHOS = "DESPACHOS"
   const val FIELD_ID_DESPACHO = "idDespacho"
   const val FIELD_SECUENCIAL_DESPACHO = "secuencialDespacho"
   const val FIELD_SECUENCIAL = "Secuencial"
   const val FIELD_ESTADO = "Estado"
   const val FIELD_ID_MOTIVO = "Id_motivo_selecciondo"
   const val FIELD_ID_SUBMOTIVOSS = "id_submotivo"
   const val FIELD_FIRMA = "firma"
   const val FIELD_FECHA_REALIZADO = "Fecha_despacho_realizado"
   const val FIELD_LUGAR_REALIZADO = "Lugar_despacho_realizado"
   const val FIELD_HORA_LLEGADA_REALIZADO = "Hora_llegada_despacho_realizado"
   const val FIELD_HORA_REAL_REALIZADO = "Hora_Real_despacho_realizado"
   const val FIELD_FECHA_LLEGADA = "FechaLlegada"
   const val FIELD_NOMBRE_CLIENTE_REALIZADO = "Nombre_cliente_despacho_realizado"
   const val FIELD_HORA_LLEGADA = "HoraLlegada"
   const val FIELD_DIRECCION = "Direccion"
   const val FIELD_LATITUD = "Latitud"
   const val FIELD_LONGITUD = "Longitud"
   const val FIELD_NOMBRE_CLIENTE = "NombreCliente"
   const val FIELD_DURACION = "Duracion"
   const val FIELD_FECHA_EMISION_DOCUMENTO = "FechaEmisionDocumento"
   const val FIELD_NRO_DOCUMENTO = "NroDocumento"
   const val FIELD_MOTIVO_DESPACHO = "Motivo"
   const val FIELD_OBSERVACION = "Observacion"
   const val FIELD_COMENTARIO_FOTO_1 = "ComentarioFoto1"
   const val FIELD_FOTO_1 = "Foto1"
   const val FIELD_COMENTARIO_FOTO_2 = "ComentarioFoto2"
   const val FIELD_FOTO_2 = "Foto2"
   const val FIELD_COMENTARIO_FOTO_3 = "ComentarioFoto3"
   const val FIELD_COMENTARIO_FOTO_4 = "ComentarioFoto4"
   const val FIELD_COMENTARIO_FOTO_5 = "ComentarioFoto5"
   const val FIELD_COMENTARIO_FOTO_6 = "ComentarioFoto6"
   const val FIELD_COMENTARIO_FOTO_7 = "ComentarioFoto7"
   const val FIELD_COMENTARIO_FOTO_8 = "ComentarioFoto8"
   const val FIELD_COMENTARIO_FOTO_9 = "ComentarioFoto9"
   const val FIELD_COMENTARIO_FOTO_10 = "ComentarioFoto10"
   const val FIELD_FOTO_3 = "Foto3"
   const val FIELD_FOTO_4 = "Foto4"
   const val FIELD_FOTO_5 = "Foto5"
   const val FIELD_FOTO_6 = "Foto6"
   const val FIELD_FOTO_7 = "Foto7"
   const val FIELD_FOTO_8 = "Foto8"
   const val FIELD_FOTO_9 = "Foto9"
   const val FIELD_FOTO_10 = "Foto10"
   const val FIELD_FIRMAR_CONFORMIDAD = "FirmaConformidad"
   const val FIELD_ESTADO_DESPACHO = "Estado_envio"
   const val FIELD_FECHA_MODIFICACION = "FechaModificacion"

   //MARK: TABLE/FIELD DETALLE PEDIDO
   const val TABLE_DETALLE_PEDIDO = "DETALLE_PEDIDO"
   const val FIELD_ITEM_DESC = "itemDesc"
   const val FIELD_SECUENCIA = "secuencia"
   const val FIELD_CANTIDAD = "cantidad"
   const val FIELD_UND = "und"

   //MARK: TABLE/FIELD CLIENTES
   const val TABLE_CLIENTES = "CLIENTES"
   const val FIELD_ID_USUARIO_CLIENTE = "IdUsuario"
   const val FIELD_ID_ENTIDAD_CLIENTE = "IdEntidad"
   const val FIELD_NOMBRE = "Nombre"
   const val FIELD_CATEGORIA_ENTIDAD = "CategoriaEntidad"
   const val FIELD_SUB_CATEGORIA_ENTIDAD = "SubCategoriaEntidad"
   const val FIELD_IDENTIFICACION = "Identificacion"
   const val FIELD_IDENTIFICACION_FISCAL = "IdentificacionFiscal"
   const val FIELD_CODIGO_INTERNO = "CodigoInterno"

   //MARK: TABLE/FIELD UBICACIONES
   const val TABLE_UBICACIONES = "UBICACIONES"
   const val FIELD_TIPO_UBICACION= "TipoUbicacion"
   const val FIELD_ID_CLIENTE_UBICACION= "IdCLiente"
   const val FIELD_ID_UBICACION= "IdUbicacion"
   const val FIELD_NOMBRE_UBICACION = "Nombre"
   const val FIELD_STATIC_URL = "StaticUrl"
   const val FIELD_DIRECCION_UBICACION= "Direccion"
   const val FIELD_ID_PUNTO_REFERENCIA= "IdPuntoReferencia"
   const val FIELD_LATITUD_UBICACION= "Latitud"
   const val FIELD_LONGITUD_UBICACION= "Longitud"


   //MARK: TABLE/FIELD DESPACHOS
   const val TABLE_DESPACHOS_REALIZADOS = "DESPACHOS_REALIZADOS"


   //MARK: TABLE/FIELD REGISTRO DE CUMPLIMIENTO
   const val TABLE_REGISTRO_CUMPLIMIENTO = "REGISTRO_CUMPLIMIENTO"
   const val FIELD_FECHA_CUMPLIMIENTO= "Fecha"
   const val FIELD_ALIAS_CUMPLIMIENTO= "Alias"
   const val FIELD_VIAJE_CUMPLIMIENTO= "Viaje"
   const val FIELD_PLANIFICADOS_CUMPLIMIENTO= "Planificados"
   const val FIELD_REAL_CUMPLIMIENTO= "Real"
   const val FIELD_GRAFICAR_CUMPLIMIENTO= "Graficar"
   const val FIELD_ID_ACTIVO_CUMPLIMIENTO= "IdActivo"
   const val FIELD_NO_VISITADOS_CUMPLIMIENTO= "NoVisitados"
   const val FIELD_CUMPLIMIENTO_CUMPLIMIENTO= "Cumplimiento"

   //MARK: TABLE/FIELD TOTALES
   const val TABLE_TOTALES = "TOTALES"
   const val FIELD_VIAJES_TOTALES = "Viajes"
   const val FIELD_VEHICULOS_ASIGNADOS_TOTALES = "VehiculosAsignados"
   const val FIELD_PUNTO_REAL_TOTALES = "PuntoReal"
   const val FIELD_PUNTOS_NO_VISITADOS_TOTALES = "PuntosNoVisitados"
   const val FIELD_PUNTOS_PLANIFICADOS_TOTALES = "PuntosPlanificados"
   const val FIELD_CUMPLIMIENTO_FLOTA_TOTALES = "CumplimientoFlota"
   const val FIELD_FECHA_TOTALES = "Fecha_totales"

