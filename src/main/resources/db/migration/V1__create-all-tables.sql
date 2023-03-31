CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `neighborhood` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `schedules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `neighborhood` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `zip_code` VARCHAR(8) NOT NULL,
  `os` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `technicians` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `neighborhood` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;