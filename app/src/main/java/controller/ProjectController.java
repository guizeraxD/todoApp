
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project){
        
        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt)" +
                "VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao inserir projeto " + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Project project){
        
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
        }catch(Exception e){
            throw new RuntimeException("Erro ao atualizar projeto", e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM projects";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Project> projects = new ArrayList<Project>();
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setCreatedAt(rs.getDate("createdAt"));
                project.setUpdatedAt(rs.getDate("updatedAt"));
                
                projects.add(project);
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao retornar todos os projetos, ", e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement, rs);
        }
        return projects;
    }
    
    public void removeById(int id){
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao deletar projeto, ", e);
        }finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
}
