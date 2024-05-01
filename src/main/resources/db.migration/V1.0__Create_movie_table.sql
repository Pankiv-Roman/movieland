CREATE TABLE movie
(
    id      SERIAL PRIMARY KEY,
    nameUkrainian   VARCHAR,
    nameNative  VARCHAR,
    yearOfRealise   INTEGER,
    rating  DOUBLE PRECISION,
    price   DOUBLE PRECISION,
    picturePath VARCHAR
);