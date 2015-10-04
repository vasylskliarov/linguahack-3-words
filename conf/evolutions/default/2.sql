# --- !Ups

CREATE SEQUENCE word_seq;
ALTER TABLE word
        ALTER COLUMN id SET NOT NULL
        , ALTER COLUMN id SET DEFAULT nextval('word_seq')
        ;
ALTER SEQUENCE word_seq
        OWNED BY word.id
        ;
SELECT setval('word_seq', MAX(w.id))
FROM word w
        ;


# --- !Downs
