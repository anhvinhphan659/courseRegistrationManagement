package Main;

import DAO.CRMclassDAO;
import DAO.CRMuserDAO;
import DAO.StudentDAO;
import POJO.CrmclassEntity;
import POJO.CrmuserEntity;
import POJO.StudentEntity;

import java.util.List;

public class Main
{
    public static void main(String[]args)
    {
        StudentEntity test=new StudentEntity();
        CrmuserEntity USERTEST=new CrmuserEntity();
        CRMuserDAO crMuserDAO=new CRMuserDAO();
        USERTEST.setUserid("ST19120680");

        crMuserDAO.saveObject(USERTEST);
        StudentDAO studdao=new StudentDAO();
//
//        test.setGender(false);
//        test.setUserid("ST19120707");
//        test.setStudentname("Phan Nguyen Anh Vinh");
//        test.setStudentid("19120707");
//        test.setClassid("19CTT4");
//
//        studdao.saveObject(test);

        test.setStudentid("19120680");
        test.setUserid("ST19120680");

        studdao.saveObject(test);
    }
}
