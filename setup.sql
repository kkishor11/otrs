
create database otrs;
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
  email varchar(100) NOT NULL PRIMARY KEY,
  password varchar(100) DEFAULT NULL,
  first_name varchar(100) DEFAULT NULL,
  last_name varchar(100) DEFAULT NULL
);

create table booking(
	booking_id integer AUTO_INCREMENT primary key,
	restaurant_id integer,
	restaurant_name varchar(255),
	no_of_booked_tables integer,
	user_id varchar(100),
	booking_date datetime,
	price decimal(10,2),
	foreign key (restaurant_id) references restaurant(restaurant_id),
	foreign key (user_id) references user(email)
);

create table pricing(
	restaurant_id integer,
	price_per_table decimal(10,2),
	foreign key (restaurant_id) references restaurant(restaurant_id)
);


INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Vrindhavan', 'Bangalore', 30);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Barbeque Nation Restaurant', 'Bangalore', 34);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Absolute Barbeque', 'Bangalore', 12);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Barbeque Nation Restaurant', 'Delhi', 43);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('More than Parathas', 'Delhi', 32);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Metro', 'Delhi', 32);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Subway', 'Delhi', 10);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Bypass Dhaba', 'Kolkata', 55);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('The Dhaba', 'Mumbai', 100);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Village', 'Mumbai', 100);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Copper Chimney', 'Muzaffarpur', 30);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Lemon Grass', 'Muzaffarpur', 30);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Kaveri Restaurant', 'Muzaffarpur', 30);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Vaishali Cafe', 'Muzaffarpur', 30);
INSERT INTO otrs.restaurant(name, city, no_of_tables) VALUES('Music Cafe', 'Muzaffarpur', 20);

INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(1, 500.99);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(2, 650);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(3, 1200);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(4, 760);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(5, 430);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(6, 880);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(7, 990);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(8, 999);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(9, 1199);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(10, 1299);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(11, 1399);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(12, 1499);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(13, 999);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(14, 765);
INSERT INTO otrs.pricing (restaurant_id, price_per_table) VALUES(15, 555);
