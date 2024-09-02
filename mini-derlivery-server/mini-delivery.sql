create DATABASE myDatabase;

USE myDatabase;

drop table `store`;

CREATE Table `store` (
    `store_id` INT NOT NULL PRIMARY KEY,
    `store_name` VARCHAR(100) NOT NULL UNIQUE KEY,
    `address` VARCHAR(100) NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL
);

CREATE Table `user` (
    `user_id` VARCHAR(50) NOT NULL PRIMARY KEY,
    `password` VARCHAR(50) NOT NULL,
    `number` VARCHAR(15) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `user_tp_cd` VARCHAR(8) NOT NULL,
    `join_date` DATE NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL
);

CREATE TABLE `order` (
    `order_id` INT NOT NULL PRIMARY KEY,
    `store_id` INT NOT NULL,
    `user_id` VARCHAR(50) NOT NULL,
    `order_tp_cd` VARCHAR(8) NOT NULL,
    `order_date` DATE NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);

CREATE TABLE `category` (
    `category_id` INT NOT NULL PRIMARY KEY,
    `store_id` INT NOT NULL,
    `category_nm` VARCHAR(100) NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
);

CREATE TABLE `menu` (
    `menu_id` INT NOT NULL PRIMARY KEY,
    `store_name` VARCHAR(100) NOT NULL,
    `category_id` INT NOT NULL,
    `menu_nm` VARCHAR(100) NOT NULL,
    `master_price` INT NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`store_name`) REFERENCES `store`(`store_name`),
    FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`)
);

CREATE TABLE `order_item` (
    `order_id` INT NOT NULL PRIMARY KEY,
    `menu_id` INT NOT NULL,
    `count` INT NOT NULL,
    `price` INT NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`),
    FOREIGN KEY (`menu_id`) REFERENCES `menu`(`menu_id`)
);
ALTER TABLE `order` -- order 테이블에 order_tp_cd에 인덱스 추가. 기본키, 유니크키 이외에 외래키로 참조하려면 인덱스 필요
ADD UNIQUE INDEX `idx_order_tp_cd` (`order_tp_cd`); 
CREATE TABLE `order_tp` (
    `order_tp_cd` VARCHAR(8) NOT NULL PRIMARY KEY,
    `order_tp_nm` VARCHAR(100) NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`order_tp_cd`) REFERENCES `order`(`order_tp_cd`)
);
ALTER TABLE `user`
ADD UNIQUE INDEX `idx_user_tp_cd` (`user_tp_cd`);
CREATE TABLE `user_tp` (
    `user_tp_cd` VARCHAR(8) NOT NULL PRIMARY KEY,
    `user_tp_nm` VARCHAR(100) NOT NULL,
    `use_yn` VARCHAR(1) NOT NULL,
    FOREIGN KEY (`user_tp_cd`) REFERENCES `user`(`user_tp_cd`)
);

