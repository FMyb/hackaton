create table if not exists methods
(
    method_id uuid         not null primary key,
    full_name varchar(511) not null,
    name      varchar(127) not null
);

create table if not exists archive_states
(
    archive_state_id uuid not null primary key,
    method_id        uuid not null references methods (method_id),
    avg_byte_count   bigint,
    avg_time         bigint,
    timestamp        bigint,
    unique (method_id, timestamp)
);

create table if not exists events
(
    event_id         uuid not null primary key,
    method_id        uuid not null references methods (method_id),
    start_timestamp  bigint,
    finish_timestamp bigint,
    bytes_count      bigint,
    stacktrace       text
);

