DROP DATABASE IF EXISTS productswishlist;
    CREATE SCHEMA productswishlist;
DROP TABLE IF EXISTS products;
CREATE TABLE `productswishlist`.`products` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(45) NOT NULL,
                                               `price` DOUBLE NOT NULL,
                                               PRIMARY KEY (`id`));
INSERT INTO `productswishlist`.`products` (`id`, `name`, `price`) VALUES ('1', 'Plakat', '20.0');
INSERT INTO `productswishlist`.`products` (`id`, `name`, `price`) VALUES ('2', 'Viskestykke', '20.0');

