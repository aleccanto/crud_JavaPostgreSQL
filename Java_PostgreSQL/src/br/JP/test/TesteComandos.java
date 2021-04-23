package br.JP.test;

import br.JP.conexao.Conexao;
import br.JP.delete.ComandosDelete;
import br.JP.insert.ComandosInsert;

import java.util.Scanner;

public class TesteComandos {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //ComandosDelete cd = new ComandosDelete();
        ComandosInsert ci = new ComandosInsert();
        System.out.print("Digite a senha do banco de dados: ");
        String senha = in.next();

        Conexao c = new Conexao("postgres", "loja001",
                "localhost", senha);
        c.conectar();

        //cd.deletaFuncionario(5, c);
        //ci.cadastraFuncionario(c);
        //ci.cadastroCliente(c);
        //c.desconectar();
        //c.sqlDML("DELETE FROM cliente WHERE id_cliente = (SELECT MAX(id_cliente) FROM cliente)");
        ci.cadastraPedido(c);
        c.desconectar();
    }
}
