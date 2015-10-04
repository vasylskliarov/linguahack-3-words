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

CREATE INDEX text_id_idx
  ON text
  USING btree
  (id);
  
CREATE INDEX id
  ON word
  USING btree
  (id);

CREATE INDEX normalized_value
  ON word
  USING btree
  (normalized_value COLLATE pg_catalog."default");
  
# --- !Downs
