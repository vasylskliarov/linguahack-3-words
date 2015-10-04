# --- !Ups

CREATE TABLE word (
	id SERIAL integer NOT NULL,
	normalized_value character varying,
	showed_count integer,
	unknown_count integer,
	CONSTRAINT word_pkey PRIMARY KEY (id)
);

CREATE TABLE text (
    id SERIAL integer NOT NULL,
  	file_name character varying,
	text character varying,
	normalized_text character varying,
	showed_count integer,
	CONSTRAINT text_pkey PRIMARY KEY (id)
);

# --- !Downs
