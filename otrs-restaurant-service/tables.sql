
use otrs;
drop table if exists booking;
drop table if exists pricing;
drop table if exists otrs.user;
drop table if exists restaurant;

create table restaurant(
	restaurant_id integer AUTO_INCREMENT primary key,
	name varchar(100),
	city varchar(100),
	no_of_tables integer,
	constraint uc_name_city unique (name,city)
);

CREATE TABLE user (
  email varchar(100) NOT NULL,
  password varchar(100) DEFAULT NULL,
  first_name varchar(100) DEFAULT NULL,
  last_name varchar(100) DEFAULT NULL,
  PRIMARY KEY (email)
);

create table booking(
	booking_id integer AUTO_INCREMENT primary key,
	restaurant_id integer,
	no_of_booked_tables integer,
	user_id varchar(100),
	booking_date datetime,
	foreign key (restaurant_id) references restaurant(restaurant_id),
	foreign key (user_id) references user(email)
);

create table pricing(
	restaurant_id integer,
	price_per_table decimal(10,2),
	foreign key (restaurant_id) references restaurant(restaurant_id)
);


