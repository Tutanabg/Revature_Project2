--DROPPERS--
drop table if exists menu_item_ingredients;
drop table if exists ingredient_order_items;
	drop table if exists ingredients;
	drop table if exists order_items;
		drop table if exists orders;
			drop table if exists order_statuses;
			drop table if exists payment_types;

drop table if exists daily_specials;
	drop table if exists menu_items;
		drop table if exists item_categories;
	drop table if exists shops;
		drop table if exists pictures;

drop table if exists user_addresses;
	drop table if exists users;
		drop table if exists user_roles;

--CREATORS--
create table pictures(
	pic_id serial primary key,
	pic_name varchar(200),
	pic_type bytea 
);

create table user_roles(
    role_id serial primary key,
	role_name varchar(50)
);

create table users(
   user_id serial primary key,
   first_name varchar(50),
   last_name varchar(50),
   phone_number varchar(50),
   email_address varchar(100),
   username varchar(50),
   password varchar(50),
   user_role int references user_roles(role_id) 	
);

create table user_addresses(
	address_id serial primary key,
	user_id int references users(user_id),
	street varchar(50),
	city varchar(50),
	zip_code int
);

create table shops(
    shop_id serial primary key,
	shop_location varchar(50),
	shop_pic int references pictures(pic_id)
);

create table item_categories(
	category_id serial primary key,
	category varchar(50)
);

create table menu_items(
    item_id serial primary key,
    item_name varchar(50),
    item_price numeric (4,2),
    item_prep_time bigint,
    item_category int references item_categories(category_id),
    item_pic int references pictures(pic_id)
);

create table daily_specials(
	daily_special_id serial primary key,
	shop_id int references shops(shop_id),
	day_of_week int,
	menu_item_id int references menu_items(item_id)
);

create table ingredients(
	ingredient_id serial primary key,
	ingredient_name varchar(50),
	cost numeric(4,2)
);

--Junction table: menu_items and ingredients--
create table menu_item_ingredients(
  	item_ingredient_id serial primary key,
	item_id int references menu_items(item_id),
	ingredient_id int references ingredients(ingredient_id)
);

create table order_statuses(
	status_id serial primary key,
	status varchar(50)
);

create table payment_types(
	payment_type_id serial primary key,
	payment_type_name varchar(50)
);

create table orders(
    order_id serial primary key,
    order_time bigint,
    order_status int references order_statuses(status_id),
    ordered_by int references users(user_id),
    order_payment int references payment_types(payment_type_id),
    delivery boolean
);

--Junction table for menu_items and order_item
create table order_items(
	order_item_id serial primary key,
	order_id int references orders(order_id),
	item_id int references menu_items(item_id),
	item_count int
);

--Junction table for extra_ingredients and order_items
create table ingredient_order_items(
  	ingredient_order_item_id serial primary key,
	order_item_id int references order_items(order_item_id),
	ingredient_id int references ingredients(ingredient_id),
	ingredient_count int
);


--POPULATORS--

	--REFERENCE TABLES--
insert into order_statuses values 
	(default, 'Order Received'),
	(default, 'Ready'),
	(default, 'Delivery in Progress'),
	(default, 'Delivered'),
	(default, 'Order Complete');

insert into item_categories values
	(default, 'Drinks'),
	(default, 'Food');

insert into payment_types values
	(default, 'cash');

insert into user_roles values
	(default, 'customer'),
	(default, 'manager');

--first nine are the menu_items in order; 10th is the shop
insert into pictures values
	(default, 'https://upload.wikimedia.org/wikipedia/commons/a/a5/Tazzina_di_caff%C3%A8_a_Ventimiglia.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/0/09/Hokitika_Cheese_and_Deli%2C_Hokitika_%283526706594%29.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/e/e3/Macchiato_FourBarrel.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/c/c8/Cappuccino_at_Sightglass_Coffee.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/A_cup_of_latte_with_lunch_at_Merewether_Beach%2CAustralia.jpeg/1280px-A_cup_of_latte_with_lunch_at_Merewether_Beach%2CAustralia.jpeg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/9/9b/Croissant%2C_cross_section.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/c/c5/Diner-Bacon-%26-Egg-Sandwich-On-Roll.jpg', '\\xDEADBEEF'),
	(default, 'https://i2.wp.com/www.mamalovesfood.com/wp-content/uploads/2021/02/CREAM-CHEESE-DANISH-2.jpg', '\\xDEADBEEF'),
	(default, 'https://upload.wikimedia.org/wikipedia/commons/5/5d/SugarCookie.JPG', '\\xDEADBEEF'),
	(default, 'https://lh5.googleusercontent.com/p/AF1QipOavX6R9WrPGCbWIL4B0hbELsO_QyBNv2x33eY=w400-h300-k-no', '\\xDEADBEEF');


	--MEAT TABLES--
insert into menu_items values
	(default, 'espresso', 2.50, 120000, 1, 1),
	(default, 'americano', 2.75, 150000, 1, 2),
	(default, 'machiatto', 3.00, 180000, 1, 3),
	(default, 'cappuccino', 3.25, 120000, 1, 4),
	(default, 'latte', 3.50, 150000, 1, 5),
	(default, 'croissant', 3.00, 30000, 2, 6),
	(default, 'breakfast sandwich', 5.00, 300000, 2, 7),
	(default, 'cheese Danish', 3.00, 30000, 2, 8),
	(default, 'sugar cookie', 2.50, 30000, 2, 9);

insert into ingredients values 
	(default, 'almond milk', 0.50),
	(default, 'sugar', 0.25),
	(default, 'artificial sweetener', 0.50),
	(default, 'soy milk', 1.00),
	(default, 'honey', 0.50),
	(default, 'extra cheese', 0.50),
	(default, 'extra spicy', 0.75);
	

insert into menu_item_ingredients values
	(default, 1, 1), (default, 1, 2), (default, 1, 3), (default, 1, 4), (default, 1, 5),
	(default, 2, 1), (default, 2, 2), (default, 2, 3), (default, 2, 4), (default, 2, 5),
	(default, 3, 1), (default, 3, 2), (default, 3, 3), (default, 3, 4), (default, 3, 5),
	(default, 4, 1), (default, 4, 2), (default, 4, 3), (default, 4, 4), (default, 4, 5),
	(default, 5, 1), (default, 5, 2), (default, 5, 3), (default, 5, 4), (default, 5, 5),
	(default, 6, 5),
	(default, 7, 6), (default, 7, 7),
	(default, 8, 5),
	(default, 9, 5);

insert into shops values 
	(default, 'Moscow', 10);

insert into users values
	(default, 'Alison', 'Saylor', '5558675309', 'alison@saylors.com', 'coffeequeen92', 'Password1', 2);

insert into user_addresses values
	(default, 1, '3621 Harley Brook Lane', 'Johnstown', 15901);

insert into daily_specials values
	(default, 1, 0, 1),
	(default, 1, 1, 2),
	(default, 1, 2, 3),
	(default, 1, 3, 4),
	(default, 1, 4, 5),
	(default, 1, 5, 6),
	(default, 1, 6, 7);
