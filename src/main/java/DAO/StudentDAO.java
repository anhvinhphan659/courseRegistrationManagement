package DAO;
import HibernateUtil.hibernateUtil;
import POJO.CrmuserEntity;
import  POJO.StudentEntity;

import HibernateUtil.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAO
{
    private static Session session;
    private static Transaction tx;
    public StudentDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public StudentEntity getObject(String ID)
    {
        StudentEntity ret;
        tx=session.beginTransaction();
        ret=session.get(StudentEntity.class,ID);
        return ret;
    }

    public void saveObject(StudentEntity student)
    {
        if(student!=null) {
            tx = session.beginTransaction();

            session.save(student);
            tx.commit();
        }
    }

    public void removeObject(StudentEntity student)
    {
        if(student!=null) {
            tx = session.beginTransaction();
            session.delete(student);
            tx.commit();
        }
    }

    public void updateID(String oldID,String newID)
    {
        StudentEntity student= getObject(oldID);
        if(student!=null)
        {
            removeObject(student);
            student.setUserid(newID);
            Transaction tx=session.beginTransaction();
            session.save(student);
            tx.commit();

        }
    }
}
