package dbService;

import dbService.DAO.Entity.Task;
import dbService.DAO.TaskDAO;
import dbService.DAO.TaskDAOImpl;

import java.util.List;

public class DBService {
    private TaskDAO taskDAO;

    private TaskDAO getTaskDAO(){
        if(taskDAO == null)
            taskDAO = new TaskDAOImpl();
        return taskDAO;
    }

    public void addTask(Task task){
        getTaskDAO().addTask(task);
    }

    public void deleteTask(Task task){
        getTaskDAO().deleteTask(task);
    }

    public List<Task> getAllTask(){
        return getTaskDAO().getAll();
    }
}
