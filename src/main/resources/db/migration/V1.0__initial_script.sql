CREATE TABLE public.client
(
    client_id     SERIAL PRIMARY KEY ,
    client_contact character varying(255),
    client_name    character varying(255)

);

CREATE TABLE public.manager
(
    manager_id SERIAL PRIMARY KEY,
    first_name character varying(255),
    last_name  character varying(255)

);

CREATE TABLE public.person
(
    person_id SERIAL PRIMARY KEY,
    first_name character varying(255),
    last_name  character varying(255),
    is_active boolean not null,
    manager_id bigint NOT NULL
);
ALTER TABLE person
    ADD FOREIGN KEY (manager_id) REFERENCES manager;

CREATE TABLE public.project
(
    project_id SERIAL PRIMARY KEY,
    project_name character varying(255),
    is_active boolean not null,
    client_id    bigint NOT NULL,
    manager_id   bigint NOT NULL
);
ALTER TABLE project
    ADD FOREIGN KEY (client_id) REFERENCES client;
ALTER TABLE project
    ADD FOREIGN KEY (manager_id) REFERENCES manager;

CREATE TABLE public.intervention
(
    intervention_id SERIAL PRIMARY KEY,
    date            date,
    mode            integer,
    person_id       bigint,
    project_id      bigint

);

ALTER TABLE intervention
    ADD FOREIGN KEY (person_id) REFERENCES person;
ALTER TABLE intervention
    ADD FOREIGN KEY (project_id) REFERENCES project;

CREATE TABLE public.tj
(
    tj_id     SERIAL PRIMARY KEY,
    tarif      real,
    person_id  bigint NOT NULL,
    project_id bigint NOT NULL
);
ALTER TABLE intervention
    ADD FOREIGN KEY (person_id) REFERENCES person;
ALTER TABLE intervention
    ADD FOREIGN KEY (project_id) REFERENCES project;



