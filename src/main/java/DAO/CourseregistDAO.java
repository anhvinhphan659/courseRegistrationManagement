package DAO;

import HibernateUtil.hibernateUtil;
import POJO.CourseregistEntity;
import POJO.CrmclassEntity;
import POJO.CrmuserEntity;
import POJO.StudentEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseregistDAO
{
    private static Session session;
    private static Transaction tx;

    public  CourseregistDAO()
    {
        hibernateUtil hb=new hibernateUtil();

        session=hb.getSessionfactory().openSession();
        tx=session.beginTransaction();
    }

    public CourseregistEntity getObject(String ID)
    {
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();

        }
       CourseregistEntity ret=session.get(CourseregistEntity.class,ID);
        return ret;
    }

    public void saveObject(CourseregistEntity courseregist)
    {
        if(courseregist!=null) {

            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();

            }
            session.save(courseregist);
            tx.commit();
        }
    }

    public void removeObject(CourseregistEntity courseregist)
    {
        if(courseregist!=null) {
            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();

            }
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
            if(tx==null)
                tx = session.beginTransaction();
            session.save(courseregist);
            tx.commit();

        }
    }

    public void saveOrUpdateObject(CourseregistEntity courseregist)
    {
        if(courseregist!=null) {
            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();

            }
            session.saveOrUpdate(courseregist);
            tx.commit();
        }
    }

    public List<CourseregistEntity> getListObject()
    {
        String hql="FROM CourseregistEntity";


        Query data= session.createQuery(hql);
        List<CourseregistEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
    }

    public List<CourseregistEntity> getListObject(String studentID)
    {
        String hql="FROM CourseregistEntity c WHERE c.idstudent=:id";

        Query data=session.createQuery(hql);
        data.setParameter("id",studentID);
        List<CourseregistEntity>ret=data.list();
        return ret;
    }

    public static Object[][] convertToObject(List<CourseregistEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                CourseregistEntity temp=data.get(i);
                Object[] add={temp.getIdstudent(),temp.getOpenid(),temp.getDateregist()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }




}
