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
    private static Session session;
    private static Transaction tx;

    public CRMclassDAO()
    {
        hibernateUtil hb=new hibernateUtil();
        if(session==null)
            session=hb.getSessionfactory().openSession();
        else
            session=hb.getSessionfactory().getCurrentSession();



        System.out.println("Initial Transaction at Intialize");
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(session.toString());
        System.out.println(tx.toString());

    }


    public void saveObject(CrmclassEntity crmclass)
    {

        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(session.toString());
        System.out.println(tx.toString());
        session.save(crmclass);
        tx.commit();

    }

    public CrmclassEntity getObject(String id)
    {
        CrmclassEntity ret;

        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(session.toString());
        System.out.println(tx.toString());
        ret=session.get(CrmclassEntity.class,id);
        tx.commit();
        return ret;
    }

    public void updateObject(CrmclassEntity crmclassEntity)
    {

        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        System.out.println(session.toString());
        System.out.println(tx.toString());
            System.out.println("Initial Transaction at save");

        session.update(crmclassEntity);
        tx.commit();
    }

    public List<CrmclassEntity> getObjects(int male)
    {
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
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
            if(tx==null)
                tx = session.beginTransaction();
            session.save(crmclass);
            tx.commit();

        }
    }

    public void updateMale(CrmclassEntity obj,int newMale)
    {
        if(obj!=null)
        {
            obj.setMale(newMale);
            if(tx==null)
                tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void updateFeMale(CrmclassEntity obj,int newFeMale)
    {
        if(obj!=null)
        {
            obj.setFemale(newFeMale);
            if(tx==null)
                tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }
    public void updateYearStart(CrmclassEntity obj,int newYearStart)
    {
        if(obj!=null)
        {
            obj.setYearstart(newYearStart);
            if(tx==null)
                tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void updateYearend(CrmclassEntity obj,int newYearEnd)
    {
        if(obj!=null)
        {
            obj.setYearend(newYearEnd);
            if(tx==null)
                tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
    }

    public void removeObject(CrmclassEntity delObj)
    {
        if(tx==null||tx.isActive()==false)
        {
            tx = session.beginTransaction();
            System.out.println("Initial Transaction at save");
        }
        session.delete(delObj);
        tx.commit();
    }
    public List<CrmclassEntity> getListObjects()
    {
        String hql="FROM CrmclassEntity";


        Query data= session.createQuery(hql);
        List<CrmclassEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
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
