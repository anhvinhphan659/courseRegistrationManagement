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
        StudentDAO studdao=new StudentDAO();

        test.setGender(false);
        test.setUserid("ST19120721");
        test.setStudentname("Phan Nguyen Anh Vinh");
        test.setStudentid("19120721");
        test.setClassid("19CTT4");

        studdao.saveObject(test);
    }
}
