ALTER TABLE customers CHANGE street address varchar(255) NOT NULL;
ALTER TABLE technicians CHANGE street address varchar(255) NOT NULL;
ALTER TABLE companies CHANGE street address varchar(255) NOT NULL;

ALTER TABLE customers ADD address_number int(11) NOT NULL AFTER address;
ALTER TABLE technicians ADD address_number int(11) NOT NULL AFTER address;
ALTER TABLE companies ADD address_number int(11) NOT NULL AFTER address;

ALTER TABLE customers ADD complement varchar(255) NOT NULL AFTER zip_code;
ALTER TABLE technicians ADD complement varchar(255) NOT NULL AFTER zip_code;
ALTER TABLE companies ADD complement varchar(255) NOT NULL AFTER zip_code;