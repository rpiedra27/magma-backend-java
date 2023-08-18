CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    price INTEGER NOT NULL,
    image_url TEXT
);

CREATE TABLE IF NOT EXISTS ingredients (
    id SERIAL PRIMARY KEY,
    label TEXT NOT NULL,
    price INTEGER NOT NULL,
    value TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    cost INTEGER NOT NULL,
    timestamp TIMESTAMPTZ DEFAULT NOW()
);

INSERT INTO users(username, email, password)
  VALUES ('rp', 'rpiedra_27@outlook.com', 'pass');

INSERT INTO items(name, description, price, image_url)
  VALUES ('Pizza de jamón', 'Salsa: Tomate Queso: Mozarella Ingredientes: Jamón', 5000, 'https://');

INSERT INTO items(name, description, price, image_url)
  VALUES ('Pizza de pepperoni', 'Salsa: Tomate Queso: Gouda Ingredientes: Pepperoni', 5500, 'https://');

INSERT INTO items(name, description, price, image_url)
  VALUES ('Pizza de texana', 'Salsa: BBQ Queso: Gouda Ingredientes: Jamón y cebolla', 6000, 'https://');

INSERT INTO items(name, description, price, image_url)
  VALUES ('Pizza de suprema', 'Salsa: Tomate Queso: Mozzarella Ingredientes: Pepperoni, jamón, chile dulce, hongos, carne molida y cebolla', 7000, 'https://');

INSERT INTO items(name, description, price, image_url)
  VALUES ('Pizza de piña', 'Salsa: Tomate Queso: Parmesano Ingredientes: Piña y jamón', 5500, 'https://');
