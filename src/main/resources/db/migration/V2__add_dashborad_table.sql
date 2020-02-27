CREATE TABLE public.dashboard
(
    dashboard_id SERIAL PRIMARY KEY,
    person_id    bigint,
    project_id   bigint,
    tarif        real,
    worked_day   real,
    total        real
);
ALTER TABLE dashboard
    ADD FOREIGN KEY (person_id) REFERENCES person;
ALTER TABLE dashboard
    ADD FOREIGN KEY (project_id) REFERENCES project
