package DAO;

import HibernateUtil.hibernateUtil;
import POJO.SemesterEntity;
import POJO.SemestersessionEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SemesterDAO
{
    private static Session session;
    private Transaction tx;

    public SemesterDAO()
    {
        hibernateUtil hb=new hibernateUtil();

            session=hb.getSessionfactory().openSession();




        System.out.println("Initial Transaction at Intialize");
            tx = session.beginTransaction();

    }

    public void saveObject(SemesterEntity semesterEntity)
    {
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(tx.toString());
        session.save(semesterEntity);
        tx.commit();
    }

    public SemesterEntity getObject(String ID)
    {
        SemesterEntity ret;

        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(tx.toString());
        ret=session.load(SemesterEntity.class,ID);
        tx.commit();
        return ret;
    }

    public void removeObject(SemesterEntity semesterEntity)
    {

        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }

        session.delete(semesterEntity);
        tx.commit();
    }



    public List<SemesterEntity> getListObjects()
    {
        String hql="FROM SemesterEntity ";

        Query data= session.createQuery(hql);
        List<SemesterEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
    }
    public void updateObject(SemesterEntity semesterEntity)
    {
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(tx.toString());
        session.update(semesterEntity);
        System.out.println("Update successs");
        tx.commit();
    }
    public static Object[][] convertToObject(List<SemesterEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                SemesterEntity temp=data.get(i);
                Object[] add={temp.getIdsemester(),temp.getNamesemester(),temp.getYearsemester(),
                temp.getDatebegin(),temp.getDateend(),temp.getIscurrentsemester()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);
        }
        return null;
    }

    public String getCurrentIDSemester()
    {
        String hql="FROM SemesterEntity s where s.iscurrentsemester =true";

        Query data=session.createQuery(hql);
        List<SemesterEntity>ret=data.list();
        if(ret!=null)
        {
            SemesterEntity obj= ret.get(0);
            return obj.getIdsemester();
        }
        return "None";
    }
}
