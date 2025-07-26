--liquibase formatted sql

--changeset FernandaMendoza:1

CREATE TABLE CLIENTES (
  ID BIGSERIAL NOT NULL,
  NOMBRE VARCHAR(100) NOT NULL,
  APELLIDO VARCHAR(100) NOT NULL,
  EDAD INTEGER NOT NULL,
  FECHA_NACIMIENTO DATE NOT NULL,
  CREATED_DATE TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  MODIFIED_DATE TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT PK_CLIENTES PRIMARY KEY (ID)
);

COMMENT ON TABLE CLIENTES IS 'Tabla que almacena la información básica de los clientes registrados.';

COMMENT ON COLUMN CLIENTES.ID IS 'Identificador único del cliente';
COMMENT ON COLUMN CLIENTES.NOMBRE IS 'Nombre del cliente';
COMMENT ON COLUMN CLIENTES.APELLIDO IS 'Apellido del cliente';
COMMENT ON COLUMN CLIENTES.EDAD IS 'Edad actual del cliente (valor numérico entero)';
COMMENT ON COLUMN CLIENTES.FECHA_NACIMIENTO IS 'Fecha de nacimiento del cliente (formato yyyy-MM-dd)';
COMMENT ON COLUMN CLIENTES.CREATED_DATE IS 'Fecha de creación del registro del cliente';
COMMENT ON COLUMN CLIENTES.MODIFIED_DATE IS 'Última fecha de modificación del registro del cliente';
