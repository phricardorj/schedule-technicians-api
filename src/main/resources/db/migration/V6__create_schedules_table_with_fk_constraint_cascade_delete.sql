ALTER TABLE schedules
ADD CONSTRAINT fk_customer_id
FOREIGN KEY (customer_id)
REFERENCES customers(id)
ON DELETE CASCADE;