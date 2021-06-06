package UI;

import DAO.*;
import POJO.*;
import org.hibernate.event.service.spi.JpaBootstrapSensitive;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class MyDefaultTableModel extends DefaultTableModel {
    private Boolean[][] editable_cells; // 2d array to represent rows and columns

    MyDefaultTableModel(int rows, int cols) { // constructor
        super(rows, cols);
        this.editable_cells = new Boolean[rows][cols];
    }


    MyDefaultTableModel(Object[][]data,Object[] columnname)
    {

        super(data,columnname);
        int rows=data.length;
        int cols=columnname.length;
        this.editable_cells = new Boolean[rows][cols];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                editable_cells[i][j]=false;
    }
    @Override
    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
        return editable_cells[row][column];
    }

    public void setCellEditable(int row, int col, boolean value) {
        this.editable_cells[row][col] = value; // set cell true/false
    }

    public void setColumnEditable(int column,Boolean value)
    {
        for(int i=0;i<editable_cells.length;i++)
        {
            setCellEditable(i,column,value);
        }
    }
    public void setColumnValue(int column,Object value)
    {
        for(int i=0;i<editable_cells.length;i++)
        {
            setValueAt(value,i,column);
        }
    }

    @Override
    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
        if(dataVector!=null) {
            int rows = dataVector.length;
            int cols = columnIdentifiers.length;
            this.editable_cells = new Boolean[rows][cols];
            for(int i=0;i<rows;i++)
                for(int j=0;j<cols;j++)
                    editable_cells[i][j]=false;
        }
    }
}

public class StudentUI
{
    private JFrame mainframe;
    private Object[][] courseOpenTableData;
    private Object[][] courseRegistTableData;
    private Object[][] displayCourseOpenData;
    private Object[][] displayRegistOpenData;
    private static String account;
    private static String pass;
    private static java.sql.Date currentDate;
    private static StudentEntity user;
    public StudentUI()
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");

