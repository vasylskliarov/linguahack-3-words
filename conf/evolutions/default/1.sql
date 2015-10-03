# --- !Ups

CREATE TABLE word (
    id int NOT NULL,
    normalized_value character varying,
    showed_count int,
    unknown_count int
);

# --- !Downs
