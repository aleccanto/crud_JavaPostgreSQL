package br.JP.delete;
import br.JP.conexao.Conexao;

public class ComandosDelete {

    public void deletaFuncionario(int id, Conexao c){
        String sql =
                """
                DELETE FROM 
                    funcionario
                WHERE
                    id_funcionario = """ + id;

        if(c.sqlDML(sql) != 0){
            System.out.println("O funcionário foi removido com sucesso");
        }else{
            System.out.println("Houve um erro ao deletar o funcionário");
        }
    }
    public void deletaProduto(int id, Conexao c){
        String sql =
                """
                DELETE FROM 
                    produto
                WHERE
                    id_produto = """ + id;

        if(c.sqlDML(sql) != 0){
            System.out.println("O produto foi removido com sucesso");
        }else{
            System.out.println("Houve um erro ao deletar o produto");
        }
    }
    public void deletaCliente(int id, Conexao c){
        String sql =
                """
                DELETE FROM 
                    cliente
                WHERE
                    id_cliente = """ + id +
                """
                DELETE FROM
                    endereco
                WHERE
                    id_cliente = """ + id;

        if(c.sqlDML(sql) != 0){
            System.out.println("O cliente foi removido com sucesso");
        }else{
            System.out.println("Houve um erro ao deletar o cliente");
        }
    }
    public void deletaCategoria(int id, Conexao c){
        String sql =
                """
                DELETE FROM 
                    categoria
                WHERE
                    id_categoria = """ + id;

        if(c.sqlDML(sql) != 0){
            System.out.println("A categoria foi removido com sucesso");
        }else{
            System.out.println("Houve um erro ao deletar a categoria");
        }
    }
    public void deletaPedido(int id, Conexao c){
        String sql =
                """
                DELETE FROM 
                    pedido
                WHERE
                    id_pedido = """ + id;

        if(c.sqlDML(sql) != 0){
            System.out.println("O pedido foi removida com sucesso");
        }else{
            System.out.println("Houve um erro ao deletar o pedido");
        }
    }
}
