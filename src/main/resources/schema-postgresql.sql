CREATE TABLE IF NOT EXISTS jogos(
    id serial PRIMARY KEY,
    nome VARCHAR(150),
    estado VARCHAR(10),
    preco DECIMAL(10, 2),
    console VARCHAR(20),
    imagem VARCHAR(200)
);

ALTER TABLE jogos
    DROP COLUMN IF EXISTS imagem;