package DAO;

import HibernateUtil.hibernateUtil;
import POJO.CrmclassEntity;
import POJO.CrmuserEntity;
import POJO.SemestersessionEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SemesterSessionDao
{
    private static Session session;
    private static Transaction tx;

    SemesterSessionDao()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public void saveObject(SemestersessionEntity semestersessionEntity)
    {
        session.save(semestersessionEntity);
        tx.commit();
    }

    public SemestersessionEntity getObject(String ID)
    {
        SemestersessionEntity ret;

        if(tx==null)
            tx = session.beginTransaction();
        ret=session.load(SemestersessionEntity.class,ID);
        tx.commit();
        return ret;
    }

    public void removeObject(SemestersessionEntity semestersessionEntity)
    {
        if(tx==null)
            tx = session.beginTransaction();
        session.delete(semestersessionEntity);
        tx.commit();
    }

    public List<SemestersessionEntity> getListObjects()
    {
        String hql="FROM SemestersessionEntity ";
        if(tx==null)
            tx = session.beginTransaction();
        Query data= session.createQuery(hql);
        List<SemestersessionEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
    }

    public static Object[][] convertToObject(List<SemestersessionEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                SemestersessionEntity temp=data.get(i);
                Object[] add={temp.getSemsesid(),temp.getIdsemester(),temp.getDatebegin(),temp.getDateend()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);
        }
        return null;
    }
}
