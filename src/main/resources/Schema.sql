CREATE TABLE client(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(75) NOT NULL,
    lastname VARCHAR(75) NOT NULL,
    docnumber VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE product(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150),
    description VARCHAR(150),
    code VARCHAR(50) UNIQUE NOT NULL,
    stock INTEGER,
    price DOUBLE
);

CREATE TABLE invoice(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER,
    FOREIGN KEY (client_id) REFERENCES client(id),
    total DOUBLE,
    invoice_date TIMESTAMP
);

CREATE TABLE invoice_detail(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    invoice_id INTEGER,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    product_id INTEGER,
    FOREIGN KEY (product_id) REFERENCES product(id),
    quantity INTEGER,
    price DOUBLE
);