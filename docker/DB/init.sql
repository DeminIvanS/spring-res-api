drop table if exists person;
create table if not exists person
(
    id
    serial
    primary
    key,
    name
    varchar
(
    100
) not null,
    )
