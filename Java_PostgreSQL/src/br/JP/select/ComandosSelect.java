package br.JP.select;

import br.JP.conexao.Conexao;

import java.sql.ResultSet;

public class ComandosSelect {

    public void mostraFuncionarios(Conexao c) {
        ResultSet rs = c.excSql
                ("""
                 SELECT 
                    * 
                 FROM 
                    funcionario;
                 """);
        try {
            System.out.println("Código do Funcionário - Nome - CPF");
            while (rs.next()) {
                int codigoFuncionario = rs.getInt("id_funcionario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                System.out.println(codigoFuncionario + " - " + nome + " - " + cpf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    public void mostraCliente(Conexao c) {
        ResultSet rs = c.excSql
                ("""
                SELECT
                    * 
                FROM 
                    cliente;
                """);
        try {
            System.out.println("Código do Cliente - Nome - Sobrenome -  " +
                    "CPF - nickname - E-Mail - Data de Nascimento");
            while (rs.next()) {
                int codigoCliente = rs.getInt("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String sobrenome = rs.getString("sobrenome");
                String nickname = rs.getString("nome_usuario");
                String email = rs.getString("email");
                String dataNascimento = rs.getString("data_nascimento");
                System.out.println(codigoCliente + " - " + nome + " - "
                        + sobrenome + " - " + cpf + " - " + nickname + " - " +
                        email + " - " + dataNascimento);
            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostraProdutos(Conexao c) {
        ResultSet rs = c.excSql
                ("""
                SELECT
                    * 
                FROM 
                    produto;
                """);
        try {
            System.out.println("Código do Produto - Nome - Descrição " +
                    "- Data de Fabricação - Valor do Produto\n");
            while (rs.next()) {
                int codigoProduto = rs.getInt("id_produto");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descrição");
                String dataFabricacao = rs.getString("data_fabricação");
                String valorUnidade = rs.getString("valor_unidade");
                System.out.println(codigoProduto + " - " + nome + " - " + descricao
                        + " - " + dataFabricacao + " - " + valorUnidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    public void mostraCategoria(Conexao c) {
        ResultSet rs = c.excSql
                ("""
                SELECT 
                    * 
                FROM 
                    produto;
                """);
        try {
            System.out.println("Código da Categoria - Nome - Descrição");
            while (rs.next()) {

                int codigoProduto = rs.getInt("id_categoria");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descrição");
                System.out.println(codigoProduto + " - " + nome + " - " + descricao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    public void mostraPedido(Conexao c) {
        ResultSet rs = c.excSql
                ("""
                SELECT
                    cl.nome AS Nome_Cliente,
                    cl.cpf AS cpf, pd.nome AS Nome_Produto,
                    pd.valor_unidade AS valor_unidade,
                    pe.qtd_produto AS quantidade_produto,
                  pe.data_pedido AS dataP
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
                    data_pedido, nome_cliente"""
                );
        try {
            double total = 0;
            String nome = "";
            System.out.println("Nome do Cliente - CPF - Nome do Produto - Valor Unidade " +
                    "- Quantidade - Total por Produto - Data do Pedido:");
            while (rs.next()) {
                if (rs.getString("Nome_cliente").equals(nome)) {
                    String nomeProduto = rs.getString("Nome_Produto");
                    double valorProduto = rs.getDouble("valor_unidade");
                    int quantidade = rs.getInt("quantidade_produto");
                    double valortotalProduto = valorProduto * quantidade;
                    String dataPedido = rs.getString("dataP");
                    total += valortotalProduto;
                    System.out.println(nomeProduto + " - "
                            + valorProduto + " - " + quantidade + " - "
                            + valorProduto);
                    System.out.println("Total da Nota: R$" + total);
                } else {
                    total = 0;
                    nome = rs.getString("Nome_Cliente");
                    String cpf = rs.getString("cpf");
                    String nomeProduto = rs.getString("Nome_Produto");
                    double valorProduto = rs.getDouble("valor_unidade");
                    int quantidade = rs.getInt("quantidade_produto");
                    double valortotalProduto = valorProduto * quantidade;
                    String dataPedido = rs.getString("dataP");
                    total = valortotalProduto;
                    System.out.println(nome + " - "
                            + cpf + " - " + nomeProduto + " - " + valorProduto + " - "
                            + quantidade + " - " + valorProduto + " - " + dataPedido);
                    System.out.println("Total dos Produtos: R$" + total);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }
}
