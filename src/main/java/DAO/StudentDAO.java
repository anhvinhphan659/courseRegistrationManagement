package DAO;
import HibernateUtil.hibernateUtil;
import POJO.StudentEntity;
import POJO.CrmuserEntity;
import  POJO.StudentEntity;

import HibernateUtil.hibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO
{
    private static Session session;
    private static Transaction tx;
    public StudentDAO()
    {
        hibernateUtil hb=new hibernateUtil();

            session=hb.getSessionfactory().openSession();




        System.out.println("Initial Transaction at Intialize");

            tx = session.beginTransaction();



    }

    public StudentEntity getObject(String ID)
    {
        StudentEntity ret;
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        ret=session.get(StudentEntity.class,ID);
        return ret;
    }

    public void saveObject(StudentEntity student)
    {
        if(student!=null) {

            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();
                System.out.println("Initial Transaction at save");
            }

            session.save(student);
            tx.commit();
        }
    }

    public void updateObject(StudentEntity student)
    {
        if(student!=null) {

            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();
                System.out.println("Initial Transaction at save");
            }

            session.update(student);
            tx.commit();
        }
    }

    public void removeObject(StudentEntity student)
    {
        if(student!=null) {
            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();
                System.out.println("Initial Transaction at save");
            }
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

    public static Object[][] convertToObject(List<StudentEntity> data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                StudentEntity temp=data.get(i);
                Object[] add={temp.getStudentid(),temp.getStudentname(),
                temp.getGender(),temp.getClassid(),temp.getUserid()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }
}
