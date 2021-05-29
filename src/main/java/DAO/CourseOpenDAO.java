package DAO;

import HibernateUtil.hibernateUtil;
import POJO.CourseopenEntity;
import POJO.StudentEntity;
import POJO.SubjectEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseOpenDAO
{
    private static Session session;
    private static Transaction tx;

    public CourseOpenDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public CourseopenEntity getObject(String ID)
    {
        CourseopenEntity ret;
        tx=session.beginTransaction();
        ret=session.get(CourseopenEntity.class,ID);
        return ret;
    }

    public void saveObject(CourseopenEntity course)
    {
        if(course!=null) {
            tx = session.beginTransaction();

            session.save(course);
            tx.commit();
        }
    }

    public void removeObject(CourseopenEntity courseopen)
    {
        if(courseopen!=null) {
            tx = session.beginTransaction();
            session.delete(courseopen);
            tx.commit();
        }
    }

    public void updateID(String oldID,String newID)
    {
        CourseopenEntity courseopen= getObject(oldID);
        if(courseopen!=null)
        {
            removeObject(courseopen);
            courseopen.setOpenid(newID);
            Transaction tx=session.beginTransaction();
            session.save(courseopen);
            tx.commit();

        }
    }
}
