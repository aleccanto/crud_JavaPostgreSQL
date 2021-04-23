package br.JP.conexao;

import java.sql.*;

public class Conexao {
    private String user, db, host, pass, url;
    private Connection conn;

    public Conexao() {

    }

    public Conexao(String user, String db, String host, String pass) {
        this.user = user;
        this.db = db;
        this.host = host;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void conectar() {
        try {
            url = "jdbc:postgresql://" + this.host + "/" + this.db +
                    "?user=" + this.user + "&password=" + this.pass + "&ssl=false";
            conn = DriverManager.getConnection(url);

            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Conexão falou");
        }
    }

    public void desconectar() {
        try {
            System.out.println("Conexão fechada");
        } catch (Exception e) {
            System.out.println("Conexão não fechada");
        }
    }


    public int sqlDML(String sql) {
        try {
            Statement st = conn.createStatement();
            int res = st.executeUpdate(sql);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet excSql(String sql) {
        try {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            return rs;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
