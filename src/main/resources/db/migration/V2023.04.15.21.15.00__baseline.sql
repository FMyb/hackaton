create table if not exists methods
(
    method_id bigint         not null primary key,
    full_name varchar(511) not null,
    name      varchar(127) not null
);

CREATE SEQUENCE if not exists id_seq INCREMENT 5 START 100;

create table if not exists archive_states
(
    archive_state_id bigint not null primary key,
    method_id        bigint not null references methods (method_id),
    avg_byte_count   numeric(10,3),
    avg_time         numeric(10,3),
    timestamp        bigint,
    unique (method_id, timestamp)
);

create table if not exists events
(
    event_id         bigint not null primary key,
    method_id        bigint not null references methods (method_id),
    start_timestamp  bigint,
    finish_timestamp bigint,
    bytes_count      bigint,
    stacktrace       text
);

