CREATE TABLE companies (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `cnpj` VARCHAR(255),
    `email` VARCHAR(255),
    `phone` VARCHAR(255),
    `cellphone` VARCHAR(255),
    `city` varchar(255) NOT NULL,
    `neighborhood` varchar(255) NOT NULL,
    `state` varchar(255) NOT NULL,
    `street` varchar(255) NOT NULL,
    `zip_code` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
