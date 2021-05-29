package HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import POJO.*;

public class hibernateUtil
{
    private static final SessionFactory sessionfactory;
    private static Session session;
    static {
        try {

            sessionfactory = new Configuration().configure().buildSessionFactory();

        }
        catch (Exception ex)
        {
            System.out.println("Initial Sesson factory error!");
            throw (ex);
        }
    }
    public SessionFactory getSessionfactory()
    {
        return sessionfactory;
    }
}
