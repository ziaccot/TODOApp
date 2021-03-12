package dbService.DAO.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "task_todo")
    private String task_todo;

    @Column(name = "task_isdone")
    private boolean isDone;

    public Task(){}

    public Task(String task_todo) {
        this.task_todo = task_todo;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_todo() {
        return task_todo;
    }

    public void setTask_todo(String task_todo) {
        this.task_todo = task_todo;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