        courseOpenTableData= CourseOpenDAO.convertToObject(new CourseOpenDAO().getListObjects());
        courseRegistTableData = CourseregistDAO.convertToObject(new CourseregistDAO().getListObject());
        currentDate=new Date(Calendar.getInstance().getTime().getTime());

    }

    public StudentUI(String acc,String pas)
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");


        courseRegistTableData = CourseregistDAO.convertToObject(new CourseregistDAO().getListObject());
        currentDate=new Date(Calendar.getInstance().getTime().getTime());
        setAccount(acc);
        setPass(pas);

        user=new StudentDAO().getObject(account);

        String headerTable[]={"SubjectID","Subject name","Credit","Course class","Shift","Teacher","Total","Registred","Choose"};

        String idSemester=new SemesterDAO().getCurrentIDSemester();
        CourseOpenDAO courseOpenDAO=new CourseOpenDAO();
        SubjectDAO subjectDAO=new SubjectDAO();
        List<CourseopenEntity>courseOpenData=courseOpenDAO.getListObjects();
        List<Object[]>courseOpenListDisplay=new ArrayList<>();
        for(int i=0;i<courseOpenData.size();i++)
        {
            if(courseOpenData.get(i).getSemsesid().compareTo(idSemester)==0)
            {
                CourseopenEntity course=courseOpenData.get(i);
                SubjectEntity subject=subjectDAO.getObject(course.getSubjectid());
                Object[] addData={
                        subject.getSubjectid(),
                        subject.getSubjectname(),
                        subject.getCredit(),
                        course.getCourseclass(),
                        CourseOpenDAO.getShift(course),
                        course.getTeacher(),
                        course.getMaxtotal(),
                        Integer.valueOf(0),
                        false
                };

                courseOpenListDisplay.add(addData);
            }
        }
        displayCourseOpenData=courseOpenListDisplay.toArray(new Object[0][]);



    }


    public String getAccount() {
        return account;
    }

    public String getPass() {
        return pass;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setMainframe(JFrame anotherFrame)
    {
        mainframe=anotherFrame;
    }

    public JFrame getMainframe()
    {
        return mainframe;
    }

    public void setUpDisplay()
    {
        //remove all old componet
        mainframe.getContentPane().removeAll();
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainframe.setSize(new Dimension(1000,800));

        //set up left panel
        JPanel leftPanel=new JPanel();
        leftPanel.setLayout(new GridLayout(8,1));
        leftPanel.setBackground(new Color(55,60,55));
        leftPanel.setPreferredSize(new Dimension(200,800));
        leftPanel.setMaximumSize(new Dimension(200,mainframe.getHeight()));
        JButton courseButton=new JButton("COURSE REGITRATION");
        JButton myaccountButton=new JButton("MY ACCOUNT");
        JButton registedCourseButton=new JButton("REGISTED COURSE");
        JButton exitButton=new JButton("Exit");
        JLabel emptylabel1=new JLabel();
        JLabel emptylabel2=new JLabel();
        JLabel emptylabel3=new JLabel();
        JLabel emptylabel4=new JLabel();
        JLabel emptylabel5=new JLabel();
        JLabel emptylabel6=new JLabel();

        courseButton.setForeground(Color.WHITE);
        courseButton.setBackground(new Color(55,65,55));
        myaccountButton.setForeground(Color.WHITE);
        myaccountButton.setBackground(new Color(55,65,55));
        exitButton.setBackground(new Color(55,65,55));
        exitButton.setForeground(Color.white);
        registedCourseButton.setBackground(new Color(55,65,55));;
        registedCourseButton.setForeground(Color.white);

        //add component to panel
        leftPanel.add(courseButton);
        leftPanel.add(registedCourseButton);
        leftPanel.add(myaccountButton);
        leftPanel.add(emptylabel1);
        leftPanel.add(emptylabel2);
        leftPanel.add(emptylabel3);
        leftPanel.add(emptylabel4);

        leftPanel.add(exitButton);
        //set up center panel
        JPanel centerPanel=new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(800,800));



        //setup for mainframe
        mainframe.add(leftPanel, BorderLayout.WEST);

        mainframe.add(centerPanel,BorderLayout.CENTER);
        //set up button
        courseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpCourseRegistDisplay(centerPanel);
                mainframe.setVisible(true);
            }
        });
        myaccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpMyAccountDisplay(centerPanel);
                mainframe.setVisible(true);
            }
        });
        registedCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAllCourseRegisted(centerPanel);
                mainframe.setVisible(true);
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainframe.dispose();
            }
        });


        mainframe.setVisible(true);
    }

    public void setUpCourseRegistDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        //set up data

        String headerTable[]={"SubjectID","Subject name","Credit","Course class","Shift","Teacher","Total","Registred","Choose"};


        MyDefaultTableModel df=new MyDefaultTableModel(displayCourseOpenData,headerTable);
        df.setCellEditable(0,6,false);
        JTable courseOpenTable=new JTable(df) {

            public Class getColumnClass(int column) {

                switch (column) {
                    case 2:
                        return Integer.class;
                    case 6:
                        return Integer.class;
                    case 7:
                        return Integer.class;
                    case 8:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }



        } ;
        for(int i=0;i<displayCourseOpenData.length;i++)
            if((Boolean) displayCourseOpenData[i][8]==true)
            {
                df.setCellEditable(i,8,false);
            }
        else
            {
                df.setCellEditable(i,8,true);
            }

        Object[][] courseRegistData=handleData.getDatawithState(displayCourseOpenData,8,true);

        MyDefaultTableModel df2=new MyDefaultTableModel(courseRegistData,headerTable);
        JTable resultTable=new JTable(df2)
        {
            public Class getColumnClass(int column) {

                switch (column) {
                    case 2:
                        return Integer.class;
                    case 6:
                        return Integer.class;
                    case 7:
                        return Integer.class;
                    case 8:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        df2.setColumnEditable(8,true);
        df2.setColumnValue(8,false);

        //set up bottomPanel
        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        bottomPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        bottomPanel.setBackground(Color.ORANGE);

        //set up scroll
        courseOpenTable.setAutoCreateRowSorter(true);
        courseOpenTable.setRowHeight(25);


        JScrollPane courseOpenScroll=new JScrollPane(courseOpenTable);
        courseOpenScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        JTable courseRegist =new JTable(df);
        courseRegist.setAutoCreateRowSorter(true);
        courseRegist.setRowHeight(25);

        JScrollPane courseRegistScroll=new JScrollPane(resultTable);
        courseRegistScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        //set up course open panel
        JPanel courseOpenPanel=new JPanel();
        courseOpenPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),300));
        courseOpenPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),350));
        courseOpenPanel.setLayout(new BorderLayout());
        courseOpenPanel.setBorder(new LineBorder(Color.BLACK));
        courseOpenPanel.setBackground(Color.WHITE);

        JPanel topCoursePanel=new JPanel();
        topCoursePanel.setPreferredSize(new Dimension(currentPanel.getWidth(),60));
        topCoursePanel.setLayout(null);

        JLabel courseAnnounceLabel=new JLabel("Course open this semester:");
        courseAnnounceLabel.setBounds(10,10,300,50);

        topCoursePanel.add(courseAnnounceLabel);

        JPanel bottomCoursePanel=new JPanel();
        bottomCoursePanel.setPreferredSize(new Dimension(currentPanel.getWidth(),80));
        bottomCoursePanel.setLayout(null);

        JButton registButton=new JButton("Regist");
        registButton.setBounds(10,10,100,50);

        bottomCoursePanel.add(registButton);

        courseOpenPanel.add(topCoursePanel,BorderLayout.NORTH);
        courseOpenScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),courseOpenPanel.getHeight()-80));
        courseOpenPanel.add(courseOpenScroll,BorderLayout.CENTER);
        courseOpenPanel.add(bottomCoursePanel,BorderLayout.SOUTH);

        //set up resultPanel
        JPanel resultPanel =new JPanel();
        resultPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),300));
        resultPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),400));
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(new LineBorder(Color.BLACK));
        resultPanel.setBackground(Color.WHITE);

        JPanel topResultPanel=new JPanel();
        topResultPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),60));
        topResultPanel.setLayout(null);

        JLabel resultAnnounceLabel=new JLabel("Course registration result this semester:");
        resultAnnounceLabel.setBounds(10,10,300,50);

        topResultPanel.add(resultAnnounceLabel);

        JPanel bottomResultPanel=new JPanel();
        bottomResultPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),80));
        bottomResultPanel.setLayout(null);

        JButton unregistButton=new JButton("Unregist");
        unregistButton.setBounds(10,10,100,50);

        bottomResultPanel.add(unregistButton);
        bottomResultPanel.setBackground(Color.WHITE);

        resultPanel.add(topResultPanel,BorderLayout.NORTH);
        courseRegistScroll.setPreferredSize(new Dimension(resultPanel.getWidth(),resultPanel.getHeight()-80));
        resultPanel.add(courseRegistScroll,BorderLayout.CENTER);
        resultPanel.add(bottomResultPanel,BorderLayout.SOUTH);

        //set up button
        registButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CourseregistDAO courseregistDAO=new CourseregistDAO();
                Object[][] tempdata=handleData.toArray(df.getDataVector());
                int len=tempdata.length;
                for(int i=0;i<len;i++)
                    if((Boolean) tempdata[i][8]) {
                        System.out.println(tempdata[i][0]);
                        df.setCellEditable(i,8,false);

                        //update display
                        displayCourseOpenData=handleData.toArray(df.getDataVector());
                        Object[][] displayData=handleData.getDatawithState(displayCourseOpenData,8,true);

                        df2.setDataVector(displayData,headerTable);
                        df2.setColumnEditable(8,true);
                        df2.setColumnValue(8,false);
                        resultTable.setModel(df2);
                        mainframe.setVisible(true);
                        //TODO: do sth with database
                        CourseregistEntity obj=new CourseregistEntity();
                        obj.setIdstudent(account);
                        String openID=(String) tempdata[i][0]+(String) tempdata[i][3];
                        obj.setOpenid(openID);
                        obj.setDateregist(currentDate);
                        obj.setIdregister(account+openID);
                        courseregistDAO.saveOrUpdateObject(obj);

                        //update dsplay to program



                    }
            }
        });

        unregistButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CourseregistDAO courseregistDAO=new CourseregistDAO();
                Object[][] tempdata = handleData.toArray(df2.getDataVector());
                Object[][] openData=handleData.toArray(df.getDataVector());
                //update dat in course open table
                for(int i=0;i<tempdata.length;i++)
                {
                    Object[]cur=tempdata[i];
                    if((Boolean) cur[8]==true) {
                        for (int j = 0; j < openData.length; j++) {
                            Object[] opencur = openData[j];
                            if ((String) cur[0] == (String) opencur[0]) {
                                if ((String) cur[1] == (String) opencur[1]) {
                                    df.setValueAt(false, j, 8);
                                    df.setCellEditable(j, 8, true);
                                }
                            }
                        }
                    }
                    //TODO: do sth with database
                    String openID=(String)cur[0]+(String) cur[3];
                    String IDregist=account+openID;
                    CourseregistEntity obj=courseregistDAO.getObject(IDregist);
                    courseregistDAO.removeObject(obj);
                }

                //update display for course result
                openData=handleData.toArray(df.getDataVector());
                tempdata=handleData.getDatawithState(openData,8,true);
                df2.setDataVector(tempdata,headerTable);
                df2.setColumnEditable(8,true);
                df2.setColumnValue(8,false);
                resultTable.setModel(df2);
                mainframe.setVisible(true);


                System.out.println(tempdata.length);

            }
        });

        //add to current
        currentPanel.add(courseOpenPanel,BorderLayout.NORTH);
        currentPanel.add(resultPanel,BorderLayout.CENTER);
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);

    }

    public void setUpMyAccountDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setBackground(Color.BLACK);
        currentPanel.setLayout(new BorderLayout());
        System.out.println("Set up Account");

        JPanel topPanel=new JPanel();
        JPanel centerPanel=new JPanel();
        JPanel bottomPanel=new JPanel();




        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setBackground(Color.ORANGE);
        bottomPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        bottomPanel.setBackground(Color.BLUE);
        centerPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),600));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(2,1));

        //set up information panel
        JPanel informationPanel=new JPanel();
        informationPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),300));
        informationPanel.setLayout(null);
        informationPanel.setBorder(new EtchedBorder(0));

