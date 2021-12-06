BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, groups, lists CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE groups (
 group_id SERIAL,
 name varchar(50)UNIQUE NOT NULL,
 user_id int NOT NULL,
 invite_sent boolean ,
 role varchar(10) NOT NULL,
 joined_on DATE DEFAULT CURRENT_TIMESTAMP ,
 CONSTRAINT PK_group_id PRIMARY KEY (group_id),
 CONSTRAINT FK_groups_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

 );
 
 CREATE TABLE lists (
 list_name varchar(50)UNIQUE NOT NULL,
 group_id int NOT NULL,
 group_name varchar(50) UNIQUE ,
 retail_name varchar(50) UNIQUE,
 list_claimed boolean,
 CONSTRAINT PK_lists PRIMARY KEY (list_name),
  CONSTRAINT FK_lists_group_name FOREIGN KEY (group_name) REFERENCES groups (name)
 );
 
 CREATE TABLE items (
 list_item_id varchar(50) NOT NULL,
 item_name varchar UNIQUE NOT NULL,
 item_amount int,
 list_name varchar UNIQUE NOT NULL,
 date_added DATE DEFAULT CURRENT_TIMESTAMP,
 user_id int NOT NULL,
 favorite boolean,
 coupon boolean,
 CONSTRAINT PK_items_table PRIMARY KEY (list_item_id),
 CONSTRAINT FK_items_list_name FOREIGN KEY (list_name) REFERENCES lists(list_name)
 );
 


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');



COMMIT TRANSACTION;
