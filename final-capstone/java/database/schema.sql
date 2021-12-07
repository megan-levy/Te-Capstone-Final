BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, groups, lists, items CASCADE;
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
	role varchar(10) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
	);


CREATE TABLE groups (
 group_id SERIAL,
 name varchar(50)UNIQUE NOT NULL,
 user_id int ,
 invite_sent boolean ,
 role varchar(10) NOT NULL,
 joined_on DATE DEFAULT CURRENT_TIMESTAMP ,
 CONSTRAINT PK_group_id PRIMARY KEY (group_id),
 CONSTRAINT FK_groups_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
 );

 
 CREATE TABLE lists (
 list_id SERIAL ,
 list_name varchar(50)UNIQUE NOT NULL,
 group_id int ,
 group_name varchar(50) UNIQUE ,
 retail_name varchar(50) UNIQUE,
 list_claimed boolean,
 CONSTRAINT PK_lists PRIMARY KEY (list_id),
  CONSTRAINT FK_lists_group_name FOREIGN KEY (group_name) REFERENCES groups (name)
 );
 
 CREATE TABLE retailer_store (
 retailer_id SERIAL,
 retail_name varchar(50) UNIQUE,
 rewards varchar(100) UNIQUE,
 group_id int,
 CONSTRAINT PK_retailer_store PRIMARY KEY (retailer_id),
 CONSTRAINT FK_retailer_list FOREIGN KEY (retail_name) REFERENCES lists(retail_name),
 CONSTRAINT FK_one_more_key FOREIGN KEY (group_id) REFERENCES groups (group_id)
 
 );
 
 
 CREATE TABLE items (
 list_item_id varchar(50) NOT NULL ,
 item_name varchar UNIQUE NOT NULL,
 item_amount int,
 list_name varchar UNIQUE NOT NULL,
 date_added DATE DEFAULT CURRENT_TIMESTAMP,
 user_id int,
 favorite boolean,
 rewards_id varchar(100) UNIQUE,
 CONSTRAINT PK_items_table PRIMARY KEY (list_item_id),
 CONSTRAINT FK_items_list_name FOREIGN KEY (list_name) REFERENCES lists(list_name),
 CONSTRAINT FK_item_savings FOREIGN KEY (rewards_id) REFERENCES retailer_store(rewards)
 );
 


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO groups (name,invite_sent,role) VALUES ('Administrator','true', 'ROLE_ADMIN');
INSERT INTO groups (name, invite_sent, role) VALUES ('Mickey_Mouse', 'true', 'ROLE_USER');
INSERT INTO lists (list_name, group_name, retail_name, list_claimed) VALUES ('Magic', 'Mickey_Mouse', 'Kroger', 'false');
INSERT INTO retailer_store (retail_name, rewards) VALUES ('Kroger', '5133161752');
INSERT INTO items (list_item_id,item_name, item_amount, list_name, favorite, rewards_id) VALUES ('1', 'Spaghetti_squash', '1', 'Magic','false', '5133161752');


COMMIT TRANSACTION;
