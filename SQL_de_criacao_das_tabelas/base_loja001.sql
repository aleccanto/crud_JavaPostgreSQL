
CREATE TABLE cliente (
	id_cliente SERIAL PRIMARY KEY,
	nome character varying(30),
	sobrenome character varying(30),
	nome_usuario character varying(30),
	email character varying(30),
	cpf character varying(15),
	data_nascimento date
);

CREATE TABLE endereco (
	id_endereco serial PRIMARY KEY,
	rua VARCHAR (50) NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	cep VARCHAR (9) NOT NULL,  
	cidade VARCHAR(50) NOT NULL,
	estado VARCHAR(50) NOT NULL,
	id_cliente int
);

CREATE TABLE categoria (
	id_categoria  SERIAL PRIMARY KEY,
	nome text,
	descricao text
);

CREATE TABLE produto (
	id_produto SERIAL PRIMARY KEY,
	nome VARCHAR(30),
	descrição VARCHAR(100),
	data_fabricação DATE,
	valor_unidade FLOAT,
	id_categoria INT REFERENCES categoria(id_categoria)
);

CREATE TABLE funcionario (
	id_funcionario SERIAL  PRIMARY KEY,
	nome VARCHAR,
	CPF CHAR(11) UNIQUE
);

CREATE TABLE estoque (
	id_estoque SERIAL PRIMARY KEY,
	id_funcionario INT REFERENCES funcionario (id_funcionario),
	id_produto INT REFERENCES produto (id_produto),
	qtd_produto INTEGER
);

CREATE TABLE pedido(
	id_pedido SERIAL,
	data_pedido DATE,
	id_cliente INT REFERENCES cliente(id_cliente),
	id_produto INT REFERENCES produto(id_produto),
	qtd_produto INT
);

--Comandos de inserção:

INSERT INTO funcionario
	(nome, cpf)
VALUES
	('Bruno Oliver Aragão', '45285450713'),
	('Gustavo Guilherme', '78695844790'),
	('Luzia Isabelly Araújo', '60063571781'),
	('Filipe Renato Severino da Mata', '30812103726'),
	('Manuel Mateus Barros', '26425746793');

INSERT INTO endereco
	(rua, bairro, cep, cidade, estado, id_cliente)
VALUES
	('Servidão Geraldo Pinto Ribeiro','Santa Cecília','25957175','Teresópolis','RJ','6'),
	('Rua Graciliano Ramos','São Pedro','25956130', 'Teresópolis', 'RJ','2'),
	('Estrada dos Colibris','Parque do Imbui','25970400','Teresópolis','RJ','3'),
	('Servidão Lux','São Pedro','25956220','Teresópolis', 'RJ','4'),
	('Rua Lúcio de Mendonça','Golfe','25974020','Teresópolis','RJ','5');


INSERT INTO categoria	
    (nome, descricao)
VALUES
	('Ciclismo','Artigos para ciclismo, desde bicicletas,
	acessórios para bicicletas, até roupas e equipamentos para o ciclista.'),
	('Air Soft', 'Artigos para air soft, desde armas elétricas
	e de pressão até equipamentos para armas e equipamentos de
	proteção para praticantes.'),
('Mergulho', 'Artigos para mergulhadores, contém equipamentos
	de mergulho e natação.'),
	('Artes marciais', 'Artigos para praticantes de artes marciais,
	contém equipamentos de proteção e equipamentos para treino.'),
	('Skate', 'Artigos para skatistas, contém peças, equipamentos
	de proteção e skates montados.');

INSERT INTO produto
	(nome, descrição, data_fabricação, valor_unidade, id_categoria)
VALUES
	 ('Bicicleta Dropp Z3 Aro 29', '  ótima opção para quem busca
	 leveza, conforto, segurança e estilo .','2021-02-05', '1749', '1'),

	 ('Óculos para Airsoft', 'possui fita elástica para 
	 ajuste rápido. Moldura com espuma interna para seu maior conforto.',
	 '2019-06-20','79', '2'),

	 ('Nadadeira  Super ', '  desenvolvido para aumentar a força e a potência
	  do movimento', '2020-01-07', '289', '3'),

	 ('Luva de Boxe Champion', 'representa e homenageia todos e todas que 
	  estão incessantemente na luta pela vitória.', '2021-01-03', '320', '4'),

	 ('Skate Iniciante ',' entende as necessidades dos 
	  skatistas', '2019-02-08', '280', '5')


INSERT INTO cliente
		(NOME, SOBRENOME, 
		NOME_USUARIO, EMAIL, 
		CPF, DATA_NASCIMENTO)
VALUES
	('Cauã', 'Gomes',	'cauaKaue',	'cauakaue@hotmail.com',	'73078657779',	'2000-08-01'),
	('Elisa', 'Elza', 'ElisaBrabinha',	'Elisaaee@hotmail.com',	'92774509750',	'1990-02-09'),
	('Rita' ,'Francisca' ,	'ritafrancisca',	'frascisquinha@hotmail.com',	'42122650796',	'1992-02-09'),	
	('Sabrina' ,'Viana',	'sabrininha',	'sabrinha@hotmail.com',	'95872579799',	'2001-02-09'),
	('Josefina', 'Franca', 'josefinafranca', 'josefinafranca@hotmail.com', '40107631083', '2001-03-23');


	INSERT INTO 
		pedido
	VALUES 
	(5, current_date, 5, 1);
	(1, '2021-04-18', 1, 1),
	(2, '2021-04-19', 2, 2),
	(3, current_date, 3, 3),
	(4, current_date, 4, 4),
	(5, current_date, 5, 5);
	
	UPDATE pedido
	SET qtd_produto = 5
	WHERE qtd_produto is NULL
	
--Comando DELETE:

DELETE FROM funcionario
WHERE id_funcionario = 5

--Comando UPDATE:
	
UPDATE pedido
SET id_produto = 13
WHERE id_produto = 14 AND id_cliente = 6

--Comandos usando INNER JOIN:

   --Para saber o endereço dos clientes.
SELECT cl.nome, ed.rua, ed.bairro, ed.cidade, ed.estado
FROM cliente cl
INNER JOIN endereco ed
ON cl.id_cliente = ed.id_cliente

	--Para descobrir a categoria dos produtos
SELECT pd.nome, pd.descrição, cd.nome, cd.descricao
FROM produto pd
INNER JOIN categoria cd
ON pd.id_categoria = cd.id_categoria

--Comandos usando COUNT
	
	--Comando para contar o número de clientes.
SELECT COUNT(*)
FROM cliente

--Comando usando GROUP BY:

	--Comando para contar os pedidos por data.
SELECT data_pedido, COUNT(*)
FROM PEDIDO
GROUP BY data_pedido

--Comando Nota Fiscal:

SELECT 
	cl.nome AS Nome_Cliente, 
	cl.cpf, pd.nome AS Nome_Produto,
	pd.valor_unidade,
	pe.qtd_produto AS quantidade_produto, 
	(pd.valor_unidade * pe.qtd_produto) AS total_produto, pe.data_pedido, pe.id_pedido
FROM 
	pedido pe
INNER JOIN 
	cliente cl
ON 
	cl.id_cliente = pe.id_cliente
INNER JOIN 
	produto pd
ON 
	pd.id_produto = pe.id_produto
ORDER BY 
	data_pedido, nome_cliente
