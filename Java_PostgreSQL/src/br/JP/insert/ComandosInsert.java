package br.JP.insert;

import br.JP.conexao.Conexao;
import com.sun.jdi.event.ExceptionEvent;

import java.sql.ResultSet;
import java.util.Locale;
import java.util.Scanner;

public class ComandosInsert {

    private final Scanner in = new Scanner(System.in);
    private final Scanner in2 = new Scanner(System.in);

    public void cadastraFuncionario(Conexao c) {
        String[] dados = new String[2];
        for (byte i = 0; i < dados.length; i++) {
            if (i == 0) {
                System.out.print("Informe o nome do Funcionário: ");
            } else {
                System.out.print("Informe o CPF do Funcionário: ");
            }
            dados[i] = in.next();
        }
        String sql =
                "INSERT INTO funcionario(nome, cpf) VALUES ('" + dados[0] + "', '" + dados[1] + "');";
        if (c.sqlDML(sql) != 0) {
            System.out.println("O funcionário " + dados[0] + ", foi cadastrado com sucesso!");
        } else {
            System.out.println("O funcionário " + dados[0] + ", não foi cadastrado com sucesso!");
        }
    }

    public void cadastroCategoria(Conexao c) {
        String[] dados = new String[2];
        for (byte i = 0; i < dados.length; i++) {
            if (i == 0) {
                System.out.print("Informe o nome da Categoria: ");
            } else {
                System.out.print("Informe a descrição da Categoria: ");
            }
            dados[i] = in.next();
        }
        String sql =
                "INSERT INTO categoria(nome, descricao) VALUES ('" + dados[0] + "', '" + dados[1] + "');";
        if (c.sqlDML(sql) != 0) {
            System.out.println("O funcionário " + dados[0] + ", foi cadastrado com sucesso!");
        } else {
            System.out.println("O funcionário " + dados[0] + ", nõa foi cadastrado com sucesso!");
        }
    }

    public void cadastroEndereco(Conexao c) {
        String[] dados = new String[5];
        String sql;
        int id = 0;
        for (byte i = 0; i < dados.length; i++) {
            switch (i) {
                case 0 -> System.out.print("Informe o nome da rua: ");
                case 1 -> System.out.print("Informe o bairro: ");
                case 2 -> System.out.print("Informe o CEP: ");
                case 3 -> System.out.print("Informe a cidade: ");
                case 4 -> System.out.print("Informe o estado: ");
            }
            if (i != 0) {
                dados[i] = in.next();
            } else {
                dados[i] = in2.next();
            }
        }

        try {
            ResultSet rs = c.excSql("SELECT MAX(id_cliente) FROM cliente");
            while (rs.next()) {
                id = rs.getInt("max");
            }
            System.out.println(id);
            sql = "INSERT INTO endereco(rua, bairro, cep, cidade, estado, id_cliente)" +
                    "VALUES ('" + dados[0] + "', '" + dados[1] + "', '" + dados[2] +
                    "', '" + dados[3] + "', '" + dados[4] + "', " + id + ")";
        } catch (Exception e) {
            System.out.println(id);//12345678912
            e.printStackTrace();
            System.out.println("Falha ao inserir o ID do cliente");
            sql = "DELETE FROM cliente WHERE id_cliente = (SELECT MAX(id_cliente) FROM cliente)";
        }

        if (c.sqlDML(sql) != 0 && !sql.equals("DELETE FROM cliente WHERE id_cliente = " +
                "(SELECT MAX(id_cliente) FROM cliente)")) {
            System.out.println("Endereço cadastrado com sucesso!");
        } else {
            System.out.println("Houve uma falha ao cadastrar o endereço!");

        }
    }

