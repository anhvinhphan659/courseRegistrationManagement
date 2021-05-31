package DAO;


import POJO.CrmuserEntity;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import HibernateUtil.hibernateUtil;

import java.util.ArrayList;
import java.util.List;


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

    public List<CrmuserEntity> getListObject()
    {
        String hql="FROM CrmuserEntity";

        Query data= session.createQuery(hql);
        List<CrmuserEntity> ret=data.list();
        return ret;
    }

    public static Object[][] convertToObject(List<CrmuserEntity>data)
    {
        if(data!=null) {
            int size = data.size();
            List<Object[]> ret=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                CrmuserEntity temp=data.get(i);
                Object[] add={temp.getUserid(),temp.getAccount(),temp.getPass(),temp.getIsadmin()};
                ret.add(add);
            }
            return ret.toArray(new Object[0][]);

        }
        return null;
    }





}
