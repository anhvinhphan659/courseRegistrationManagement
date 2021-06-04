package UI;

import DAO.*;
import POJO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class DataHandle
{

}
class addActionHandle
{

    public static void setUpAdd(String[]labelData,Object[]data,String CMD)
    {
        System.out.println("Setting up add"+CMD);
        //set up new Frame display to add
        JFrame addFrame=new JFrame("Add "+CMD);
        addFrame.setDefaultLookAndFeelDecorated(true);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setSize(new Dimension(400,200));
        addFrame.setPreferredSize(new Dimension(400,200));


        int len=labelData.length;

        JPanel addPanel=new JPanel();
        addPanel.setPreferredSize(new Dimension(300,200));
        addPanel.setLayout(new GridLayout(2,len));

        //create object in frame
        for(int i=0;i<len;i++)
        {
            JLabel curLabel=new JLabel(labelData[i]);
            curLabel.setHorizontalAlignment(JLabel.CENTER);
            addPanel.add(curLabel);
        }

        //create a list to store component create
        java.util.List<Component> dataInput=new ArrayList<>();
        for(int i=0;i<len;i++)
        {
            if(data[i].getClass()==Boolean.class)
            {
                JCheckBox checkBox=new JCheckBox();
                checkBox.setSelected((Boolean) data[i]);
                addPanel.add(checkBox);
                dataInput.add(checkBox);
            }
            else {
                JTextField curTextField = new JTextField();
                addPanel.add(curTextField);
                dataInput.add(curTextField);
            }
        }


        JPanel confirmPanel=new JPanel();
        confirmPanel.setPreferredSize(new Dimension(100,addFrame.getHeight()));
        confirmPanel.setMaximumSize(new Dimension(100,addFrame.getHeight()));
        JButton confirmButton=new JButton("Confirm");
        confirmPanel.add(confirmButton,BorderLayout.CENTER);

        addFrame.add(addPanel,BorderLayout.CENTER);
        addFrame.add(confirmPanel,BorderLayout.EAST);

        //set up button
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object[] dataObj=handleData.getDataComponent(dataInput);
                if(handleData.isMissingData(dataObj)==true)
                {
                    JOptionPane.showMessageDialog(null,"Please fill full data to process!");
                }
                else {
                    if(CMD.compareTo("Account")==0) {
                        saveData(dataObj, CRMuserDAO.class);
                        JOptionPane.showMessageDialog(null, "Add new user success!");
                    }
                    if(CMD.compareTo("Subject")==0) {
                        saveData(dataObj, SubjectDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new subject success!");
                    }

                    if(CMD.compareTo("CourseOpen")==0) {
                        saveData(dataObj, CourseOpenDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new course success!");
                    }
                    if(CMD.compareTo("Class")==0)
                    {
                        saveData(dataObj,CRMclassDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new class success!");
                    }
                    if(CMD.compareTo("Student")==0) {
                        saveData(dataObj, StudentDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new Student success!");
                    }
                    if(CMD.compareTo("Semester")==0)
                    {
                        saveData(dataObj,SemesterDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new Semester success!");
                    }
                    if(CMD.compareTo("Semses")==0)
                    {
                        saveData(dataObj,SemesterSessionDAO.class);
                        JOptionPane.showMessageDialog(null,"Add new Semester Session success!");
                    }
                    addFrame.dispose();
                }
        }
        });
        addFrame.setVisible(true);

    }

    public static void saveData(Object[] dataObj,Class saveClass)
    {
        if (CRMuserDAO.class.equals(saveClass))
        {
            CRMuserDAO dao=new CRMuserDAO();
            CrmuserEntity obj=new CrmuserEntity(dataObj);
            dao.saveObject(obj);
        }
        if(SubjectDAO.class.equals(saveClass))
        {
            SubjectDAO dao=new SubjectDAO();
            SubjectEntity obj=new SubjectEntity(dataObj);
            dao.saveObject(obj);
        }
        if(CourseOpenDAO.class.equals(saveClass))
        {
            CourseOpenDAO courseOpenDAO=new CourseOpenDAO();
            CourseopenEntity obj=new CourseopenEntity(dataObj);
            courseOpenDAO.saveObject(obj);
        }
        if(CRMclassDAO.class.equals(saveClass))
        {
            CRMclassDAO crMclassDAO=new CRMclassDAO();
            CrmclassEntity obj=new CrmclassEntity(dataObj);
            crMclassDAO.saveObject(obj);
        }
        if(StudentDAO.class.equals(saveClass))
        {
            StudentDAO studentDAO=new StudentDAO();
            StudentEntity obj=new StudentEntity(dataObj);
            studentDAO.saveObject(obj);

        }
        if(SemesterDAO.class.equals(saveClass))
        {
            SemesterDAO semesterDAO=new SemesterDAO();
            SemesterEntity obj=new SemesterEntity(dataObj);
            semesterDAO.saveObject(obj);
        }
        if(SemesterSessionDAO.class.equals(saveClass))
        {
            SemesterSessionDAO semesterSessionDAO=new SemesterSessionDAO();
            SemestersessionEntity obj=new SemestersessionEntity(dataObj);
            System.out.println("Save Object "+SemestersessionEntity.class);
            semesterSessionDAO.saveObject(obj);
        }
    }
}

class editActionHanle
{
    public static void setUpEdit(String[] labelData,Object[]data,String CMD)
    {
        System.out.println("Setting up add"+CMD);
        //set up new Frame display to add
        JFrame editFrame=new JFrame("Edit "+CMD);
        editFrame.setDefaultLookAndFeelDecorated(true);
        editFrame.setAlwaysOnTop(true);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setSize(new Dimension(400,200));
        editFrame.setPreferredSize(new Dimension(400,200));


        int len=labelData.length;

        JPanel editPanel=new JPanel();
        editPanel.setPreferredSize(new Dimension(300,200));
        editPanel.setLayout(new GridLayout(2,len));

        //create object in frame
        for(int i=0;i<len;i++)
        {
            JLabel curLabel=new JLabel(labelData[i]);
            curLabel.setHorizontalAlignment(JLabel.CENTER);
            editPanel.add(curLabel);
        }

        System.out.println(len);
        //create a list to store component create
        java.util.List<Component> dataInput=new ArrayList<>();
        for(int i=0;i<len;i++)
        {

            if(data[i].getClass()==Boolean.class)
            {
                JCheckBox checkBox=new JCheckBox();
                checkBox.setSelected((Boolean) data[i]);
                editPanel.add(checkBox);
                dataInput.add(checkBox);
            }
            else if (data[i].getClass()==java.sql.Date.class)
            {
                JTextField curTextField = new JTextField();
                editPanel.add(curTextField);
                java.sql.Date date=(java.sql.Date)data[i];
                String day=Integer.valueOf(date.getDate()).toString();
                String month=Integer.valueOf(date.getMonth()+1).toString();
                String year=Integer.valueOf(date.getYear()+1900).toString();
                if(date.getDate()<10)
                    day="0"+day;
                if(date.getMonth()+1<10)
                    month="0"+month;
                curTextField.setText(year+"-"+month+"-"+day);
                dataInput.add(curTextField);
            }
            else {
                JTextField curTextField = new JTextField();
                editPanel.add(curTextField);
                if(data[i].getClass()==Integer.class)
                {
                    curTextField.setText(data[i].toString());
                }
                else {
                    curTextField.setText((String) data[i]);
                }
                dataInput.add(curTextField);
            }
        }
        System.out.println(dataInput.size());

        //disable for ID
        dataInput.get(0).setEnabled(false);

        JPanel confirmPanel=new JPanel();
        confirmPanel.setPreferredSize(new Dimension(100,editFrame.getHeight()));
        confirmPanel.setMaximumSize(new Dimension(100,editFrame.getHeight()));
        JButton confirmButton=new JButton("Confirm");
        confirmPanel.add(confirmButton,BorderLayout.CENTER);

        editFrame.add(editPanel,BorderLayout.CENTER);
        editFrame.add(confirmPanel,BorderLayout.EAST);

        //set up button
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object[] dataObj=handleData.getDataComponent(dataInput);
                if(handleData.isMissingData(dataObj)==true)
                {
                    JOptionPane.showMessageDialog(null,"Please fill full data to process!");
                }
                else {
                    //TODO: do sthing with database
                    if (CMD.compareTo("Account")==0)
                    {
                        //disable to edit ID
                        dataInput.get(1).setEnabled(false);
                        editData(dataObj,CRMuserDAO.class);
                        JOptionPane.showMessageDialog(null,"Update user successfully!");
                    }
                    if(CMD.compareTo("Subject")==0)
                    {
                        editData(dataObj,SubjectDAO.class);
                        JOptionPane.showMessageDialog(null,"Update subject successfully!");
                    }
                    if(CMD.compareTo("Course")==0)
                    {
                        editData(dataObj,CourseOpenDAO.class);
                        JOptionPane.showMessageDialog(null,"Update Course successfully!");
                    }
                    if(CMD.compareTo("Student")==0)
                    {
                        dataInput.get(4).setEnabled(false);
                        editData(dataObj,StudentDAO.class);
                        JOptionPane.showMessageDialog(null,"Update Student successfully!");
                    }
                    if(CMD.compareTo("Class")==0)
                    {
                        editData(dataObj,CRMclassDAO.class);

                        JOptionPane.showMessageDialog(null,"Update Class successfully!");
                    }
                    if(CMD.compareTo("Semester")==0)
                    {
                        editData(dataObj,SemesterDAO.class);

                        JOptionPane.showMessageDialog(null,"Update Semester successfully!");
                    }
                    if(CMD.compareTo("SemesterSession")==0)
                    {
                        editData(dataObj,SemesterSessionDAO.class);

                        JOptionPane.showMessageDialog(null,"Update Semester successfully!");
                    }

                    editFrame.dispose();
                }
            }
        });
        editFrame.setVisible(true);
    }

    public static void editData(Object[] dataObj,Class saveClass)
    {
        if(CRMuserDAO.class.equals(saveClass))
        {
            CRMuserDAO crMuserDAO=new CRMuserDAO();
            CrmuserEntity obj=crMuserDAO.getObject((String)dataObj[0]);
            obj.setPass((String) dataObj[2]);
            obj.setIsadmin((Boolean) dataObj[3]);
            crMuserDAO.updateObject(obj);
        }
        if(SubjectDAO.class.equals(saveClass))
        {
            SubjectDAO subjectDAO=new SubjectDAO();
            SubjectEntity obj=subjectDAO.getObject((String) dataObj[0]);
            obj.setCredit((Integer)dataObj[2]);
            obj.setSubjectname((String) dataObj[1]);
            obj.setFalculty((String) dataObj[3]);
            subjectDAO.updateObject(obj);
        }
        if(CRMclassDAO.class.equals(saveClass))
        {
            CRMclassDAO crMclassDAO=new CRMclassDAO();
            CrmclassEntity obj=crMclassDAO.getObject((String) dataObj[0]);
            obj.setMale((Integer)dataObj[1] );
            obj.setFemale((Integer)dataObj[2] );
            String schoolYear=(String)dataObj[4];
            String[] years=schoolYear.split("-");
            obj.setYearstart(Integer.valueOf(years[0]));
            obj.setYearend(Integer.valueOf(years[1]));
            crMclassDAO.updateObject(obj);

        }
        if(StudentDAO.class.equals(saveClass))
        {
            StudentDAO studentDAO=new StudentDAO();
            StudentEntity obj=studentDAO.getObject((String) dataObj[0]);
            obj.setStudentname((String) dataObj[1]);
            obj.setGender((Boolean) dataObj[2]);
            obj.setClassid((String) dataObj[3]);
            studentDAO.updateObject(obj);
        }
        if(SemesterDAO.class.equals(saveClass))
        {
            SemesterDAO semesterDAO=new SemesterDAO();
            SemesterEntity obj=semesterDAO.getObject((String) dataObj[0]);
            obj.setNamesemester(Integer.valueOf((String) dataObj[1]));
            obj.setYearsemester(Integer.valueOf((String) dataObj[2]));
            obj.setDatebegin(SemestersessionEntity.convertStringToDate((String) dataObj[3]));
            obj.setDateend(SemestersessionEntity.convertStringToDate((String) dataObj[3]));

            semesterDAO.updateObject(obj);
        }
        if(SemesterSessionDAO.class.equals(saveClass))
        {
            SemesterSessionDAO semesterSessionDAO=new SemesterSessionDAO();
            SemestersessionEntity obj=semesterSessionDAO.getObject((String) dataObj[0]);
            obj.setIdsemester((String)dataObj[1]);
            obj.setDatebegin((Date)dataObj[2]);
            obj.setDateend((Date) dataObj[3]);
            semesterSessionDAO.updateObject(obj);
        }
    }
}

