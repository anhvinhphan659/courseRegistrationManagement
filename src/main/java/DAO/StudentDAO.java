package DAO;
import HibernateUtil.hibernateUtil;
import POJO.CourseopenEntity;
import POJO.CrmuserEntity;
import  POJO.StudentEntity;

import HibernateUtil.hibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
        if(tx==null)
            tx = session.beginTransaction();
        ret=session.get(StudentEntity.class,ID);
        return ret;
    }

    public void saveObject(StudentEntity student)
    {
        if(student!=null) {
            if(tx==null)
                tx = session.beginTransaction();

            session.save(student);
            tx.commit();
        }
    }

    public void removeObject(StudentEntity student)
    {
        if(student!=null) {
            if(tx==null)
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
            student.setStudentid(newID);
            if(tx==null)
                tx = session.beginTransaction();
            session.save(student);
            tx.commit();

        }
    }
    public List<StudentEntity> getListObjects()
    {
        String hql="FROM StudentEntity ";


        Query data= session.createQuery(hql);
        List<StudentEntity> ret=data.list();
        return ret;
    }
}
