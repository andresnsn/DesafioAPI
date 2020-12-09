CREATE TABLE PRODUTO(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    codigo_produto VARCHAR(20),
    valor DECIMAL(10, 2) NOT NULL,
    promocao BOOLEAN NOT NULL,
    valor_promo DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    imagem VARCHAR(50) NOT NULL,
    quantidade BIGINT(10) NOT NULL,
    codigo_fornecedor BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(id) ON DELETE CASCADE
);

INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Samsung Galaxy S20', '1234561', 2999.00, 1, 2849.05, 'Smartphone', 'galaxys20.png', 7, 1);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Samsung Galaxy A31', '1234562', 1359.00, 1, 1291.05, 'Smartphone', 'galaxya31.png', 15, 2);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Xbox Series X', '1234563', 4599.00, 0, 4369.05, 'Console', 'xboxseriesx.jpg', 30, 3);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Playstation 5', '1234564', 4699.00, 0, 4464.05, 'Console', 'ps5.png', 30, 4);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Mi Band 5', '1234565', 249.90, 1, 237.40, 'Smartband', 'miband5.jpg', 52, 5);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('GTX 1660', '1234566', 2117.53, 1, 1799.90, 'GPU', 'gtx1660.png', 15, 6);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Redragon Mitra RGB', '1234567', 320.90, 1, 304.85, 'Teclado', 'redmitrargb.png', 25, 7);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Samsung Galaxy S10', '7654321', 2300.90, 0, 2100.05, 'Smartphone', 'galaxys10.png', 7, 1);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Samsung Galaxy A51', '7654322', 1498.50, 1, 1240.10, 'Smartphone', 'galaxya51.png', 15, 2);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Xbox One X', '7654323', 3499.00, 0, 3199.99, 'Console', 'xboxonex.jpg', 30, 3);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Playstation 4 Pro', '7654324', 3599.00, 0, 3299.90, 'Console', 'ps4pro.png', 30, 4);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Mi Band 4', '7654325', 149.90, 1, 140.10, 'Smartband', 'miband4.jpg', 52, 5);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('GTX 1050 TI', '7654326', 999.90, 1, 899.90, 'GPU', 'gtx1050ti.png', 15, 6);
INSERT INTO PRODUTO(nome, codigo_produto, valor, promocao, valor_promo, categoria, imagem, quantidade, codigo_fornecedor) values('Redragon Mitra Single Color', '7654327', 299.90, 1, 249.90, 'Teclado', 'redmitrasinglecolor.png', 25, 7);

