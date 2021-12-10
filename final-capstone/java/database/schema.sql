BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, groups, lists, items, member_of CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50)UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(10) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
	);
	
	 
 

CREATE TABLE groups (
 group_id SERIAL UNIQUE,
 name varchar(50) NOT NULL,
 group_description varchar(50),
 created_on DATE DEFAULT CURRENT_TIMESTAMP ,
 CONSTRAINT PK_group_id PRIMARY KEY (group_id)
 );
 
 CREATE TABLE member_of (
  user_id int,
  group_id int,
  invite_accepted boolean,
  joined_on DATE DEFAULT CURRENT_TIMESTAMP ,
  CONSTRAINT FK_users_table_join FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT FK_member_join_group FOREIGN KEY (group_id) REFERENCES groups(group_id));


 
 CREATE TABLE lists (
 list_id SERIAL ,
 list_name varchar(50)NOT NULL,
 group_id int ,
 list_claimed boolean,
 list_description varchar(50),
 CONSTRAINT PK_lists PRIMARY KEY (list_id),
 CONSTRAINT FK_lists_group_id FOREIGN KEY (group_id) REFERENCES groups (group_id)
 );
 
-- CREATE TABLE retailer_store (
-- retailer_id SERIAL,
-- retail_name varchar(50),
-- rewards varchar(100),
-- CONSTRAINT PK_retailer_store PRIMARY KEY (retailer_id)
-- --CONSTRAINT FK_retailer_list FOREIGN KEY (retail_name) REFERENCES lists(retail_name)
-- );
-- 
-- CREATE TABLE retailer_list (
-- retailer_id int, 
-- list_id int,
-- CONSTRAINT PK_retailer_id PRIMARY KEY (retailer_id, list_id),
-- CONSTRAINT FK_retailer_list_retailer_id FOREIGN KEY (retailer_id) REFERENCES retailer_store(retailer_id),
-- CONSTRAINT FK_retailer_list_list_id FOREIGN KEY (list_id) REFERENCES lists(list_id)
-- );
 

 
 CREATE TABLE items (
 list_item_id varchar(50) NOT NULL ,
 item_name varchar NOT NULL,
 item_amount int,
 list_id int NOT NULL,
 date_added DATE DEFAULT CURRENT_TIMESTAMP,
 user_id int,
 favorite boolean,
 --rewards_id varchar(100) UNIQUE,
 CONSTRAINT PK_items_table PRIMARY KEY (list_item_id),
 CONSTRAINT FK_items_list_id FOREIGN KEY (list_id) REFERENCES lists(list_id)
 --CONSTRAINT FK_item_savings FOREIGN KEY (rewards_id) REFERENCES retailer_store(rewards)
 );
 


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--INSERT INTO groups (name, group_description) VALUES ('Administrator','house_and_home');
--INSERT INTO groups (name, group_description) VALUES ('Mickey_Mouse', 'Dave_home');
--INSERT INTO lists (list_name, group_name, retail_name, list_claimed) VALUES ('Magic', 'Mickey_Mouse', 'Kroger', 'false');
--INSERT INTO retailer_store (retail_name, rewards) VALUES ('Kroger', '5133161752');
--INSERT INTO items (list_item_id,item_name, item_amount, list_name, favorite, rewards_id) VALUES ('1', 'Spaghetti_squash', '1', 'Magic','false', '5133161752');


COMMIT TRANSACTION;
