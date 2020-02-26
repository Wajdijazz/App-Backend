CREATE SEQUENCE hibernate_sequence START 1;
CREATE TABLE public.client
(
    client_id      bigint NOT NULL,
    client_contact character varying(255),
    client_name    character varying(255),
    PRIMARY KEY (client_id)

);

CREATE TABLE public.manager
(
    manager_id bigint NOT NULL,
    first_name character varying(255),
    last_name  character varying(255),
    PRIMARY KEY (manager_id)

);

CREATE TABLE public.person
(
    person_id  bigint NOT NULL,
    first_name character varying(255),
    last_name  character varying(255),
    is_active boolean not null,
    manager_id bigint NOT NULL,
    PRIMARY KEY (person_id)

);
ALTER TABLE person
    ADD FOREIGN KEY (manager_id) REFERENCES manager;

CREATE TABLE public.project
(
    project_id   bigint NOT NULL,
    project_name character varying(255),
    is_active boolean not null,
    client_id    bigint NOT NULL,
    manager_id   bigint NOT NULL,
    PRIMARY KEY (project_id)
);
ALTER TABLE project
    ADD FOREIGN KEY (client_id) REFERENCES client;
ALTER TABLE project
    ADD FOREIGN KEY (manager_id) REFERENCES manager;

CREATE TABLE public.intervention
(
    intervention_id bigint NOT NULL,
    date            date,
    mode            integer,
    person_id       bigint,
    project_id      bigint,
    PRIMARY KEY (intervention_id)
);

ALTER TABLE intervention
    ADD FOREIGN KEY (person_id) REFERENCES person;
ALTER TABLE intervention
    ADD FOREIGN KEY (project_id) REFERENCES project;

CREATE TABLE public.tj
(
    tj_id      bigint NOT NULL,
    tarif      real,
    person_id  bigint NOT NULL,
    project_id bigint NOT NULL
);
ALTER TABLE intervention
    ADD FOREIGN KEY (person_id) REFERENCES person;
ALTER TABLE intervention
    ADD FOREIGN KEY (project_id) REFERENCES project;



