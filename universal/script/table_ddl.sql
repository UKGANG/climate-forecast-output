use climate;

CREATE TABLE gps_location(id BIGINT NOT NULL AUTO_INCREMENT,
	ref_id BIGINT NULL,
    country NVARCHAR(255),
    city NVARCHAR(255),
    lon NVARCHAR(50),
    lat NVARCHAR(50),
    CONSTRAINT pk_gps_location PRIMARY KEY(id));

CREATE TABLE climate(id BIGINT NOT NULL AUTO_INCREMENT,
    DATE DATETIME,
    gps_location_id BIGINT NOT NULL,
    humidity DOUBLE,
    is_ces BIT,
    temp_min DOUBLE,
    temp_max DOUBLE,
    pressure DOUBLE,
    FOREIGN KEY (gps_location_id) REFERENCES gps_location(id),
    CONSTRAINT pk_climate PRIMARY KEY(id));

CREATE TABLE property(
	id BIGINT NOT NULL AUTO_INCREMENT,
    k NVARCHAR(255),
    v NVARCHAR(255),
    CONSTRAINT pk_property PRIMARY KEY(id));

CREATE INDEX index_climate_date ON climate(DATE);
CREATE INDEX index_gps_location_ref_id ON gps_location(ref_id, lon, lat);

INSERT INTO property (k, v) VALUES ('API_KEY', '8834be252524001a51c55c081de60e8b');
INSERT INTO property (k, v) VALUES ('TAOXIA_ID', '1793119');
