package DAO;

import java.util.List;
import POJO.CrmclassEntity;
import HibernateUtil.hibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CRMclassDAO
{
    private Session session;
    private Transaction tx;

    public CRMclassDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        Session current=hb.getSessionfactory().getCurrentSession();
        if(current!=null)
            session=current;
        else
            session=hb.getSessionfactory().openSession();
    }


    public void saveObject(CrmclassEntity crmclass)
    {

        Transaction tx=session.beginTransaction();
        session.save(crmclass);
        tx.commit();

    }

    public CrmclassEntity getObject(String id)
    {
        CrmclassEntity ret;

        Transaction tx=session.beginTransaction();
        ret=session.load(CrmclassEntity.class,id);
        tx.commit();
        return ret;
    }

    public List<CrmclassEntity> getObjects(int male)
    {
        tx=session.beginTransaction();
        String query= "from CrmclassEntity c where c.male= " + String.valueOf(male);
        Query data=session.createQuery(query);
        List<CrmclassEntity> ret=data.list();
        return ret;

    }

    public void updateID(String oldID,String newID)
    {
        CrmclassEntity crmclass= getObject(oldID);
        if(crmclass!=null)
        {
            removeObject(crmclass);
            crmclass.setClassid(newID);
            Transaction tx=session.beginTransaction();
            session.save(crmclass);
            tx.commit();

        }
    }

    public void updateMale(CrmclassEntity obj,int newMale)
    {
        if(obj!=null)
        {
            obj.setMale(newMale);
            tx=session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void updateFeMale(CrmclassEntity obj,int newFeMale)
    {
        if(obj!=null)
        {
            obj.setFemale(newFeMale);
            tx=session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }
    public void updateYearStart(CrmclassEntity obj,int newYearStart)
    {
        if(obj!=null)
        {
            obj.setYearstart(newYearStart);
            tx=session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void updateYearend(CrmclassEntity obj,int newYearEnd)
    {
        if(obj!=null)
        {
            obj.setYearend(newYearEnd);
            tx=session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void removeObject(CrmclassEntity delObj)
    {
        tx= session.beginTransaction();
        session.delete(delObj);
        tx.commit();
    }

}
