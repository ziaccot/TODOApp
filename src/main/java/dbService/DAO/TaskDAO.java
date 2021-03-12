package dbService.DAO;

import dbService.DAO.Entity.Task;
import org.hibernate.SessionFactory;

import java.util.List;

/*
*   Added an interface, because it`s righter to do so.
 */
public interface TaskDAO {
    void addTask(Task task);
    void deleteTask(Task task);
    List<Task> getAll();
}
