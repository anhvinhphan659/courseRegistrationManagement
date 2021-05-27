package Main;

import DAO.CRMclassDAO;
import POJO.CrmclassEntity;

public class Main
{
    public static void main(String[]args)
    {
        CrmclassEntity test=new CrmclassEntity();
        test.setClassid("20CTT4");
        test.setMale(10);
        test.setFemale(10);
        test.setYearend(2020);
        test.setYearend(2023);
        CRMclassDAO crmdao=new CRMclassDAO();
        crmdao.save(test);

    }
}
