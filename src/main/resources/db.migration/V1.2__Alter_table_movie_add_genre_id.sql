ALTER TABLE movie
    ADD COLUMN genre_id BIGINT;

ALTER TABLE movie
    ADD CONSTRAINT genre_id FOREIGN KEY (genre_id) REFERENCES genre (id);