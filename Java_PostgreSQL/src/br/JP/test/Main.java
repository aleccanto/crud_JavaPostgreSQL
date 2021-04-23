package br.JP.test;

import br.JP.conexao.Conexao;

import br.JP.delete.ComandosDelete;
import br.JP.insert.ComandosInsert;
import br.JP.select.ComandosSelect;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        System.out.print("Digite o nome da base de dados: ");
        String db = in2.next();
        System.out.print("Digite a senha do banco de dados: ");
        String senha = in.next();
        Conexao c = new Conexao("postgres", "loja001",
                "localhost", senha);
        c.conectar();
        ComandosSelect cs = new ComandosSelect();
        boolean continua = true;

        int opc, opc4;
        String opc2, opc3;

        System.out.println("Bem-vindo ao Sistema");
        while (continua) {
            System.out.println("Escolha uma das Opções a baixo:\n");
            do {
                try {
                    System.out.print
                            ("""
                                    1 - Ver Funcionários
                                    2 - Ver Produtos
                                    3 - Ver Clientes
                                    4 - Ver Pedidos
                                    5 - Ver Categorias
                                    6 - Sair do Sistema
                                    >>\s""");

                    opc = in2.nextByte();
                } catch (Exception e) {
                    opc = 8;
                }
                switch (opc) {
                    case 1 -> {
                        cs.mostraFuncionarios(c);
                        System.out.print("Deseja fazer alguma alteração? SIM/NÃO\n>>> ");
                        opc3 = in.next().toUpperCase(Locale.ROOT);
                        if (opc3.equals("SIM")) {
                            while (opc3.equals("SIM")) {
                                System.out.print("Selecione uma opção:\n1 - Adicionar Funcionário\n" +
                                        "2 - Deletar Funcionário\n3 - Sair\n>>> ");
                                opc4 = in.nextInt();
                                switch (opc4) {
                                    case 1 -> {
                                        ComandosInsert ci = new ComandosInsert();
                                        ci.cadastraFuncionario(c);
                                    }
                                    case 2 -> {
                                        ComandosDelete cd = new ComandosDelete();
                                        System.out.print("Informe o id do funcionário para exclusão: ");
                                        int id = in.nextInt();
                                        cd.deletaFuncionario(id, c);
                                    }
                                    case 3 -> System.out.println("Saindo...");
                                }
                                System.out.println("Deseja fazer mais alguma alteração? SIM/NÃO ");
                                opc3 = in.next().toUpperCase(Locale.ROOT);


                            }
                        }
                    }
                    case 2 -> {
                        cs.mostraProdutos(c);
                        System.out.print("Deseja fazer alguma alteração? SIM/NÃO\n>>> ");
                        opc3 = in.next().toUpperCase(Locale.ROOT);
                        if (opc3.equals("SIM")) {
                            while (opc3.equals("SIM")) {
                                System.out.print("Selecione uma opção:\n1 - Adicionar Produto\n" +
                                        "2 - Deletar Produto\n3 - Sair\n>>> ");
                                opc4 = in.nextInt();
                                switch (opc4) {
                                    case 1 -> {
                                        ComandosInsert ci = new ComandosInsert();
                                        ci.cadastraProduto(c);
                                    }
                                    case 2 -> {
                                        ComandosDelete cd = new ComandosDelete();
                                        System.out.print("Informe o id do produto para exclusão: ");
                                        int id = in.nextInt();
                                        cd.deletaProduto(id, c);
                                    }
                                    case 3 -> System.out.println("Saindo...");
                                }
                                System.out.println("Deseja fazer mais alguma alteração? SIM/NÃO");
                                opc3 = in.next().toUpperCase(Locale.ROOT);
                            }
                        }
                    }
                    case 3 -> {
                        cs.mostraCliente(c);
                        System.out.print("Deseja fazer alguma alteração? SIM/NÃO\n>>> ");
                        opc3 = in.next().toUpperCase(Locale.ROOT);
                        if (opc3.equals("SIM")) {
                            while (opc3.equals("SIM")) {
                                System.out.print("Selecione uma opção:\n1 - Adicionar Cliente\n" +
                                        "2 - Deletar Cliente\n3 - Sair\n>>> ");
                                opc4 = in.nextInt();
                                switch (opc4) {
                                    case 1 -> {
                                        ComandosInsert ci = new ComandosInsert();
                                        ci.cadastroCliente(c);
                                    }
                                    case 2 -> {
                                        ComandosDelete cd = new ComandosDelete();
                                        System.out.print("Informe o id do produto para exclusão: ");
                                        int id = in.nextInt();
                                        cd.deletaCliente(id, c);
                                    }
                                    case 3 -> System.out.println("Saindo...");
                                }
                                System.out.println("Deseja fazer mais alguma alteração? SIM/NÃO");
                                opc3 = in.next().toUpperCase(Locale.ROOT);
                            }
                        }
                    }
                    case 4 -> {
                        cs.mostraPedido(c);
                        System.out.print("Deseja fazer alguma alteração? SIM/NÃO\n>>> ");
                        opc3 = in.next().toUpperCase(Locale.ROOT);
                        if (opc3.equals("SIM")) {
                            while (opc3.equals("SIM")) {
                                System.out.print("Selecione uma opção:\n1 - Adicionar Pedido\n" +
                                        "2 - Deletar Pedido\n3 - Sair\n>>> ");
                                opc4 = in.nextInt();
                                switch (opc4) {
                                    case 1 -> {
                                        ComandosInsert ci = new ComandosInsert();
                                        ci.cadastraPedido(c);
                                    }
                                    case 2 -> {
                                        ComandosDelete cd = new ComandosDelete();
                                        System.out.print("Informe o id do Pedido para exclusão: ");
                                        int id = in.nextInt();
                                        cd.deletaPedido(id, c);
                                    }
                                    case 3 -> System.out.println("Saindo...");
                                }
                                System.out.println("Deseja fazer mais alguma alteração? SIM/NÃO");
                                opc3 = in.next().toUpperCase(Locale.ROOT);
                            }
                        }
                    }
                    case 5 -> {
                        cs.mostraCategoria(c);
                        System.out.print("Deseja fazer alguma alteração? SIM/NÃO\n>>> ");
                        opc3 = in.next().toUpperCase(Locale.ROOT);
                        if (opc3.equals("SIM")) {
                            while (opc3.equals("SIM")) {
                                System.out.print("Selecione uma opção:\n1 - Adicionar uma Categoria\n" +
                                        "2 - Deletar uma Categoria\n3 - Sair\n>>> ");
                                opc4 = in.nextInt();
                                switch (opc4) {
                                    case 1 -> {
                                        ComandosInsert ci = new ComandosInsert();
                                        ci.cadastroCategoria(c);
                                    }
                                    case 2 -> {
                                        ComandosDelete cd = new ComandosDelete();
                                        System.out.print("Informe o id da categoria para exclusão: ");
                                        int id = in.nextInt();
                                        cd.deletaCategoria(id, c);
                                    }
                                    case 3 -> System.out.println("Saindo...");
                                }
                                System.out.println("Deseja fazer mais alguma alteração? SIM/NÃO");
                                opc3 = in.next().toUpperCase(Locale.ROOT);
                            }
                        }
                    }
                    case 6 -> {
                        c.desconectar();
                        return;
                    }
                    default -> System.out.println("Opção invalida!");
                }
                System.out.print("Deseja realizar outra consulta? Sim/Não\n>>> ");
                opc2 = in.next();
                opc2 = opc2.toUpperCase(Locale.ROOT);

                if (!(("S").equals(opc2) || ("SIM").equals(opc2))) {
                    continua = false;
                }
            } while (opc2.equals("SIM") || opc2.equals("S"));
        }
        c.desconectar();
    }
}
