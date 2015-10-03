# --- !Ups

CREATE TABLE word (
    id int NOT NULL,
    normalized_value character varying,
    showed_count int,
    unknown_count int
);

CREATE TABLE text (
    id int NOT NULL,
    file_name character varying,
    text character varying,
    normalized_text int,
    showed_count int
);

# --- !Downs
