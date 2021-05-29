package DAO;

import HibernateUtil.hibernateUtil;
import POJO.CourseregistEntity;
import POJO.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseregistDAO
{
    private static Session session;
    private static Transaction tx;

    public  CourseregistDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public CourseregistEntity getObject(String ID)
    {
        CourseregistEntity ret;
        tx=session.beginTransaction();
        ret=session.get(CourseregistEntity.class,ID);
        return ret;
    }

    public void saveObject(CourseregistEntity courseregist)
    {
        if(courseregist!=null) {
            tx = session.beginTransaction();

            session.save(courseregist);
            tx.commit();
        }
    }

    public void removeObject(CourseregistEntity courseregist)
    {
        if(courseregist!=null) {
            tx = session.beginTransaction();
            session.delete(courseregist);
            tx.commit();
        }
    }

    public void updateID(String oldID,String newID)
    {
        CourseregistEntity courseregist= getObject(oldID);
        if(courseregist!=null)
        {
            removeObject(courseregist);
            courseregist.setIdregister(newID);
            Transaction tx=session.beginTransaction();
            session.save(courseregist);
            tx.commit();

        }
    }
}
