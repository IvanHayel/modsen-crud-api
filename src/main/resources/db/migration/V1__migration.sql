CREATE TABLE events
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at  TIMESTAMP with time zone                NOT NULL,
    updated_at  TIMESTAMP with time zone                NOT NULL,
    theme       VARCHAR(255)                            NOT NULL,
    description VARCHAR(255)                            NOT NULL,
    organizer   VARCHAR(255)                            NOT NULL,
    place       VARCHAR(255)                            NOT NULL,
    start_date  TIMESTAMP with time zone                NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id)
);