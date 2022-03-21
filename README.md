**Estructura de la base de datos**

CREATE DATABASE pizzeria;

\c pizzeria;

CREATE TABLE customers (
    idCustomer int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    email varchar(255),
    phone varchar(255) NOT NULL
);

CREATE TABLE pizzas (
    idPizza int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    price float NOT NULL
);

CREATE TABLE orders(
    idOrder int NOT NULL PRIMARY KEY,
    orderDate date NOT NULL,
    idCustomer int NOT NULL,
    FOREIGN KEY (idCustomer) REFERENCES customers(idCustomer)
);

CREATE TABLE ordersdetails(
    quantity int NOT NULL,
    priceEach float NOT NULL,
    idPizza int NOT NULL,
    idOrder int NOT NULL,
    FOREIGN KEY (idPizza) REFERENCES pizzas(idPizza),
    FOREIGN KEY (idOrder) REFERENCES orders(idOrder),
    Constraint PK_Order_Detail Primary Key (idOrder, idPizza)
);

CREATE TABLE ingredients (
    idIngredient int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE contains(
    idIngredient int NOT NULL,
    idPizza int NOT NULL,
    FOREIGN KEY (idIngredient) REFERENCES ingredients(idIngredient),
    FOREIGN KEY (idPizza) REFERENCES pizzas(idPizza),
    Constraint PK_Uso Primary Key (idIngredient, idPizza)
);