//        currentPanel.setLayout(null);
        JLabel myaccountLabel=new JLabel("My account 's information:");
        JLabel studentIDLabel=new JLabel("Student ID:");
        JTextField studentIDTextField=new JTextField();
        JLabel nameLabel=new JLabel("Name:");
        JTextField nameTextField=new JTextField();
        JLabel classLabel=new JLabel("Class: ");
        JTextField classTextField=new JTextField();
        JLabel genderLabel=new JLabel("Gender:");
        JTextField genderTextField=new JTextField();

        //set up label and textfield

        myaccountLabel.setBounds(20,20,200,30);
        studentIDLabel.setBounds(20,70,100,30);
        studentIDTextField.setBounds(150,70,350,30);
        nameLabel.setBounds(20,120,100,30);
        nameTextField.setBounds(150,120,350,30);
        classLabel.setBounds(20,170,100,30);
        classTextField.setBounds(150,170,350,30);
        genderLabel.setBounds(20,220,100,30);
        genderTextField.setBounds(150,220,350,30);

        studentIDTextField.setText(account);
        studentIDTextField.setEditable(false);
        nameTextField.setText(user.getStudentname());
        nameTextField.setEditable(false);
        classTextField.setText(user.getClassid());
        classTextField.setEditable(false);
        if(user.getGender()==true)
            genderTextField.setText("Female");
        else
            genderTextField.setText("Male");
        genderTextField.setEditable(false);

        //add some infor
        informationPanel.add(myaccountLabel);
        informationPanel.add(studentIDLabel);
        informationPanel.add(studentIDTextField);
        informationPanel.add(nameLabel);
        informationPanel.add(nameTextField);
        informationPanel.add(classLabel);
        informationPanel.add(classTextField);
        informationPanel.add(genderLabel);
        informationPanel.add(genderTextField);



        //set up change pass panel
        JPanel changePassPanel=new JPanel();
        changePassPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),300));
        changePassPanel.setLayout(null);
        changePassPanel.setBorder(new EtchedBorder(0));

        JLabel changpassLabel=new JLabel("Change password: ");
        JLabel oldpasswordLabel=new JLabel("Old password: ");
        JTextField oldpassTextField=new JTextField();
        JLabel newpasswodLabel=new JLabel("New password: ");
        JTextField newpassTextField=new JTextField();
        JButton changePassButton=new JButton("Change password");


        //set up label and textfield
        changpassLabel.setBounds(20,20,300,30);
        oldpasswordLabel.setBounds(20,70,100,30);
        oldpassTextField.setBounds(140,70,350,30);
        newpasswodLabel.setBounds(20,120,100,30);
        newpassTextField.setBounds(140,120,350,30);
        changePassButton.setBounds(140,170,150,30);

        changePassPanel.add(changpassLabel);
        changePassPanel.add(oldpasswordLabel);
        changePassPanel.add(oldpassTextField);
        changePassPanel.add(newpasswodLabel);
        changePassPanel.add(newpassTextField);
        changePassPanel.add(changePassButton);

        centerPanel.add(informationPanel);
        centerPanel.add(changePassPanel);

        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);
        System.out.println("Finish setting account!");

        //set up button
        changePassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String oldpass=oldpassTextField.getText();
                String newpass=newpassTextField.getText();
                if(oldpass.isEmpty()==true)
                {
                    JOptionPane.showMessageDialog(null,"Please fill in old pass!");
                    return;
                }
                if(oldpass.isEmpty()==true)
                {
                    JOptionPane.showMessageDialog(null,"Please fill in new pass!");
                    return;
                }

                if(pass.compareTo(oldpass)!=0)
                {
                    JOptionPane.showMessageDialog(null,"The old password is not correct!");
                    return;
                }
                else
                {
                    CRMuserDAO crMuserDAO=new CRMuserDAO();
                    CrmuserEntity obj=crMuserDAO.getObject("ST"+account);
                    obj.setPass(newpass);
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to change pass?");
                    if(choose==JOptionPane.YES_OPTION)
                    {
                        crMuserDAO.updateObject(obj);
                        pass=newpass;
                    }


                }
            }
        });
