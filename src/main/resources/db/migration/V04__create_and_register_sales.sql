CREATE TABLE VENDA (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_fornecedor BIGINT(20) NOT NULL,
    codigo_cliente BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(id),
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(id),
    total_compra DECIMAL(10, 2),
    data_compra DATE NOT NULL
);

CREATE TABLE VENDA_PRODUTOS(
    venda_id BIGINT(20) NOT NULL,
    produto_id BIGINT(20) NOT NULL,
    FOREIGN KEY(venda_id) REFERENCES venda(id),
    FOREIGN KEY(produto_id) REFERENCES produto(id)
);

INSERT INTO VENDA(codigo_fornecedor, codigo_cliente, total_compra, data_compra) values(5, 5, 2000.50, "2020-10-15");

INSERT INTO VENDA_PRODUTOS(venda_id, produto_id) values(1, 4);

