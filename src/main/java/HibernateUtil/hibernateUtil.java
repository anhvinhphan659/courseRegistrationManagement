package HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import POJO.*;

public class hibernateUtil
{
    private Session session;
    public  hibernateUtil()
    {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(StudentEntity.class);
            cfg.addAnnotatedClass(CourseopenEntity.class);
            cfg.addAnnotatedClass(CourseregistEntity.class);
            cfg.addAnnotatedClass(CrmclassEntity.class);
            cfg.addAnnotatedClass(SubjectEntity.class);
            cfg.addAnnotatedClass(SemesterEntity.class);
            cfg.addAnnotatedClass(CrmuserEntity.class);
            SessionFactory sf = cfg.buildSessionFactory();
            session = sf.openSession();
        }
        catch (Throwable ex)
        {
            System.err.println("Failed to create Session " +ex);
        }
    }
    public Session getSession()
    {
        return  session;
    }
    public void saveObject(Object obj)
    {
        if(session!=null) {
            Transaction tx;
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        }
    }
    public void updateObject(Object obj)
    {
        if(session!=null) {
            Transaction tx;
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }
    public void removeObject(Object obj)
    {
        if(session!=null) {
            Transaction tx;
            tx = session.beginTransaction();
            session.remove(obj);
            tx.commit();
        }
    }
}
