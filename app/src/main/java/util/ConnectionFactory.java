package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
    
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/todoapp";
    public static final String user = "root";
    public static final String pass = "";
    
    public static Connection getConnection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            throw new RuntimeException("Erro na conexao com banco de dados", e);
        }
    }
    
    public static void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao fechar a conexao com o banco", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try{
            if(connection != null){
                connection.close();
            }
            
            if(statement != null){
                statement.close();
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao fechar a conexao com o banco", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs){
        try{
            if(connection != null){
                connection.close();
            }
            
            if(statement != null){
                statement.close();
            }
            
            if(rs != null){
                rs.close();
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao fechar a conexao com o banco", e);
        }
    }
}
