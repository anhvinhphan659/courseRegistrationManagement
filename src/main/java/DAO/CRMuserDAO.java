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
        if(session.getTransaction()==null)
            tx=session.beginTransaction();
    }

    public void saveObject(CrmuserEntity crmuser)
    {
        tx = session.beginTransaction();
        session.save(crmuser);
        tx.commit();

    }

    public CrmuserEntity getObject(String userID)
    {
        CrmuserEntity ret;
        if(tx==null)
            tx = session.beginTransaction();
        ret=session.get(CrmuserEntity.class,userID);
        return ret;
    }

    public void removeObject(CrmuserEntity crmuser)
    {
        if(tx==null)
            tx = session.beginTransaction();
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

    public List<CrmuserEntity> getListObjects()
    {
        String hql="FROM CrmuserEntity";
        if(tx==null)
            tx = session.beginTransaction();
        Query data= session.createQuery(hql);
        List<CrmuserEntity> ret=data.list();
        System.out.println("Get list objects successfully from "+ret.getClass().toString());
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

    public int checkAccount(String acc,String pas)
    {
        String hql= "FROM CrmuserEntity c WHERE c.account=: accoun";

        tx=session.beginTransaction();
        System.out.println(session.toString());
        System.out.println(tx.toString());
        Query query=session.createQuery(hql);
        query.setParameter("accoun",acc);
        List<CrmuserEntity>result=query.list();
        System.out.println(result);
        if(result==null||result.size()==0) {
            return -1;
        }
        CrmuserEntity data= (CrmuserEntity) query.list().get(0);
        if (data.getPass().compareTo(pas)==0)
        {
            if(data.getIsadmin()==true)
                return 2;
            else
                return 1;
        }
        return 0;
    }



}
