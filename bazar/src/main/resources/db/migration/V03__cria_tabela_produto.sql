CREATE TABLE produto(
	codigo int(5) primary key auto_increment,
	nome varchar(50),
	preco double
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto (nome, preco) 
VALUES('caderno', 30), 
	  ('folha A4', 40), 
	  ('Fichario pequeno', 25.90);