class handleData
{
    public static Object[][] toArray(Vector<Vector>data)
    {
        int size=data.size();
        Object[][]ret =new Object[size][];
        for(int i=0;i<size;i++)
        {
            ret[i]=data.get(i).toArray();
        }

        return ret;
    }
    public static Object[] getDataColumn(Object[][]data,int column)
    {
        List<Object>ret=new ArrayList<>();
        int len= data.length;;
        for(int i=0;i<len;i++)
            ret.add(data[i][column]);
        return ret.toArray();
    }
    public static Object[][] filterData(Object[][]origindata, int column, String cur)
    {
        List<Object[]>ret=new ArrayList<>();
        List<Object[]>data=Arrays.asList(origindata);
        System.out.println(column);
        System.out.println(data.size());
        for(int i=0;i<data.size();i++)
        {
            String text;
            if(data.get(i)[column]==null)
                text="";
            else if(data.get(i)[column].getClass()== Date.class)
                text=new SimpleDateFormat("yyyy/MM/dd").format((Date)data.get(i)[column]);
            else if(data.get(i)[column].getClass()==Integer.class)
                text=((Integer)data.get(i)[column]).toString();
            else if(data.get(i)[column].getClass()==Boolean.class)
                text=((Boolean)data.get(i)[column]).toString();
            else
                text=(String) data.get(i)[column];

            if(text.indexOf(cur)>=0)
                ret.add(origindata[i]);
        }
        System.out.println(ret.size());
        return ret.toArray(new Object[0][]);
    }

