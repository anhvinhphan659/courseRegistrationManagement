package Main;

import DAO.*;
import POJO.CrmclassEntity;
import POJO.CrmuserEntity;
import POJO.SemestersessionEntity;
import POJO.StudentEntity;
import UI.*;
import java.util.List;

public class Main
{
    public static void main(String[]args)
    {
//        StudentEntity test=new StudentEntity();
//        CrmuserEntity USERTEST=new CrmuserEntity();
//        CRMuserDAO crMuserDAO=new CRMuserDAO();
//        USERTEST.setUserid("ST19120680");
//
//        crMuserDAO.saveObject(USERTEST);
//        StudentDAO studdao=new StudentDAO();
////
////        test.setGender(false);
////        test.setUserid("ST19120707");
////        test.setStudentname("Phan Nguyen Anh Vinh");
////        test.setStudentid("19120707");
////        test.setClassid("19CTT4");
////
////        studdao.saveObject(test);
//
//        test.setStudentid("19120680");
//        test.setUserid("ST19120680");
//
//        studdao.saveObject(test);
//        CRMuserDAO userdao=new CRMuserDAO();
//        CrmuserEntity data= userdao.getObject("ST19120721");
//        data.setPass("hellowrosd");
//        userdao.updateObject(data);
//        CrmuserEntity TEST=new CrmuserEntity("1912021");
//        userdao.saveObject(TEST);
//        userdao.getObject("1912021");
//        System.out.println(data.toString());
//        System.out.println(userdao.getListObject().size());
//        Object[][]ret=CRMuserDAO.convertToObject(userdao.getListObject());
//        System.out.println(ret.length);
        AcademicUI acaui=new AcademicUI();
        acaui.setUpDisplay();

//        System.out.println("ST19120721".indexOf("ST"));
//        SemesterSessionDAO semesterSessionDAO=new SemesterSessionDAO();
//        SemestersessionEntity test=new SemestersessionEntity();
//        test.setSemsesid("test");
//        semesterSessionDAO.saveObject(test);
//
//        StudentUI studentUI=new StudentUI();
//        studentUI.setUpDisplay();
//        CRMuserDAO crMuserDAO=new CRMuserDAO();
//                       int res= crMuserDAO.checkAccount("sa","123456");
//       System.out.println(res);
    }


}
