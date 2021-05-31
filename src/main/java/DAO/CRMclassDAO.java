package DAO;

import java.util.ArrayList;
import java.util.List;
import POJO.CrmclassEntity;
import HibernateUtil.hibernateUtil;
import POJO.CrmuserEntity;
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
    public List<CrmclassEntity> getListObject()
    {
        String hql="FROM CrmclassEntity";

        Query data= session.createQuery(hql);
        List<CrmclassEntity> ret=data.list();
        return ret;
    }

    public static Object[][] convertToObject(List<CrmclassEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                CrmclassEntity temp=data.get(i);
                Object[] add={temp.getClassid(),temp.getMale(),temp.getFemale(),
                        temp.getFemale()+temp.getMale(),
                        Integer.valueOf(temp.getYearstart())+ "-"+Integer.valueOf(temp.getYearend()) };
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }

}