    public static boolean isMissingData(Object[] dataObj)
    {
        int len= dataObj.length;
        for(int i=0;i<len;i++)
        {
            if(dataObj[i].getClass()==String.class)
            {
                String temp=(String) dataObj[i];
                if(temp.isEmpty()==true)
                    return true;
            }
        }
        return false;
    }
    public  static Object[] getDataComponent(List<Component>dataCompo)
    {
        List<Object> retList=new ArrayList<>();
        for(int i=0;i<dataCompo.size();i++)
        {
            Component dat=dataCompo.get(i);
            if(dat.getClass()==JCheckBox.class)
                retList.add(((JCheckBox) dat).isSelected());
            else {
                JTextField temp=(JTextField)dat;
                retList.add(temp.getText());
            }

        }
        return retList.toArray();

    }


    public static Object[][] getDatawithState(Object[][]data,int boolColumn,Boolean val)
    {
        Object[][] ret;
        java.util.List<Object[]> listData= Arrays.asList(data);
        java.util.List<Object[]> listRet=new ArrayList<>();
        for(int i=0;i<listData.size();i++)
        {
            if(listData.get(i)[boolColumn]==val)
                listRet.add(listData.get(i));
        }

        ret=listRet.toArray(new Object[0][]);
        return ret;
    }

    public static Object[][] removeData(Object[][]data,int row)
    {
        Object[][] ret;
        java.util.List<Object[]> listData=Arrays.asList(data);
        listData.remove(row);

        ret=listData.toArray(new Object[0][]);
        return ret;
    }

    public static Object[][] addData(Object[][]data,Object[]newData)
    {
        Object[][] ret;
        java.util.List<Object[]> listData=Arrays.asList(data);
        listData.add(newData);

        ret=listData.toArray(new Object[0][]);
        return ret;
    }

    public static Object[] getDistinct(Object[] data)
    {
        List<Object> objData=Arrays.asList(data);
        objData=objData.stream().distinct().collect(Collectors.toList());
        return  objData.toArray();

    }

}