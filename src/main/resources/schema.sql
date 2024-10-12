CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    imagem_url VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(20)
); 
