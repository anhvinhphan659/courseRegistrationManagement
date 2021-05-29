package DAO;


import POJO.CrmuserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import HibernateUtil.hibernateUtil;

public class CRMuserDAO
{
    private static Session session;
    private static Transaction tx;
    public CRMuserDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();

    }

    public void saveObject(CrmuserEntity crmuser)
    {
        Transaction tx=session.beginTransaction();
        session.save(crmuser);
        tx.commit();

    }

    public CrmuserEntity getObject(String userID)
    {
        CrmuserEntity ret;
        tx=session.beginTransaction();
        ret=session.get(CrmuserEntity.class,userID);
        return ret;
    }

    public void removeObject(CrmuserEntity crmuser)
    {
        Transaction tx=session.beginTransaction();
        session.delete(crmuser);
        tx.commit();
    }

    public void updateID(String oldID,String newID)
    {
        CrmuserEntity crmuser= getObject(oldID);
        if(crmuser!=null)
        {
            removeObject(crmuser);
            crmuser.setUserid(newID);
            Transaction tx=session.beginTransaction();
            session.save(crmuser);
            tx.commit();

        }
    }





}
