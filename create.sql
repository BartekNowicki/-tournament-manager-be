
    create table players (
       bug_id bigint not null,
        device_id bigint,
        tester_id bigint,
        primary key (bug_id)
    ) engine=InnoDB;

    create table bugs_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into bugs_seq values ( 1 );

    create table singlesTournaments (
       device_id bigint not null,
        description varchar(255),
        primary key (device_id)
    ) engine=InnoDB;

    create table devices_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into devices_seq values ( 1 );

    create table tester_device (
       tester_id bigint not null,
        device_id bigint,
        primary key (tester_id)
    ) engine=InnoDB;

    create table tester_device_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into tester_device_seq values ( 1 );

    create table testers (
       tester_id bigint not null,
        country varchar(255),
        first_name varchar(255),
        last_login varchar(255),
        last_name varchar(255),
        primary key (tester_id)
    ) engine=InnoDB;

    create table testers_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into testers_seq values ( 1 );

    create table things (
       id bigint not null,
        description varchar(255),
        good bit,
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table things_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into things_seq values ( 1 );

    create table players (
       bug_id bigint not null,
        device_id bigint,
        tester_id bigint,
        primary key (bug_id)
    ) engine=InnoDB;

    create table bugs_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into bugs_seq values ( 1 );

    create table singlesTournaments (
       device_id bigint not null,
        description varchar(255),
        primary key (device_id)
    ) engine=InnoDB;

    create table devices_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into devices_seq values ( 1 );

    create table tester_device (
       tester_id bigint not null,
        device_id bigint,
        primary key (tester_id)
    ) engine=InnoDB;

    create table tester_device_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into tester_device_seq values ( 1 );

    create table testers (
       tester_id bigint not null,
        country varchar(255),
        first_name varchar(255),
        last_login varchar(255),
        last_name varchar(255),
        primary key (tester_id)
    ) engine=InnoDB;

    create table testers_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into testers_seq values ( 1 );

    create table things (
       id bigint not null,
        description varchar(255),
        good bit,
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table things_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into things_seq values ( 1 );

    create table players (
       bug_id bigint not null,
        device_id bigint,
        tester_id bigint,
        primary key (bug_id)
    ) engine=InnoDB;

    create table bugs_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into bugs_seq values ( 1 );

    create table singlesTournaments (
       device_id bigint not null,
        description varchar(255),
        primary key (device_id)
    ) engine=InnoDB;

    create table devices_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into devices_seq values ( 1 );

    create table tester_device (
       tester_id bigint not null,
        device_id bigint,
        primary key (tester_id)
    ) engine=InnoDB;

    create table tester_device_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into tester_device_seq values ( 1 );

    create table testers (
       tester_id bigint not null,
        country varchar(255),
        first_name varchar(255),
        last_login varchar(255),
        last_name varchar(255),
        primary key (tester_id)
    ) engine=InnoDB;

    create table testers_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into testers_seq values ( 1 );

    create table things (
       id bigint not null,
        description varchar(255),
        good bit,
        title varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table things_seq (
       next_val bigint
    ) engine=InnoDB;

    insert into things_seq values ( 1 );
