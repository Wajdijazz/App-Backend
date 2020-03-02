CREATE TABLE public.client
(
    client_id      SERIAL PRIMARY KEY,
    client_contact character varying(255) NOT NULL,
    client_name    character varying(255) NOT NULL
);

CREATE TABLE public.manager
(
    manager_id SERIAL PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name  character varying(255) NOT NULL

);

CREATE TABLE public.person
(
    person_id  SERIAL PRIMARY KEY,
    first_name character varying(64),
    last_name  character varying(64),
    is_active  boolean not null,
    manager_id bigint  NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES manager (manager_id)
);

CREATE TABLE public.project
(
    project_id   SERIAL PRIMARY KEY,
    project_name character varying(64),
    is_active    boolean not null,
    client_id    bigint  NOT NULL,
    manager_id   bigint  NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (client_id),
    FOREIGN KEY (manager_id) REFERENCES manager (manager_id)
);

CREATE TABLE public.intervention
(
    intervention_id SERIAL PRIMARY KEY,
    date            date    NOT NULL,
    mode            integer NOT NULL,
    person_id       bigint  NOT NULL,
    project_id      bigint  NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (person_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id)
);

CREATE TABLE public.tj
(
    tj_id      SERIAL PRIMARY KEY,
    tarif      real   NOT NULL,
    person_id  bigint NOT NULL,
    project_id bigint NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (person_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id)
);



