package DAO;

import POJO.CrmclassEntity;
import HibernateUtil.hibernateUtil;
import org.hibernate.Session;

public class CRMclassDAO
{
    public void save(CrmclassEntity crmclass)
    {
        hibernateUtil hb=new hibernateUtil();
        hb.saveObject(crmclass);
    }
}
