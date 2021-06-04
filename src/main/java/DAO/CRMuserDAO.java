package DAO;


import POJO.CrmuserEntity;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import HibernateUtil.hibernateUtil;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class CRMuserDAO
{
    private SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
    private Session session=sessionFactory.openSession();
    public CRMuserDAO()
    {


    }

    public void saveObject(CrmuserEntity crmuser)
    {
       session.beginTransaction();
       session.save(crmuser);
       session.getTransaction().commit();


    }

    public CrmuserEntity getObject(String userID)
    {
        CrmuserEntity ret;



session.beginTransaction();
        ret=session.get(CrmuserEntity.class,userID);

        return ret;
    }

    public void updateObject(CrmuserEntity user)
    {
        session.update(user);
        session.getTransaction().commit();
    }

    public void removeObject(CrmuserEntity crmuser)
    {
        session.beginTransaction();
        session.delete(crmuser);
        session.getTransaction().commit();
    }

        public List<CrmuserEntity> getListObjects()
    {
        String hql="FROM CrmuserEntity";
        session.beginTransaction();
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

       session.beginTransaction();

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
