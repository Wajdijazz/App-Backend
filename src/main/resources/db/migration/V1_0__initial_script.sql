
CREATE TABLE public.client (
    client_id bigint NOT NULL,
    client_contact character varying(255),
    client_name character varying(255)
);






CREATE TABLE public.intervention (
    intervention_id bigint NOT NULL,
    date date,
    mode integer,
    person_id bigint,
    project_id bigint
);



CREATE TABLE public.manager (
    manager_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);




CREATE TABLE public.person (
    person_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    manager_id bigint NOT NULL
);



CREATE TABLE public.project (
    project_id bigint NOT NULL,
    project_name character varying(255),
    client_id bigint NOT NULL,
    manager_id bigint NOT NULL
);




CREATE TABLE public.tj (
    tj_id bigint NOT NULL,
    tarif real,
    person_id bigint NOT NULL,
    project_id bigint NOT NULL
);




