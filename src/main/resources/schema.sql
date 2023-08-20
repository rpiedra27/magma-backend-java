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
  VALUES 
    ('Pizza de jamón', 'Salsa: Tomate Queso: Mozarella Ingredientes: Jamón', 5000, 'https://'),
    ('Pizza de pepperoni', 'Salsa: Tomate Queso: Gouda Ingredientes: Pepperoni', 5500, 'https://'),
    ('Pizza de texana', 'Salsa: BBQ Queso: Gouda Ingredientes: Jamón y cebolla', 6000, 'https://'),
    ('Pizza de suprema', 'Salsa: Tomate Queso: Mozzarella Ingredientes: Pepperoni, jamón, chile dulce, hongos, carne molida y cebolla', 7000, 'https://'),
    ('Pizza de piña', 'Salsa: Tomate Queso: Parmesano Ingredientes: Piña y jamón', 5500, 'https://'),
    ('Pan de ajo', 'unidades: 5', 1200, 'https://'),
    ('Bread sticks con salsa de tomate', 'unidades: 10', 2000, 'https://'),
    ('Calzone', 'unidades: 1', 1500, 'https://'),
    ('Pan con tomate y queso', 'unidades: 9', 2500, 'https://'),
    ('Helado de vainilla', '3 bolas de helado que se puede pedir con sirope de chocolate o caramelo', 2100, 'https://'),
    ('Tres leches', 'Pedazo de Tres leches', 2900, 'https://'),
    ('Flan', 'Disfrutá del flan más exquisito', 3300, 'https://'),
    ('Coca cola', 'Categoria: Gaseosa\nMililitros: 600', 800, 'https://'),
    ('Jugo de naranja', 'Categoria: Jugo natural\nMililitros: 1000', 1100, 'https://'),
    ('Té frío de limón', 'Categoria: Té frío\nMililitros: 1000', 1300, 'https://');

INSERT INTO ingredients(label, price, value)
  VALUES
    ('Pequeña', 1000, 'small'),
    ('Mediana', 2000, 'medium'),
    ('Grande', 3000, 'large'),
    ('Monstruo', 4000, 'monster'),
    ('Tomate', 5000, 'tomato'),
    ('Picante', 200, 'spicy'),
    ('BBQ', 400, 'bbq'),
    ('Goda', 400, 'goda'),
    ('Parmesano', 300, 'parmesan'),
    ('Mozzarella', 400, 'mozzarella'),
    ('Jamón', 400, 'ham'),
    ('Tocineta', 100, 'bacon'),
    ('Carne molida', 250, 'ground_beef'),
    ('Pepperoni', 300, 'pepperoni'),
    ('Pollo', 200, 'chicken'),
    ('Salchicha', 250, 'sausage'),
    ('Cebolla', 50, 'onion'),
    ('Chile', 50, 'peppers'),
    ('Aceitunas', 100, 'olives'),
    ('Hongos', 100, 'mushrooms'),
    ('Rodajas de tomate', 50, 'tomato_slices'),
    ('Piña', 50000, 'pineapple'),
    ('Queso extra', 300, 'extra_cheese'),
    ('Salsa extra', 200, 'extra_sauce');

INSERT INTO orders(user_id, cost)
  VALUES
    (1, 13000),
    (1, 4000),
    (1, 63000);