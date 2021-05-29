package DAO;

import HibernateUtil.hibernateUtil;

import POJO.SubjectEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        tx=session.beginTransaction();
        ret=session.get(SubjectEntity.class,ID);
        return ret;
    }

    public void saveObject(SubjectEntity subject)
    {
        if(subject!=null) {
            tx = session.beginTransaction();

            session.save(subject);
            tx.commit();
        }
    }
    public void removeObject(SubjectEntity subject)
    {
        if(subject!=null) {
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
            Transaction tx=session.beginTransaction();
            session.save(subject);
            tx.commit();

        }
    }
}
