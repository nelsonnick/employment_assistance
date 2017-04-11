CREATE TABLE `person` (
`id` int NOT NULL AUTO_INCREMENT,
`person_category` int NULL,
`person_type` int NULL,
`person_state` int NULL,
`person_name` varchar(255) CHARACTER SET utf8 NULL,
`person_number` varchar(255) CHARACTER SET utf8 NULL,
`person_phone` varchar(255) CHARACTER SET utf8 NULL,
`person_address` varchar(255) CHARACTER SET utf8 NULL,
`person_company` varchar(255) CHARACTER SET utf8 NULL,
`person_timeA` datetime NULL,
`person_timeB` datetime NULL,
`person_job` int NULL,
`person_disability` varchar(255) CHARACTER SET utf8 NULL,
`person_security` varchar(255) CHARACTER SET utf8 NULL,
`person_remark` varchar(255) CHARACTER SET utf8 NULL,
`person_childNum` int NULL,
`person_remainMonth` int NULL,
`spouse_state` int NULL,
`spouse_name` varchar(255) CHARACTER SET utf8 NULL,
`spouse_number` varchar(255) CHARACTER SET utf8 NULL,
`spouse_company` varchar(255) NULL,
`spouse_time` datetime NULL,
`spouse_remark` varchar(255) CHARACTER SET utf8 NULL,
`child1_state` int NULL,
`child1_name` varchar(255) CHARACTER SET utf8 NULL,
`child1_number` varchar(255) CHARACTER SET utf8 NULL,
`child1_company` varchar(255) CHARACTER SET utf8 NULL,
`child1_time` datetime NULL,
`child1_remark` varchar(255) CHARACTER SET utf8 NULL,
`child2_state` int NULL,
`child2_name` varchar(255) CHARACTER SET utf8 NULL,
`child2_number` varchar(255) CHARACTER SET utf8 NULL,
`child2_company` varchar(255) CHARACTER SET utf8 NULL,
`child2_time` datetime NULL,
`child2_remark` varchar(255) CHARACTER SET utf8 NULL,
`child3_state` int NULL,
`child3_name` varchar(255) CHARACTER SET utf8 NULL,
`child3_number` varchar(255) CHARACTER SET utf8 NULL,
`child3_company` varchar(255) CHARACTER SET utf8 NULL,
`child3_time` datetime NULL,
`child3_remark` varchar(255) CHARACTER SET utf8 NULL,
`child4_state` int NULL,
`child4_name` varchar(255) CHARACTER SET utf8 NULL,
`child4_number` varchar(255) CHARACTER SET utf8 NULL,
`child4_company` varchar(255) CHARACTER SET utf8 NULL,
`child4_time` datetime NULL,
`child4_remark` varchar(255) CHARACTER SET utf8 NULL,
`child5_state` int NULL,
`child5_name` varchar(255) CHARACTER SET utf8 NULL,
`child5_number` varchar(255) CHARACTER SET utf8 NULL,
`child5_company` varchar(255) CHARACTER SET utf8 NULL,
`child5_time` datetime NULL,
`child5_remark` varchar(255) CHARACTER SET utf8 NULL,
`location_id` int NULL,
`state` int NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `location` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) CHARACTER SET utf8 NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `user` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) CHARACTER SET utf8 NULL,
`state` int NULL,
`pass` varchar(255) CHARACTER SET utf8 NULL,
`login` varchar(255) CHARACTER SET utf8 NULL,
`location_id` int NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `category` (
`id` int NOT NULL AUTO_INCREMENT,
`describe` varchar(255) CHARACTER SET utf8 NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `type` (
`id` int NOT NULL AUTO_INCREMENT,
`describe` varchar(255) CHARACTER SET utf8 NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `state` (
`id` int NOT NULL AUTO_INCREMENT,
`describe` varchar(255) CHARACTER SET utf8 NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `operate` (
`id` int NOT NULL AUTO_INCREMENT,
`user_id` int NULL,
`person_id` int NULL,
`time` datetime NULL,
`type` int NULL COMMENT '1新增2修改3删除',
PRIMARY KEY (`id`) 
);


ALTER TABLE `user` ADD CONSTRAINT `user_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `category` FOREIGN KEY (`person_category`) REFERENCES `category` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `type` FOREIGN KEY (`person_type`) REFERENCES `type` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_spouse` FOREIGN KEY (`spouse_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_child1` FOREIGN KEY (`child1_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_child2` FOREIGN KEY (`child2_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_child3` FOREIGN KEY (`child3_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_child4` FOREIGN KEY (`child4_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_child5` FOREIGN KEY (`child5_state`) REFERENCES `state` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`);
ALTER TABLE `operate` ADD CONSTRAINT `operate_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `operate` ADD CONSTRAINT `operate_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);
ALTER TABLE `person` ADD CONSTRAINT `state_person` FOREIGN KEY (`person_state`) REFERENCES `state` (`id`);

