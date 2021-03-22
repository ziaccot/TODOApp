package dbService.DAO;

import dbService.DAO.Entity.Task;
import dialog.ErrorDialog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class TaskDAOImpl implements TaskDAO{
    private static SessionFactory sessionFactory;

    @Override
    public void addTask(Task task) {
        sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(task);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            new ErrorDialog("SQL Error", ex.getMessage(), ex.getStackTrace().toString()).showAndWait();
        }
        session.close();
    }


    @Override
    public void deleteTask(Task task) {
        sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.delete(task);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            new ErrorDialog("SQL Error", ex.getMessage(), ex.getStackTrace().toString()).showAndWait();
        }
        session.close();
    }


    @Override
    public List<Task> getAll() {
        List<Task> tasks;

        sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Task", Task.class);
        tasks = query.getResultList();
        session.close();
        return tasks;
    }

    public static SessionFactory buildSessionFactory(){
        try {
            if (sessionFactory == null){
                StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                        .configure("cfg/hibernate.cfg.xml")
                        .build();
                Metadata metadata = new MetadataSources(standardServiceRegistry)
                        .getMetadataBuilder()
                        .build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        }catch (Throwable ex){
            new ErrorDialog("SQL Error", ex.getMessage(), ex.getStackTrace().toString()).showAndWait();
        }
        return null;
    }

    public static void shutdown(){
        sessionFactory.close();
    }
}
