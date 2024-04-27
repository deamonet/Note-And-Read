CREATE TABLE `read_and_note`.`user`  (
    `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    `username` varchar(30) NOT NULL,
    `email` varchar(30) NOT NULL,
    `phone_number` varchar(20) NOT NULL,
    `password` varchar(50) NOT NULL,
    `registration` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`, `username`, `phone_number`, `email`),
    UNIQUE INDEX `username_index`(`username`) USING BTREE,
    UNIQUE INDEX `email_index`(`email`) USING BTREE,
    UNIQUE INDEX `phone_number_index`(`phone_number`) USING BTREE
);