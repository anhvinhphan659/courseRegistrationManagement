package UI;

import DAO.*;
import POJO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                    if(CMD.compareTo("Account")==0)
                        saveData(dataObj,CRMuserDAO.class);
                    if(CMD.compareTo("Subject")==0)
                        saveData(dataObj, SubjectDAO.class);
                    if(CMD.compareTo("CourseOpen")==0)
                        saveData(dataObj, CourseOpenDAO.class);
                    if(CMD.compareTo("Class")==0)
                        saveData(dataObj,CRMclassDAO.class);
                    if(CMD.compareTo("Student")==0)
                        saveData(dataObj, StudentDAO.class);
                    JOptionPane.showMessageDialog(null,"Add new user success!");
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
    }
}

class handleData
{

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