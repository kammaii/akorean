-- Drop all the objects from the database

DROP TABLE IF EXISTS callcenter."EMPLOYEE";
DROP TABLE IF EXISTS callcenter."EMPLOYEE_TYPE";
DROP TABLE IF EXISTS callcenter.employee;
DROP TABLE IF EXISTS callcenter.employee_type;

DROP SEQUENCE IF EXISTS callcenter.EMPLOYEE_SEQ;
DROP SCHEMA IF EXISTS callcenter;

CREATE SCHEMA callcenter;

CREATE SEQUENCE callcenter.EMPLOYEE_SEQ
    INCREMENT 1
    START 0
    MINVALUE 0
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE callcenter.employee
(
    EMPLOYEE_NAME text COLLATE pg_catalog."default",
    EMPLOYEE_ID bigint NOT NULL DEFAULT nextval('callcenter.EMPLOYEE_SEQ'::regclass),
    EMPLOYEE_TYPE text COLLATE pg_catalog."default",
    CONSTRAINT EMPLOYEE_pkey PRIMARY KEY (EMPLOYEE_ID)
);


