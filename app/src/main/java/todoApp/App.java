
package todoApp;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

public class App {

    public static void main(String[] args) {
        /*
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Teste");
        project.setDescription("testando teste");
        project.setUpdatedAt(new Date());
        //projectController.save(project);
        
        
        Project project2 = new Project();
        project2.setName("Teste ediçaooaoaoao");
        project2.setDescription("Teste edicaoaodafwo");
        project2.setId(2);
        projectController.update(project2);
        
        //projectController.removeById(1);
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos = " + projects.size());
        for(int i=0;i<projects.size();i++){
            System.out.println(projects.get(i));
        }
        */
        
        TaskController taskController = new TaskController();
        /*
        Task task = new Task();
        task.setIdProject(2);
        task.setName("Teste Task2");
        task.setDescription("Descricao teste2");
        task.setNotes("Notas teste2");
        task.setIsCompleted(false);
        task.setDeadline(new Date());
        taskController.save(task);
        
        Task task = new Task();
        task.setId(2);
        task.setIdProject(2);
        task.setName("Teste task2 alterado");
        task.setDescription("Descricao teste 2 alterado");
        task.setIsCompleted(true);
        task.setDeadline(new Date());
        taskController.update(task);
        */
        /*
        List<Task> tasks = taskController.getAllNoId();
        System.out.println("Total de tarefas = " + tasks.size());
        for(int i=0;i<tasks.size();i++){
            S
        */
        List<Task> tasks2 = taskController.getAll(2);
        System.out.println("Total de tarefas = " + tasks2.size() + ", Projeto id = 2" );
        for(int i=0;i<tasks2.size();i++){
            System.out.println(tasks2.get(i));
        }
    }
}
