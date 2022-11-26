DROP TABLE  accion_constancia;
DROP TABLE  accion_justificacion;
DROP TABLE  accion_reclamo;
DROP TABLE  analista;
DROP TABLE  convocatoria_asistencia;
DROP TABLE  estudiante;
DROP TABLE  evento;
DROP TABLE  justificacion;
DROP TABLE  tutor;
DROP TABLE  reclamo;
DROP TABLE  usuario;
DROP TABLE  itr;
DROP TABLE  constancia;

CREATE SEQUENCE itr_seq;
CREATE TABLE itr
(
    id           int          NOT NULL,
    departamento varchar2(45) NOT NULL,
    nombre       varchar2(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id               int          NOT NULL,
    documento        int          NOT NULL,
    usuario          varchar2(45) NOT NULL,
    contrasenia      varchar2(45) NOT NULL,
    apellidos        varchar2(45) NOT NULL,
    nombres          varchar2(45) NOT NULL,
    fecha_nacimiento TIMESTAMP    NOT NULL,
    departamento     varchar2(45) DEFAULT NULL,
    genero           varchar(1)   DEFAULT NULL,
    localidad        varchar2(45) DEFAULT NULL,
    mail             varchar2(45) NOT NULL,
    telefono         varchar2(45) NOT NULL,
    id_itr           int          NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT documento_UNIQUE UNIQUE (documento),
    CONSTRAINT usuario_UNIQUE UNIQUE (usuario),
    CONSTRAINT fk_usuario_id_itr FOREIGN KEY (id_itr) REFERENCES itr
);

CREATE SEQUENCE estudiante_seq;
CREATE TABLE estudiante
(
    id         int       NOT NULL,
    id_usuario int       NOT NULL,
    generacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_estudiante_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

CREATE SEQUENCE tutor_seq;
CREATE TABLE tutor
(
    id         int NOT NULL,
    id_usuario int DEFAULT NULL,
    tipo       int DEFAULT NULL,
    area       int DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_tutor_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

CREATE SEQUENCE analista_seq;
CREATE TABLE analista
(
    id         int NOT NULL,
    id_usuario int DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_analista_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

CREATE SEQUENCE evento_seq;
CREATE TABLE evento
(
    id     int       NOT NULL,
    inicio timestamp NOT NULL,
    fin    timestamp DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE evento_analista_seq;
CREATE TABLE evento_analista
(
    analista_id int DEFAULT NULL,
    evento_id   int DEFAULT NULL,
    PRIMARY KEY (analista_id, evento_id),
    CONSTRAINT fk_evento_analista_analista FOREIGN KEY (analista_id) REFERENCES analista (id),
    CONSTRAINT fk_evento_analista_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE evento_tutor_seq;
CREATE TABLE evento_tutor
(
    tutor_id  int DEFAULT NULL,
    evento_id int DEFAULT NULL,
    PRIMARY KEY (tutor_id, evento_id),
    CONSTRAINT fk_evento_tutor_tutor FOREIGN KEY (tutor_id) REFERENCES tutor (id),
    CONSTRAINT fk_evento_tutor_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE evento_estudiante_seq;
CREATE TABLE evento_estudiante
(
    calificacion  int       NOT NULL,
    asistencia    number(1) NOT NULL,
    estudiante_id int DEFAULT NULL,
    evento_id     int DEFAULT NULL,
    PRIMARY KEY (estudiante_id, evento_id),
    CONSTRAINT fk_evento_estudiante_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante (id),
    CONSTRAINT fk_evento_estudiante_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE justificacion_seq;
CREATE TABLE justificacion
(
    id            int          NOT NULL,
    detalle       varchar2(45) NOT NULL,
    fecha         timestamp    NOT NULL,
    estudiante_id int DEFAULT NULL,
    evento_id     int DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_justificacion_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante (id),
    CONSTRAINT fk_justificacion_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE constancia_seq;
CREATE TABLE constancia
(
    id            int          NOT NULL,
    detalle       varchar2(45) NOT NULL,
    fecha         timestamp    NOT NULL,
    evento_id     int DEFAULT NULL,
    estudiante_id int DEFAULT NULL,
    estado        varchar2(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_constancia_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante (id),
    CONSTRAINT fk_constancia_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE reclamo_seq;
CREATE TABLE reclamo
(
    id            int          NOT NULL,
    detalle       varchar2(45) NOT NULL,
    fecha         timestamp    NOT NULL,
    estudiante_id int DEFAULT NULL,
    evento_id     int DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_reclamo_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiante (id),
    CONSTRAINT fk_reclamo_evento FOREIGN KEY (evento_id) REFERENCES evento (id)
);

CREATE SEQUENCE accion_justificacion_seq;
CREATE TABLE accion_justificacion
(
    -- cambio a pk compuesta
    detalle          varchar2(45) NOT NULL,
    fecha            timestamp    NOT NULL,
    justificacion_id int DEFAULT NULL,
    analista_id      int DEFAULT NULL,
    PRIMARY KEY (justificacion_id, analista_id),
    CONSTRAINT fk_accion_justificacion_analista FOREIGN KEY (analista_id) REFERENCES analista (id),
    CONSTRAINT fk_accion_justificacion_justificacion FOREIGN KEY (justificacion_id) REFERENCES justificacion (id)
);

CREATE SEQUENCE accion_constancia_seq;
CREATE TABLE accion_constancia
(
    -- cambio a pk compuesta
    detalle       varchar2(45) NOT NULL,
    fecha         timestamp    NOT NULL,
    constancia_id int DEFAULT NULL,
    analista_id   int DEFAULT NULL,
    PRIMARY KEY (constancia_id, analista_id),
    CONSTRAINT fk_accion_constancia_analista FOREIGN KEY (analista_id) REFERENCES analista (id),
    CONSTRAINT fk_accion_constancia_constancia FOREIGN KEY (constancia_id) REFERENCES constancia (id)
);

CREATE SEQUENCE accion_reclamo_seq;
CREATE TABLE accion_reclamo
(
    -- cambio a pk compuesta
    detalle     varchar2(45) NOT NULL,
    fecha       timestamp    NOT NULL,
    reclamo_id  int DEFAULT NULL,
    analista_id int DEFAULT NULL,
    PRIMARY KEY (reclamo_id, analista_id),
    CONSTRAINT fk_accion_reclamo_analista FOREIGN KEY (analista_id) REFERENCES analista (id),
    CONSTRAINT fk_accion_reclamo_reclamo FOREIGN KEY (reclamo_id) REFERENCES reclamo (id)
);