    public void cadastroCliente(Conexao c) {
        String[] dados = new String[6];
        for (byte i = 0; i < dados.length; i++) {
            switch (i) {
                case 0 -> System.out.print("Informe o nome: ");
                case 1 -> System.out.print("Informe o sobrenome: ");
                case 2 -> System.out.print("Informe o nome de usuário: ");
                case 3 -> System.out.print("Informe o email: ");
                case 4 -> System.out.print("Informe o CPF: ");
                case 5 -> System.out.print("Informe a data de nascimento: ex: 10/02/1995\n>>> ");
            }
            dados[i] = in.next();
        }
        String sql = "INSERT INTO cliente(nome, sobrenome, nome_usuario, email, cpf, data_nascimento)" +
                "VALUES ('" + dados[0] + "', '" + dados[1] + "', '" + dados[2] + "', '" + dados[3]
                + "', '" + dados[4] + "', '" + dados[5] + "')";

        if (c.sqlDML(sql) != 0) {
            System.out.println("Cliente cadastrado com sucesso!");
            cadastroEndereco(c);
        } else {
            System.out.println("Houve uma falha ao cadastrar o cliente!");
        }
    }

    public void cadastraPedido(Conexao c) {
        int[] dados = new int[3];
        String sql;
        for (int i = 0; i < dados.length; i++) {
            switch (i) {
                case 0 -> System.out.print("Informe o id do produto: ");
                case 1 -> System.out.print("Informe a quantidade do produto: ");
                case 2 -> System.out.print("Informe o id do cliente: ");
            }
            dados[i] = in.nextInt();
        }

        sql = "INSERT INTO pedido" +
                "(id_produto, qtd_produto, id_cliente, data_pedido) " +
                "VALUES(" + dados[0] + ", " + dados[1] + ", " + dados[2] + ", current_date)";

        if (c.sqlDML(sql) != 0) {
            String opc;
            System.out.print("Pedido realizado com sucesso. Deseja adicionar mais produtos a este pedido? SIM/NÃO\n>>> ");
            opc = in.next();
            opc = opc.toUpperCase(Locale.ROOT);
            if (("SIM").equals(opc)) {
                try {
                    boolean continuaPedido = true;
                    int[] dados2 = new int[2];
                    sql = "SELECT MAX(id_pedido) FROM pedido";
                    int id;
                    ResultSet rs = c.excSql(sql);
                    while (rs.next()) {
                        id = rs.getInt("max");
                    }
                    while (continuaPedido) {

                        for (int i = 0; i < dados2.length; i++) {
                            switch (i) {
                                case 0 -> System.out.print("Informe o id do produto: ");
                                case 1 -> System.out.print("Informe a quantidade do pedido: ");
                            }
                            dados2[i] = in.nextInt();
                        }
                        sql = "INSERT INTO pedido" +
                                "(id_produto, qtd_produto, id_cliente, data_pedido) " +
                                "VALUES(" + dados2[0] + ", " + dados2[1] + ", " + dados[2] + ", current_date)";
                        c.sqlDML(sql);
                        System.out.print("Deseja adicionar mais um produto?: ");
                        opc = in.next().toUpperCase(Locale.ROOT);
                        continuaPedido = "SIM".equals(opc);
                    }
                    System.out.println("Pedido Finalizado!");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Houve um ero ao adicionar mais produtos.");
                }
            } else {
                System.out.println("Pedido Finalizado!");
            }
        }
    }

    public void cadastraProduto(Conexao c) {
        String[] dados = new String[3];
        byte id_categoria;
        double valor; //2.45
        for (int i = 0; i < dados.length; i++) {
            switch (i) {
                case 0 -> System.out.print("Informe o nome do produto: ");
                case 1 -> System.out.print("Informe a descrição do produto: ");
                case 2 -> System.out.print("Informe a data de fabricação: ");
            }
            dados[i] = in.next();
        }

        System.out.print("Informe o valor do produto: ");
        valor = in.nextDouble();
        System.out.print("Informe o id da categoria: ");
        id_categoria = in.nextByte();

        String sql = "INSERT INTO produto(nome, descrição, data_fabricação, valor_unidade, id_categoria)" +
                "VALUES ('" + dados[0] + "', '" + dados[1] + "', '" + dados[2] + "', "
                + valor + ", " + id_categoria + "')";

        if (c.sqlDML(sql) != 0) {
            System.out.println("O produto " + dados[0] + " foi cadastrado com sucesso!");
        } else {
            System.out.println("Houve um erro ao cadastrar o produto.");
        }
    }
}
