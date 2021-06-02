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
    private static Transaction tx;

    SemesterDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public void saveObject(SemesterEntity semesterEntity)
    {
        session.save(semesterEntity);
        tx.commit();
    }

    public SemesterEntity getObject(String ID)
    {
        SemesterEntity ret;

        if(tx==null)
            tx = session.beginTransaction();
        ret=session.load(SemesterEntity.class,ID);
        tx.commit();
        return ret;
    }

    public void removeObject(SemesterEntity semesterEntity)
    {
        if(tx==null)
            tx = session.beginTransaction();
        session.delete(semesterEntity);
        tx.commit();
    }
    public List<SemesterEntity> getListObjects()
    {
        String hql="FROM SemesterEntity ";
        if(tx==null)
            tx = session.beginTransaction();
        Query data= session.createQuery(hql);
        List<SemesterEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
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
}
