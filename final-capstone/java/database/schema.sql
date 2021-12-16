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
 invite_code varchar(6),
 created_on DATE DEFAULT CURRENT_TIMESTAMP ,
 CONSTRAINT PK_group_id PRIMARY KEY (group_id)
 );
 
 CREATE TABLE member_of (
  user_id int,
  group_id int,
  invite_accepted boolean DEFAULT false,
  joined_on DATE DEFAULT CURRENT_TIMESTAMP ,
  CONSTRAINT FK_users_table_join FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT FK_member_join_group FOREIGN KEY (group_id) REFERENCES groups(group_id));


 
 CREATE TABLE lists (
 list_id SERIAL ,
 list_name varchar(50)NOT NULL,
 group_id int,
 list_claimed_by int,
 list_description varchar(50),
 CONSTRAINT PK_lists PRIMARY KEY (list_id),
 CONSTRAINT FK_lists_group_id FOREIGN KEY (group_id) REFERENCES groups (group_id),
 CONSTRAINT FK_lists_list_claimed_by FOREIGN KEY (list_claimed_by) REFERENCES users (user_id)

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
 list_item_id SERIAL,
 item_name varchar NOT NULL,
 item_amount int,
 list_id int NOT NULL,
 date_added DATE DEFAULT CURRENT_TIMESTAMP,
 user_id int,
 favorite boolean,
 --rewards_id varchar(100) UNIQUE,
 CONSTRAINT PK_items_table PRIMARY KEY (list_item_id),
 CONSTRAINT FK_items_list_id FOREIGN KEY (list_id) REFERENCES lists(list_id) ON DELETE CASCADE,
 CONSTRAINT FK_item_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
 --CONSTRAINT FK_item_savings FOREIGN KEY (rewards_id) REFERENCES retailer_store(rewards)
 );
 


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('FieriGuy@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('John@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('FredFlinstone@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('MayorDave@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Forgetful@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Elevate@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');


INSERT INTO groups (name, group_description, invite_code) VALUES ('Mayor Dave Gatherings','community gatherings', 'M1C2E4');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Java Junction', 'Cohort 12 Shenannigans', '0L3RD7');
INSERT INTO groups (name, group_description, invite_code) VALUES ('See #ies', 'A sharp set of folks', '2ZY4R8');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Golf Tracker', 'fore-get me nots', 'G6RN98');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Zig Zaggers','neighborhood', 'N5F3X2');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Cincy Techies For Good', 'coding sessions for good', 'H2D4E0');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Animal Shelter', 'so you dont keep hounding me', 'U6EL24');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Family Readers', 'dont make me fine you', 'B4R3EL');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Rental Property Managers', 'you act like you own this space', 'XF53EF');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Pet Play Pals', 'so we dont have a catastrophe', 'G6E3W3');
INSERT INTO groups (name, group_description, invite_code) VALUES ('Pub Hub', 'ale you need to know is rye here', 'P7L8Y5');


INSERT INTO lists (list_name, group_id, list_claimed_by, list_description) VALUES ('Daveville Donut Days', 1, null, 'prepare for the donut day kick off');
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Chocolate Donuts', 6, 1, 6, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Sprinkle Donuts', 4, 1, 6, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Glazed Donuts', 9, 1, 6, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Donut Holes', 10, 1, 3, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Daves Dragonberry Donuts', 2, 1, 3, false);

INSERT INTO lists (list_name, group_id, list_claimed_by, list_description) VALUES ('Java Jubilee', 2, 8, 'Java group celebration');
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Coffee Bags', 3, 2, 5, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Biscottis', 5, 2, 5, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Coffee Creamer', 2, 2, 5, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Sugar Cubes', 10, 2, 4, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Coffee Stirrers', 2, 2, 4, false);

INSERT INTO lists (list_name, group_id, list_claimed_by, list_description) VALUES ('Adoption Event', 10, null, 'event to get even more pet play time');
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Dog Bones', 8, 3, 7, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Cat Chow', 5, 3, 7, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Cookies', 2, 3, 5, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Leash', 4, 3, 5, false);
INSERT INTO items (item_name, item_amount, list_id, user_id, favorite) VALUES ('Fish food', 2, 3, 5, false);





INSERT INTO member_of(user_id, group_id, invite_accepted) VALUES (3, 1, true);
INSERT INTO member_of(user_id, group_id, invite_accepted) VALUES (8, 2, true);
INSERT INTO member_of(user_id, group_id, invite_accepted) VALUES (8, 6, true);

--INSERT INTO groups (name, group_description) VALUES ('Administrator','house_and_home');
--INSERT INTO groups (name, group_description) VALUES ('Mickey_Mouse', 'Dave_home');
--INSERT INTO lists (list_name, group_name, retail_name, list_claimed) VALUES ('Magic', 'Mickey_Mouse', 'Kroger', 'false');
--INSERT INTO retailer_store (retail_name, rewards) VALUES ('Kroger', '5133161752');
--INSERT INTO items (list_item_id,item_name, item_amount, list_name, favorite, rewards_id) VALUES ('1', 'Spaghetti_squash', '1', 'Magic','false', '5133161752');


COMMIT TRANSACTION;
