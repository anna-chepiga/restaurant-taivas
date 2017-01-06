CREATE DATABASE restaurant_taivas;

USE restaurant_taivas;

CREATE TABLE restaurant_info (
  id                    INT          NOT NULL,
  name                  VARCHAR(20)  NOT NULL,
  address               VARCHAR(100) NOT NULL,
  phone_number          VARCHAR(20),
  email                 VARCHAR(20),
  restaurant_schema_url VARCHAR(100),
  PRIMARY KEY (id)
);

INSERT INTO restaurant_info VALUES
  (1, 'Taivas', '1119 Macdougal St, New York,<br> NY 10012, USA', '1-800-123-4567', 'taivas@gmail.com',
   '/resources/images/restaurant_info/restaurant_schema.jpg');

CREATE TABLE employee (
  id         INT         NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(20) NOT NULL,
  last_name  VARCHAR(20) NOT NULL,
  position   INT,
  salary     INT,
  photo_url  VARCHAR(50),
  dtype      VARCHAR(50),
  PRIMARY KEY (id)
);

INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Anna', 'Chepiga', 1, 100000, 'resources/images/employees_photos/1.jpg', 'Employee');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Charles', 'Dance', 2, 80000, 'resources/images/employees_photos/2.jpeg', 'Employee');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Chris', 'Hemsworth', 3, 60000, 'resources/images/employees_photos/3.jpg', 'Employee');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Tom', 'Hiddleston', 4, 50000, 'resources/images/employees_photos/4.jpg', 'Cook');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Eva', 'Green', 4, 40000, 'resources/images/employees_photos/5.jpg', 'Cook');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Johnny', 'Depp', 5, 30000, 'resources/images/employees_photos/6.jpg', 'Waiter');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Harry', 'Potter', 5, 25000, 'resources/images/employees_photos/7.jpg', 'Waiter');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Sharon', 'den Adel', 5, 27000, 'resources/images/employees_photos/8.jpg', 'Waiter');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Ronald', 'Weasley', 5, 26000, 'resources/images/employees_photos/8.jpg', 'Waiter');
INSERT INTO employee (first_name, last_name, position, salary, photo_url, dtype)
VALUES ('Jeremy', 'Renner', 4, 45000, 'resources/images/employees_photos/8.jpg', 'Cook');

