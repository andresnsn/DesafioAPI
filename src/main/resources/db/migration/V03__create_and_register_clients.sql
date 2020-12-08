CREATE TABLE CLIENTE(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    documento VARCHAR(20) NOT NULL,
    data_cadastro DATE NOT NULL
);

INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('André Bezerra Ribeiro', 'andre.sn@live.com', 'gardenofwords', '38-702-318-5', '2020-11-30');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Urashima Keitaro', 'urashima.keitaro@lovehina.com', 'lovehina456', '21-596-112-7', '2020-11-15');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Emiya Shirou', 'emiya.shirou@fate.com', 'fate123', '35-916-549-8', '2020-11-17');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Uzumaki Naruto', 'uzumaki.naruto@naruto.com', 'naruto123', '41-479-349-9', '2020-11-11');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Ouma Shu', 'ouma.shu@guiltycrown.com', 'guiltycrown123', '26-216-227-2', '2020-11-5');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Ehren Jaegar', 'ehren.jaegar@shingekinokyojin.com', 'shingekinokyojin123', '45-224-695-6', '2020-11-14');
INSERT INTO CLIENTE(nome, email, senha, documento, data_cadastro) values('Saint Seiya', 'saint.seiya@saintseiya.com', 'saintseiya123', '31-925-456-7', '2020-11-10');