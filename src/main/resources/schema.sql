CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    customerId VARCHAR(255) NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR,
    company VARCHAR,
    city VARCHAR,
    country VARCHAR,
    priPhoneNumber VARCHAR,
    secPhoneNumber VARCHAR NULL,
    email VARCHAR UNIQUE,
    subscriptionDate VARCHAR,
    website VARCHAR
);