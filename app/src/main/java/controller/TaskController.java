
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save(Task task){
        
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
            
        }catch(Exception e){
            throw new RuntimeException("Erro ao inserir tarefa " + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
            
        }
    }
    
    public void update(Task task){
        
        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
               
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            //Estabelecendo conexao com banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando query
            statement = conn.prepareStatement(sql);
            
            //setando valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //executando query
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int taskId){
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao deletar tarefa" + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection(conn, statement);
            
        }
    }
    
    public List<Task> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        //tasks que serão retornadas
        List<Task> tasks = new ArrayList<Task>();
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            //setando valor de filtro da busca
            statement.setInt(1, idProject);
            
            //valor retornado ao executar a query
            rs = statement.executeQuery();
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setIdProject(rs.getInt("idProject"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setNotes(rs.getString("notes"));
                task.setIsCompleted(rs.getBoolean("completed"));
                task.setDeadline(rs.getDate("deadline"));
                task.setCreatedAt(rs.getDate("createdAt"));
                task.setUpdatedAt(rs.getDate("updatedAt"));
                
                tasks.add(task);
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao buscar tarefas " + e.getMessage(), e);
        }finally {
            ConnectionFactory.closeConnection(conn, statement, rs);
        }
            
        return tasks;
    }
    
    public List<Task> getAllNoId(){
        String sql = "SELECT * FROM tasks";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        //tasks que serão retornadas
        List<Task> tasks = new ArrayList<Task>();
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            //valor retornado ao executar a query
            rs = statement.executeQuery(sql);
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setIdProject(rs.getInt("idProject"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setNotes(rs.getString("notes"));
                task.setIsCompleted(rs.getBoolean("completed"));
                task.setDeadline(rs.getDate("deadline"));
                task.setCreatedAt(rs.getDate("createdAt"));
                task.setUpdatedAt(rs.getDate("updatedAt"));
                
                tasks.add(task);
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao buscar tarefas " + e.getMessage(), e);
        }finally {
            ConnectionFactory.closeConnection(conn, statement, rs);
        }
            
        return tasks;
    }
}
