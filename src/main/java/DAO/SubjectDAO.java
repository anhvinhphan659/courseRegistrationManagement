package DAO;

import HibernateUtil.hibernateUtil;

import POJO.CrmuserEntity;
import POJO.SubjectEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO
{
    private static Session session;
    private static Transaction tx;

    public SubjectDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }

    public SubjectEntity getObject(String ID)
    {
        SubjectEntity ret;
        if(tx==null)
            tx = session.beginTransaction();
        ret=session.get(SubjectEntity.class,ID);
        return ret;
    }

    public void saveObject(SubjectEntity subject)
    {
        if(subject!=null) {
            if(tx==null)
                tx = session.beginTransaction();

            session.save(subject);
            tx.commit();
        }
    }
    public void removeObject(SubjectEntity subject)
    {
        if(subject!=null) {
            if(tx==null)
                tx = session.beginTransaction();
            session.delete(subject);
            tx.commit();
        }
    }

    public void updateID(String oldID,String newID)
    {
        SubjectEntity subject= getObject(oldID);
        if(subject!=null)
        {
            removeObject(subject);
            subject.setSubjectid(newID);
            if(tx==null)
                tx = session.beginTransaction();
            session.save(subject);
            tx.commit();

        }
    }
    public List<SubjectEntity> getListObjects()
    {
        String hql="FROM SubjectEntity";

        Query data= session.createQuery(hql);
        List<SubjectEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
        return ret;
    }

    public static Object[][] convertToObject(List<SubjectEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                SubjectEntity temp=data.get(i);
                Object[] add={temp.getSubjectid(),temp.getSubjectname(),temp.getCredit(),temp.getFalculty()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }
}
