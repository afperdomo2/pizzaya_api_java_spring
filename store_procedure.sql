DROP procedure IF EXISTS `take_random_pizza_order`;
DELIMITER $$
CREATE PROCEDURE `take_random_pizza_order` (
    IN customer_id BIGINT,
    IN order_type CHAR(1),
    OUT order_taken BOOL
) BEGIN DECLARE random_pizza_id INT;

DECLARE price_random_pizza DECIMAL(10, 2);

DECLARE price_with_discount DECIMAL(10, 2);

DECLARE WITH_ERRORS BOOL DEFAULT FALSE;

DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN
SET
    WITH_ERRORS = TRUE;

END;

SELECT
    id,
    price INTO random_pizza_id,
    price_random_pizza
FROM
    pizzas
WHERE
    is_available = 1
ORDER BY
    RAND ()
LIMIT
    1;

SET
    price_with_discount = price_random_pizza - (price_random_pizza * 0.20);

START TRANSACTION;

INSERT INTO
    customer_orders (customer_id, date, total, order_type, notes)
VALUES
    (
        customer_id,
        NOW(),
        price_with_discount,
        'C',
        '20% OFF PIZZA RANDOM PROMOTION'
    );

INSERT INTO
    order_items (customer_order_id, pizza_id, quantity, subtotal)
VALUES
    (
        LAST_INSERT_ID (),
        random_pizza_id,
        1,
        price_random_pizza
    );

IF WITH_ERRORS THEN
SET
    order_taken = FALSE;

ROLLBACK;

ELSE
SET
    order_taken = TRUE;

COMMIT;

END IF;

SELECT
    order_taken;

END
$$

DELIMITER ;