//


//
//        System.out.println("Set up My Account");
    }

    public void setUpAllCourseRegisted(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        //set up data
        String headerTable[]={"SubjectID","Name","Course class","Credit","Shift","Teacher","Date registed"};
        Object[][] courseRegistedData=getAllRegistedCourse();
        MyDefaultTableModel df=new MyDefaultTableModel(courseRegistedData,headerTable);
        JTable courseRegistedTable=new JTable(df)
        {

            public Class getColumnClass(int column) {

                switch (column) {
                    case 3:
                        return Integer.class;

                    default:
                        return String.class;
                }
            }
        };

        courseRegistedTable.setAutoCreateRowSorter(true);
        courseRegistedTable.setRowHeight(25);

        JScrollPane courseRegistedScroll=new JScrollPane(courseRegistedTable);

        //set up panel

        JPanel topPanel =new JPanel();
        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(null);

        JPanel centerPanel=new JPanel();
        centerPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),600));
        centerPanel.setLayout(new BorderLayout());

        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        bottomPanel.setBackground(Color.ORANGE);

        //set up center panel
        centerPanel.add(courseRegistedScroll);

        //add to current

        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);
    }

    public int countRegisted(String openID)
    {
        int count=0;
        int len= courseRegistTableData.length;
        for(int i=0;i<len;i++)
        {
            String cur=(String) courseRegistTableData[i][1];
            System.out.println(cur);
            if(cur.compareTo(openID)==0)
                count++;
        }

        return count;
    }

    public Object[][] getAllRegistedCourse()
    {
            CourseOpenDAO courseOpenDAO=new CourseOpenDAO();
            SubjectDAO subjectDAO=new SubjectDAO();
            List<Object[]>ret=new ArrayList<>();
            List<CourseregistEntity>registedData=new CourseregistDAO().getListObject(account);
            for(int i=0;i<registedData.size();i++)
            {
                CourseregistEntity curRegist=registedData.get(i);
                CourseopenEntity curCourse=courseOpenDAO.getObject(curRegist.getOpenid());
                SubjectEntity subject=subjectDAO.getObject(curCourse.getSubjectid());
                Object[]add={
                        subject.getSubjectid(),
                        subject.getSubjectname(),
                        curCourse.getCourseclass(),
                        subject.getCredit(),
                        CourseOpenDAO.getShift(curCourse),
                        curCourse.getTeacher(),
                        curRegist.getDateregist(),
                };
                ret.add(add);
                System.out.println(add);
            }
            return ret.toArray(new Object[0][]);

    }

}



