INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Reebok', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('New Balance', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Under Armour', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Converse', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Vans', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Fila', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO BRAND (BRAND_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Levi', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('상의', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('아우터', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('바지', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('스니커즈', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('가방', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('모자', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('양말', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY (CATEGORY_NAME, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('액세서리', false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Nike
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Upperwear1', 10000, 1, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Upperwear2', 12000, 1, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Upperwear3', 15000, 1, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Outerwear1', 20000, 2, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Outerwear2', 23000, 2, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Outerwear3', 25000, 2, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Pants1', 15000, 3, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Pants2', 17000, 3, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Nike Pants3', 19000, 3, 1, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Adidas
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Upperwear1', 11000, 1, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Upperwear2', 13000, 1, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Upperwear3', 16000, 1, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Outerwear1', 21000, 2, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Outerwear2', 23000, 2, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Outerwear3', 27000, 2, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Pants1', 16000, 3, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Pants2', 18000, 3, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Adidas Pants3', 20000, 3, 2, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Puma
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Upperwear1', 12000, 1, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Upperwear2', 14000, 1, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Upperwear3', 17000, 1, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Outerwear1', 22000, 2, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Outerwear2', 24000, 2, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Outerwear3', 28000, 2, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Pants1', 17000, 3, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Pants2', 19000, 3, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, CATEGORY_ID, BRAND_ID, DELETE_YN, CREATED_AT, UPDATED_AT)
VALUES ('Puma Pants3', 22000, 3, 3, false, '2024-01-01 12:00:00', '2024-01-01 12:00:00');


-- Nike 최고가
INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 1, 3, 15000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 2, 6, 25000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 3, 9, 19000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Adidas 최고가
INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 1, 12, 16000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 2, 15, 27000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 3, 18, 20000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Puma 최고가
INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 1, 21, 17000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 2, 24, 28000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_HIGHEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, HIGH_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 3, 27, 22000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Nike 최저가
INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 1, 1, 10000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 2, 4, 20000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (1, 3, 7, 15000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Adidas 최저가
INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 1, 10, 11000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 2, 13, 21000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (2, 3, 16, 16000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

-- Puma 최저가
INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 1, 19, 12000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 2, 22, 22000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');

INSERT INTO CATEGORY_LOWEST_PRICE (BRAND_ID, CATEGORY_ID, PRODUCT_ID, LOWEST_PRICE, CREATED_AT, UPDATED_AT)
VALUES (3, 3, 25, 17000, '2024-01-01 12:00:00', '2024-01-01 12:00:00');
