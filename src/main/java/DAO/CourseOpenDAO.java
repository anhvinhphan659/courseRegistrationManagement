package DAO;

import HibernateUtil.hibernateUtil;
import POJO.CourseopenEntity;
import POJO.CrmuserEntity;
import POJO.StudentEntity;
import POJO.SubjectEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseOpenDAO
{
    private static Session session;
    private static Transaction tx;

    public CourseOpenDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        session=hb.getSessionfactory().openSession();


            System.out.println("Initial Transaction at Intialize");
            tx = session.beginTransaction();


    }

    public CourseopenEntity getObject(String ID)
    {
        CourseopenEntity ret;
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        ret=session.get(CourseopenEntity.class,ID);
        return ret;
    }

    public void saveObject(CourseopenEntity course)
    {
        if(course!=null) {
            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();
                System.out.println("Initial Transaction at save");
            }

            session.save(course);
            tx.commit();
        }
    }

    public void removeObject(CourseopenEntity courseopen)
    {
        if(courseopen!=null) {
            if(tx==null||tx.isActive()==false)
            {
                tx = session.beginTransaction();
                System.out.println("Initial Transaction at save");
            }
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

    public List<CourseopenEntity> getListObjects()
    {
        String hql="FROM CourseopenEntity ";


        Query data= session.createQuery(hql);
        List<CourseopenEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
    }

    public static Object[][] convertToObject(List<CourseopenEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                CourseopenEntity temp=data.get(i);
                Object[] add={temp.getSubjectid(),temp.getCourseclass(),
                        "("+Integer.valueOf(temp.getBeginshift())
                        + "-" + Integer.valueOf(temp.getEndshift())
                        +") "+ Integer.valueOf(temp.getDiw()),
                        temp.getTeacher(),temp.getMaxtotal()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }
}
