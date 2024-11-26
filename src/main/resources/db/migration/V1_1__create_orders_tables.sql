CREATE TABLE `orders` (
  `id` int NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total_amount` DECIMAL NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `order_items` (
  `id` int NOT NULL,  
  `order_id` int NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` DECIMAL NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_order_items_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);
