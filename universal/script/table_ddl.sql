use climate;

create table gps_location(id bigint not null auto_increment,
	ref_id bigint null,
    country nvarchar(255),
    city nvarchar(255),
    lon double,
    lat double,
    constraint pk_gps_location primary key(id));

create table climate(id bigint not null auto_increment,
    date datetime,
    gps_location_id bigint not null,
    humidity double,
    is_ces bit,
    temp_min double,
    temp_max double,
    pressure double,
    FOREIGN KEY (gps_location_id) REFERENCES gps_location(id),
    constraint pk_climate primary key(id));