CREATE TABLE position (
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO position VALUES (1, 'SEO');
INSERT INTO position VALUES (2, 'Art Director');
INSERT INTO position VALUES (3, 'Manager');
INSERT INTO position VALUES (4, 'Cooker');
INSERT INTO position VALUES (5, 'Waiter');

CREATE TABLE orders (
  id           INT  NOT NULL AUTO_INCREMENT,
  waiter_id    INT,
  table_number INT,
  date         DATE NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO orders (waiter_id, table_number, date) VALUES (6, 1, '2016-11-16');
INSERT INTO orders (waiter_id, table_number, date) VALUES (7, 2, '2016-11-16');
INSERT INTO orders (waiter_id, table_number, date) VALUES (6, 5, '2016-11-18');
INSERT INTO orders (waiter_id, table_number, date) VALUES (7, 3, '2016-11-20');
INSERT INTO orders (waiter_id, table_number, date) VALUES (8, 4, '2016-11-20');

CREATE TABLE dish (
  id        INT         NOT NULL AUTO_INCREMENT,
  name      VARCHAR(20) NOT NULL,
  category  INT,
  price     DOUBLE      NOT NULL,
  weight    INT         NOT NULL,
  photo_url VARCHAR(50),
  PRIMARY KEY (id)
);

INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Borshch', 1, 9.5, 250, 'resources/images/dish_photos/Borshch.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Pasta', 2, 9.2, 250, 'resources/images/dish_photos/Pasta.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Coffee', 3, 3.5, 250, 'resources/images/dish_photos/Coffee.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Cesar', 4, 6.5, 300, 'resources/images/dish_photos/Cesar.jpeg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Steak', 5, 7, 150, 'resources/images/dish_photos/Steak.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Scrambled eggs', 2, 4, 200, 'resources/images/dish_photos/Scrambled_eggs.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Sausage', 5, 3.2, 70, 'resources/images/dish_photos/Sausage.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Toast', 6, 2, 60, 'resources/images/dish_photos/Toast.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Cake', 7, 3.5, 100, 'resources/images/dish_photos/Cake.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Ice-cream', 7, 3, 110, 'resources/images/dish_photos/Ice-cream.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Tea', 3, 3, 250, 'resources/images/dish_photos/Tea.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Greek salad', 4, 7, 300, 'resources/images/dish_photos/Greek_salad.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Fish', 5, 6, 150, 'resources/images/dish_photos/Fish.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Juice', 3, 2.5, 250, 'resources/images/dish_photos/Juice.jpg');
INSERT INTO dish (name, category, price, weight, photo_url)
VALUES ('Wine', 3, 5, 250, 'resources/images/dish_photos/Wine.jpg');

CREATE TABLE dish_category (
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO dish_category (name) VALUES ('First course');
INSERT INTO dish_category (name) VALUES ('Main course');
INSERT INTO dish_category (name) VALUES ('Beverage');
INSERT INTO dish_category (name) VALUES ('Salad');
INSERT INTO dish_category (name) VALUES ('Meat');
INSERT INTO dish_category (name) VALUES ('Starter');
INSERT INTO dish_category (name) VALUES ('Dessert');

CREATE TABLE order_details (
  order_id       INT,
  cooked_dish_id INT
);

INSERT INTO order_details VALUES (1, 1);
INSERT INTO order_details VALUES (1, 2);
INSERT INTO order_details VALUES (2, 3);
INSERT INTO order_details VALUES (2, 4);
INSERT INTO order_details VALUES (3, 4);
INSERT INTO order_details VALUES (3, 2);
INSERT INTO order_details VALUES (4, 2);
INSERT INTO order_details VALUES (5, 2);

CREATE TABLE cooked_dish (
  id        INT NOT NULL AUTO_INCREMENT,
  dish_id   INT,
  cooker_id INT,
  order_id  INT,
  PRIMARY KEY (id)
);

INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (1, 4, 1);
INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (2, 4, 1);
INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (3, 5, 2);
INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (5, 5, 2);
INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (4, 5, 3);
INSERT INTO cooked_dish (dish_id, cooker_id, order_id) VALUES (2, 4, 3);

CREATE TABLE menu (
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO menu (name) VALUES ('Breakfast');
INSERT INTO menu (name) VALUES ('Lunch');
INSERT INTO menu (name) VALUES ('Dinner');

CREATE TABLE menu_details (
  menu_id INT,
  dish_id INT
);

INSERT INTO menu_details VALUES (1, 6);
INSERT INTO menu_details VALUES (1, 7);
INSERT INTO menu_details VALUES (1, 8);
INSERT INTO menu_details VALUES (1, 3);
INSERT INTO menu_details VALUES (1, 9);
INSERT INTO menu_details VALUES (2, 1);
INSERT INTO menu_details VALUES (2, 2);
INSERT INTO menu_details VALUES (2, 3);
INSERT INTO menu_details VALUES (2, 4);
INSERT INTO menu_details VALUES (2, 5);
INSERT INTO menu_details VALUES (2, 10);
INSERT INTO menu_details VALUES (2, 11);
INSERT INTO menu_details VALUES (3, 11);
INSERT INTO menu_details VALUES (3, 12);
INSERT INTO menu_details VALUES (3, 13);
INSERT INTO menu_details VALUES (3, 14);
INSERT INTO menu_details VALUES (3, 15);

CREATE TABLE ingredient (
  ID     INT NOT NULL AUTO_INCREMENT,
  name   VARCHAR(20),
  amount INT,
  PRIMARY KEY (id)
);

INSERT INTO ingredient (name, amount) VALUES ('Potato', 100);
INSERT INTO ingredient (name, amount) VALUES ('Beet', 90);
INSERT INTO ingredient (name, amount) VALUES ('Cabbage', 95);
INSERT INTO ingredient (name, amount) VALUES ('Onion', 102);
INSERT INTO ingredient (name, amount) VALUES ('Rice', 50);
INSERT INTO ingredient (name, amount) VALUES ('Pasta', 60);
INSERT INTO ingredient (name, amount) VALUES ('Sauce', 30);
INSERT INTO ingredient (name, amount) VALUES ('Tea', 30);
INSERT INTO ingredient (name, amount) VALUES ('Coffee', 40);
INSERT INTO ingredient (name, amount) VALUES ('Apple juice', 55);
INSERT INTO ingredient (name, amount) VALUES ('Orange juice', 75);
INSERT INTO ingredient (name, amount) VALUES ('Sweet pepper', 35);
INSERT INTO ingredient (name, amount) VALUES ('Cheese', 28);
INSERT INTO ingredient (name, amount) VALUES ('Cucumber', 62);
INSERT INTO ingredient (name, amount) VALUES ('Sun oil', 15);
INSERT INTO ingredient (name, amount) VALUES ('Pork', 105);
INSERT INTO ingredient (name, amount) VALUES ('Beef', 88);
INSERT INTO ingredient (name, amount) VALUES ('Eggs', 150);
INSERT INTO ingredient (name, amount) VALUES ('Bread', 200);
INSERT INTO ingredient (name, amount) VALUES ('Butter', 100);
INSERT INTO ingredient (name, amount) VALUES ('Jam', 95);
INSERT INTO ingredient (name, amount) VALUES ('Milk', 180);
INSERT INTO ingredient (name, amount) VALUES ('Tomato', 230);
INSERT INTO ingredient (name, amount) VALUES ('Olives', 105);
INSERT INTO ingredient (name, amount) VALUES ('Fish', 95);
INSERT INTO ingredient (name, amount) VALUES ('Red wine', 75);
INSERT INTO ingredient (name, amount) VALUES ('White wine', 85);

CREATE TABLE dish_details (
  dish_id       INT,
  ingredient_id INT
);

INSERT INTO dish_details VALUES (1, 1);
INSERT INTO dish_details VALUES (1, 3);
INSERT INTO dish_details VALUES (1, 4);
INSERT INTO dish_details VALUES (1, 12);
INSERT INTO dish_details VALUES (1, 15);
INSERT INTO dish_details VALUES (2, 4);
INSERT INTO dish_details VALUES (2, 6);
INSERT INTO dish_details VALUES (2, 7);
INSERT INTO dish_details VALUES (2, 13);
INSERT INTO dish_details VALUES (3, 9);
INSERT INTO dish_details VALUES (4, 3);
INSERT INTO dish_details VALUES (4, 7);
INSERT INTO dish_details VALUES (4, 13);
INSERT INTO dish_details VALUES (4, 16);
INSERT INTO dish_details VALUES (5, 17);
INSERT INTO dish_details VALUES (6, 18);
INSERT INTO dish_details VALUES (6, 4);
INSERT INTO dish_details VALUES (6, 15);
INSERT INTO dish_details VALUES (7, 18);
INSERT INTO dish_details VALUES (8, 19);
INSERT INTO dish_details VALUES (9, 19);
INSERT INTO dish_details VALUES (9, 20);
INSERT INTO dish_details VALUES (9, 21);
INSERT INTO dish_details VALUES (10, 20);
INSERT INTO dish_details VALUES (10, 21);
INSERT INTO dish_details VALUES (10, 22);
INSERT INTO dish_details VALUES (11, 8);
INSERT INTO dish_details VALUES (12, 4);
INSERT INTO dish_details VALUES (12, 12);
INSERT INTO dish_details VALUES (12, 13);
INSERT INTO dish_details VALUES (12, 14);
INSERT INTO dish_details VALUES (12, 15);
INSERT INTO dish_details VALUES (12, 23);
INSERT INTO dish_details VALUES (12, 24);
INSERT INTO dish_details VALUES (13, 25);
INSERT INTO dish_details VALUES (14, 10);
INSERT INTO dish_details VALUES (14, 11);
INSERT INTO dish_details VALUES (15, 26);
INSERT INTO dish_details VALUES (15, 27);

CREATE TABLE user (
  id       INT          NOT NULL AUTO_INCREMENT,
  username VARCHAR(50)  NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  email    VARCHAR(20)  NOT NULL UNIQUE,
  name     VARCHAR(50),
  role     INT          NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

INSERT INTO user (username, password, email, name, role)
VALUES ('admin',
        '1c71ad1441cb1ad17612a15d1a01a41521f91e81541fd1c11e01e71a512a13810115f1231f31ea1b11d810b19311d1d417216314d1fa1c711c1d314e1bc1351d116a1b71fb18a1901c811f1971511131d61c715318d1c619d1d81de1901771ec',
        'anna.chepiga@list.ru', 'anna', 2);
INSERT INTO user (username, password, email, name, role)
VALUES ('user',
        '1b114316114014c10718f1fd15419c1031db14413c13f1ed1e21f31e51341d713f1781f71731011ed1971d41a41361a91fd19d1b015e1e81b31251c01ad13614318b1431fe1c815110c12014f1c11c11ed1b211d1091411c010e19e12c11c1e2',
        'user@gmail.com', 'user', 1);


CREATE TABLE user_role (
  id   INT         NOT NULL,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO user_role VALUES (1, 'user');
INSERT INTO user_role VALUES (2, 'admin');

CREATE TABLE user_orders (
  user_id  INT,
  order_id INT
);

INSERT INTO user_orders VALUES (2, 1);
INSERT INTO user_orders VALUES (2, 4);

ALTER TABLE cooked_dish
  ADD CONSTRAINT dish_description_id_FK FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  ADD CONSTRAINT cooker_id_FK FOREIGN KEY (cooker_id)
REFERENCES employee (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  ADD CONSTRAINT order_id_FK FOREIGN KEY (order_id)
REFERENCES orders (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE dish
  ADD CONSTRAINT dish_category_FK FOREIGN KEY (category)
REFERENCES dish_category (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE employee
  ADD CONSTRAINT employee_position_FK FOREIGN KEY (position)
REFERENCES position (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE orders
  ADD CONSTRAINT order_waiter_FK FOREIGN KEY (waiter_id)
REFERENCES employee (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE dish_details
  ADD CONSTRAINT ingr_to_dish_Dish_FK FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  ADD CONSTRAINT ingr_to_dish_Ingredient_FK FOREIGN KEY (ingredient_id)
REFERENCES ingredient (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE menu_details
  ADD CONSTRAINT dish_to_menu_Dish_FK FOREIGN KEY (dish_id)
REFERENCES dish (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  ADD CONSTRAINT dish_to_menu_Menu_FK FOREIGN KEY (menu_id)
REFERENCES menu (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE order_details
  ADD CONSTRAINT dish_to_order_Order_FK FOREIGN KEY (order_id)
REFERENCES orders (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT dish_to_order_Dish_FK FOREIGN KEY (cooked_dish_id)
REFERENCES cooked_dish (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

ALTER TABLE user
  ADD CONSTRAINT user_role_FK FOREIGN KEY (role)
REFERENCES user_role (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;

ALTER TABLE user_orders
  ADD CONSTRAINT user_orders_User_FK FOREIGN KEY (user_id)
REFERENCES user (id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  ADD CONSTRAINT user_orders_Order_FK FOREIGN KEY (order_id)
REFERENCES orders (id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

DELIMITER //
CREATE PROCEDURE select_id(OUT param INT)
  BEGIN
    SELECT last_insert_id()
    INTO param;
  END//