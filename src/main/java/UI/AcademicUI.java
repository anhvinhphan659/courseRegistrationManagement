package UI;

import javax.swing.*;
import DAO.*;
import POJO.StudentEntity;

import java.awt.event.*;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class AcademicUI
{
    private JFrame mainframe;
     private static Object[][] userTableData;
    private static Object[][] subjectTableData;
    private static Object[][] courseTableData;
    private static Object[][] classTableData;
    private static Object[][] studentTableData;
    private static Object[][] orginCourseData;

    private static Object[][] semesterTableData;
    private static Object[][] semsesTableData;

    public AcademicUI()
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");

        CRMuserDAO crMuserDAO=new CRMuserDAO();
        CRMclassDAO crMclassDAO=new CRMclassDAO();
        SubjectDAO subjectDAO=new SubjectDAO();
        CourseOpenDAO courseOpenDAO=new CourseOpenDAO();
        StudentDAO studentDAO=new StudentDAO();
        SemesterDAO semesterDAO=new SemesterDAO();
        SemesterSessionDAO semesterSessionDAO=new SemesterSessionDAO();

        userTableData=CRMuserDAO.convertToObject(crMuserDAO.getListObjects());
        classTableData=CRMclassDAO.convertToObject(crMclassDAO.getListObjects());
         subjectTableData=SubjectDAO.convertToObject(subjectDAO.getListObjects());
         courseTableData=CourseOpenDAO.convertToObject(courseOpenDAO.getListObjects());
         orginCourseData=courseTableData;
        studentTableData=StudentDAO.convertToObject(studentDAO.getListObjects());
        semesterTableData=SemesterDAO.convertToObject(semesterDAO.getListObjects());
        semsesTableData= SemesterSessionDAO.convertToObject(semesterSessionDAO.getListObjects());
        courseTableData=handleData.getDataWithValue(courseTableData,5,getCurrentSemester());
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
        JButton accountButton=new JButton("ACCOUNT");
        JButton subjectButton=new JButton("SUBJECT");
        JButton classButton=new JButton("CLASS");
        JButton courseButton=new JButton("COURSE");
        JButton semesterButton=new JButton("SEMESTER");

        JButton exitButton=new JButton("Exit");
        JLabel emptylabel1=new JLabel();
        JLabel emptylabel2=new JLabel();
        JLabel emptylabel3=new JLabel();
        JLabel emptylabel4=new JLabel();
        JLabel emptylabel5=new JLabel();
        JLabel emptylabel6=new JLabel();

        accountButton.setBackground(new Color(55,65,55));
        subjectButton.setBackground(new Color(55,65,55));
        classButton.setBackground(new Color(55,65,55));
        courseButton.setBackground(new Color(55,65,55));
        exitButton.setBackground(new Color(55,65,55));
        semesterButton.setBackground(new Color(55,65,55));
        accountButton.setForeground(Color.white);
        subjectButton.setForeground(Color.white);
        classButton.setForeground(Color.white);
        courseButton.setForeground(Color.white);
        exitButton.setForeground(Color.white);
        semesterButton.setForeground(Color.white);
        accountButton.setFont(new Font("Open Sans",Font.BOLD,16));
        subjectButton.setFont(new Font("Open Sans",Font.BOLD,16));
        classButton.setFont(new Font("Open Sans",Font.BOLD,16));
        courseButton.setFont(new Font("Open Sans",Font.BOLD,16));
        exitButton.setFont(new Font("Open Sans",Font.BOLD,16   ));
        semesterButton.setFont(new Font("Open Sans",Font.BOLD,16   ));
        //add to leftpanel
        leftPanel.add(accountButton);
        leftPanel.add(subjectButton);
        leftPanel.add(classButton);
        leftPanel.add(courseButton);
        leftPanel.add(semesterButton);
        leftPanel.add(emptylabel2);
        leftPanel.add(emptylabel3);
        leftPanel.add(exitButton);

        //set up center panel
        JPanel centerPanel=new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(800,800));

        //set up button
        accountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAccountDisplay(centerPanel);
                mainframe.setVisible(true);
            }
        });
        subjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpSubjectDisplay(centerPanel);
                mainframe.setVisible(true);
            }
        });
        courseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpCourseDisplay(centerPanel);
                mainframe.setVisible(true);
            }
        });
        classButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpClassDisplay(centerPanel);
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
        semesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpSemesterDisplay(centerPanel);
                mainframe.setVisible(true);

            }
        });


        //setup for mainframe
        mainframe.add(leftPanel, BorderLayout.WEST);
        setUpAccountDisplay(centerPanel);
        mainframe.add(centerPanel,BorderLayout.CENTER);
        mainframe.setVisible(true);
    }

    public void setUpAccountDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"UserID","Account","Password","isAdmin"};


        //set up data display
        DefaultTableModel df=new DefaultTableModel(userTableData,headerTable);
        JTable accountTable=new JTable(df) {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                return userTableData[0][columnIndex].getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        } ;

        accountTable.setAlignmentX(50);
        accountTable.setAutoCreateRowSorter(true);
        accountTable.setRowHeight(25);
        JScrollPane accountScroll=new JScrollPane(accountTable);
        accountScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        accountScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));
//        accountTable.setSize(accountScroll.getWidth()-50,accountScroll.getHeight()-50);
//        accountScroll.setLayout(null);

        //set up top panel
        JPanel topPanel =new JPanel();
        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(null);

        JLabel searchLabel=new JLabel("Search: ");
        searchLabel.setBounds(30,50,100,30);
        JComboBox searchCombo=new JComboBox(headerTable);
        searchCombo.setBounds(130,50,100,30);
        JTextField searchTextField=new JTextField();
        searchTextField.setBounds(230,50,350,30);
        JButton refreshButton=new JButton("Refresh");
        refreshButton.setBounds(650,50,100,30);

        topPanel.add(searchLabel);
        topPanel.add(searchCombo);
        topPanel.add(searchTextField);
        topPanel.add(refreshButton);


        //set up bottom panel
        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

        JPanel editPanel=new JPanel();
        editPanel.setPreferredSize(new Dimension(100,accountScroll.getHeight()));
        editPanel.setMaximumSize(new Dimension(100,accountScroll.getHeight()));
        editPanel.setBackground(Color.BLUE);
        JButton addButton=new JButton("ADD");
        JButton editButton=new JButton("EDIT");
        JButton removeButton=new JButton("REMOVE");
        editPanel.setLayout(new GridLayout(12,1,10,10));
        editPanel.add(addButton);
        editPanel.add(editButton);
        editPanel.add(removeButton);

        //set up button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=accountTable.getSelectedRow();
                while (selected>=0)
                {

                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION)
                    {

                        Object[][]displayData=handleData.toArray(df.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,CRMuserDAO.class);
                        userTableData =handleData.removeObject(userTableData,handleData.getIndexObject(userTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df.setDataVector(displayData,headerTable);
                        accountTable.setModel(df);

                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }

                    selected=accountTable.getSelectedRow();
                }

            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=accountTable.getSelectedRow();
                if(selected>=0) {
                    Object[][]displayData=handleData.toArray(df.getDataVector());
                    Object[] rowData=displayData[selected];
                    String[] header={"UserID","Account","Pass","IsAdmin"};
                    editActionHanle.setUpEdit(header,rowData,"Account");
                }
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] dataSample={"string","string","string",false};

                addActionHandle.setUpAdd(headerTable,dataSample,"Account");
            }
        });
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
                userTableData=CRMuserDAO.convertToObject(new CRMuserDAO().getListObjects());
                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(userTableData,selected,cur);
                df.setDataVector(displayData,headerTable);
                accountTable.setModel(df);
            }
        });

        //set up jtext
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(classTableData,selected,cur);
                df.setDataVector(dataDisplay,headerTable);
                accountTable.setModel(df);
                mainframe.setVisible(true);
            }
        });

        JPanel contain1=new JPanel();
        JPanel contain2=new JPanel();
        
        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpSubjectDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"SubjectID","Name","Credit","Faculty"};

        DefaultTableModel df=new DefaultTableModel(subjectTableData,headerTable);
        JTable subjectTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        subjectTable.setAutoCreateRowSorter(true);
        subjectTable.setRowHeight(25);

        JScrollPane subjectScroll=new JScrollPane(subjectTable);
        subjectScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        subjectScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));

        //set up top panel
        JPanel topPanel=new JPanel();
        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(null);

        JLabel searchLabel=new JLabel("Search: ");
        searchLabel.setBounds(30,50,100,30);
        JComboBox searchCombo=new JComboBox(headerTable);
        searchCombo.setBounds(130,50,100,30);
        JTextField searchTextField=new JTextField();
        searchTextField.setBounds(230,50,350,30);
        JButton refreshButton=new JButton("Refresh");
        refreshButton.setBounds(650,50,100,30);

        topPanel.add(searchLabel);
        topPanel.add(searchCombo);
        topPanel.add(searchTextField);
        topPanel.add(refreshButton);

        //set up bottom panel
        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

        //set up editPanel
        JPanel editPanel=new JPanel();
        editPanel.setPreferredSize(new Dimension(100,subjectScroll.getHeight()));
        editPanel.setMaximumSize(new Dimension(100,subjectScroll.getHeight()));
        editPanel.setBackground(Color.BLUE);
        JButton addButton=new JButton("ADD");
        JButton editButton=new JButton("EDIT");
        JButton removeButton=new JButton("REMOVE");

        editPanel.setLayout(new GridLayout(12,1,10,10));
        editPanel.add(addButton);
        editPanel.add(editButton);
        editPanel.add(removeButton);

        //set up button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=subjectTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,SubjectDAO.class);

                        subjectTableData =handleData.removeObject(subjectTableData,handleData.getIndexObject(subjectTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);

                        df.setDataVector(displayData,headerTable);
                        subjectTable.setModel(df);
                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected=subjectTable.getSelectedRow();
                }

            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=subjectTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[] rowData= subjectTableData[selected];
                    String[]header={"SubjectID","Name","Credit","Faculty"};
                    System.out.println("Before edit");
                    editActionHanle.setUpEdit(header,rowData,"Subject");
                    System.out.println("After edit");

                }
                String cur=searchTextField.getText();

                //update table


                int columnselected=searchCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(subjectTableData,columnselected,cur);


                df.setDataVector(displayData,headerTable);
                subjectTable.setModel(df);
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object[] dataSample={"String","String",Integer.valueOf(1),"String"};
                addActionHandle.setUpAdd(headerTable,dataSample,"Subject");
                String cur=searchTextField.getText();
                subjectTableData=SubjectDAO.convertToObject(new SubjectDAO().getListObjects());
                int columnselected=searchCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(subjectTableData,columnselected,cur);

                df.setDataVector(displayData,headerTable);
                subjectTable.setModel(df);
                mainframe.setVisible(true);
            }
        });

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
                subjectTableData=SubjectDAO.convertToObject(new SubjectDAO().getListObjects());
                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(subjectTableData,selected,cur);
                df.setDataVector(displayData,headerTable);
                subjectTable.setModel(df);
            }
        });
        //set up jtext
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(subjectTableData,selected,cur);
                df.setDataVector(dataDisplay,headerTable);
                subjectTable.setModel(df);
                mainframe.setVisible(true);
            }
        });



        //add to current
        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(subjectScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpClassDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"ClassID","Male","Female","Total","SchoolYear"};

        //set up Table
        DefaultTableModel df=new DefaultTableModel(classTableData,headerTable);
        JTable classTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        String headerTable2[]={"StudentID","Name","Gender","ClassID","UserID"};
        DefaultTableModel df2=new DefaultTableModel(studentTableData,headerTable2);
        JTable studenTable=new JTable(df2)
        {
            @Override
            public Class<?> getColumnClass(int column) {
                return studentTableData[0][column].getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        classTable.setAutoCreateRowSorter(true);
        classTable.setRowHeight(25);

        //set up empty Panel
        JPanel emptyPanel =new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

        //set up scrollpane
        JScrollPane classScroll=new JScrollPane(classTable);

        JScrollPane studentScroll=new JScrollPane(studenTable);

        //set up classPanel
        JPanel classPanel =new JPanel();
        classPanel.setSize(new Dimension(currentPanel.getWidth(),200));
        classPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),250));
        classPanel.setLayout(new BorderLayout());

        JPanel funcClassPanel=new JPanel();
        funcClassPanel.setPreferredSize(new Dimension(currentPanel.getWidth()-100,50));
        funcClassPanel.setMaximumSize(new Dimension(currentPanel.getWidth()-100,50));
        funcClassPanel.setLayout(null);

        JLabel searchClassLabel=new JLabel("Search: ");
        searchClassLabel.setBounds(10,10,100,30);
        JComboBox searchClassCombo=new JComboBox(headerTable);
        searchClassCombo.setBounds(110,10,100,30);
        searchClassCombo.setBackground(Color.WHITE);
        JTextField searchClassTextField=new JTextField();
        searchClassTextField.setBounds(210,10,350,30);
        JButton refreshClassButton=new JButton("Refresh");
        refreshClassButton.setBounds(680,10,100,30);

        funcClassPanel.add(searchClassLabel);
        funcClassPanel.add(searchClassCombo);
        funcClassPanel.add(searchClassTextField);
        funcClassPanel.add(refreshClassButton);

        JPanel editClassPanel=new JPanel();
        editClassPanel.setPreferredSize(new Dimension(100,classPanel.getHeight()));
        editClassPanel.setBackground(Color.BLUE);
        editClassPanel.setLayout(new GridLayout(5,1,10,10));

        JButton addClassButton=new JButton("Add");
        JButton editClassButton=new JButton("Edit");
        JButton removeClassButton =new JButton("Remove");

        editClassPanel.add(addClassButton);
        editClassPanel.add(editClassButton);
        editClassPanel.add(removeClassButton);

        classScroll.setPreferredSize(new Dimension(classPanel.getWidth()-100,classPanel.getHeight()-80));
        classPanel.add(funcClassPanel,BorderLayout.NORTH);
        classPanel.add(classScroll,BorderLayout.CENTER);
        classPanel.add(editClassPanel,BorderLayout.EAST);

        //set up StudentPanel
        JPanel studentPanel=new JPanel();
        studentPanel.setSize(new Dimension(currentPanel.getWidth(),200));
        studentPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),250));
        studentPanel.setLayout(new BorderLayout());


        JPanel funcStudentPanel=new JPanel();
        funcStudentPanel.setPreferredSize(new Dimension(currentPanel.getWidth()-100,50));
        funcStudentPanel.setMaximumSize(new Dimension(currentPanel.getWidth()-100,50));
        funcStudentPanel.setLayout(null);


        JLabel searchStudentLabel=new JLabel("Search: ");
        searchStudentLabel.setBounds(10,10,100,30);
        JComboBox searchStudentCombo=new JComboBox(headerTable2);
        searchStudentCombo.setBounds(110,10,100,30);
        searchStudentCombo.setBackground(Color.WHITE);
        JTextField searchStudentTextField=new JTextField();
        searchStudentTextField.setBounds(210,10,350,30);
        Object[] classDataCombo=handleData.getDataColumn(classTableData,0);
        Arrays.sort(classDataCombo);
        JComboBox classChooseCombo=new JComboBox(classDataCombo);
        classChooseCombo.addItem("");
        classChooseCombo.setSelectedItem("");

        classChooseCombo.setBounds(580,10,80,30);
        JButton refreshStudentButton=new JButton("Refresh");
        refreshStudentButton.setBounds(680,10,100,30);

        funcStudentPanel.add(searchStudentLabel);
        funcStudentPanel.add(searchStudentCombo);
        funcStudentPanel.add(searchStudentTextField);
        funcStudentPanel.add(classChooseCombo);
        funcStudentPanel.add(refreshStudentButton);

        JPanel editStudentPanel=new JPanel();
        editStudentPanel.setPreferredSize(new Dimension(100,classPanel.getHeight()));
        editStudentPanel.setBackground(Color.BLUE);
        editStudentPanel.setLayout(new GridLayout(5,1,10,10));

        JButton addStudentButton=new JButton("Add");
        JButton editStudentButton=new JButton("Edit");
        JButton removeStudentButton =new JButton("Remove");
        JButton moreStudentButton=new JButton("More... ");

        editStudentPanel.add(addStudentButton);
        editStudentPanel.add(editStudentButton);
        editStudentPanel.add(removeStudentButton);
        editStudentPanel.add(moreStudentButton);

        studentScroll.setPreferredSize(new Dimension(studentPanel.getWidth()-100,studentPanel.getHeight()-80));
        studentPanel.add(funcStudentPanel,BorderLayout.NORTH);
        studentPanel.add(studentScroll,BorderLayout.CENTER);
        studentPanel.add(editStudentPanel,BorderLayout.EAST);

        //set up centerPanel
        JPanel centerPanel=new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),500));

        centerPanel.add(classPanel,BorderLayout.NORTH);
        centerPanel.add(studentPanel,BorderLayout.CENTER);

        //set up button
        moreStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=studenTable.getSelectedRow();
                Object[][]displayData=handleData.toArray(df2.getDataVector());
                Object[]rowData=displayData[selected];
                setUpMoreDisplay(currentPanel,(String) rowData[0]);
                mainframe.setVisible(true);
            }
        });
        addClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] header={"ClassID","Male","Female","Year start","Year end"};
                Object[] datasample={"String",Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(1)};
                addActionHandle.setUpAdd(header,datasample,"Class");
            }
        });

        addStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] header={"StudentID","Name","Gender","ClassID"};
                Object[] sampleData={"String","String",false,"String"};
                addActionHandle.setUpAdd(header,sampleData,"Student");

            }
        });

        removeClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=classTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,CRMclassDAO.class);

                        classTableData =handleData.removeObject(classTableData,handleData.getIndexObject(classTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df.setDataVector(displayData,headerTable);
                        classTable.setModel(df);

                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected=classTable.getSelectedRow();
                }

            }
        });
        removeStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected= studenTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df2.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,StudentDAO.class);

                        studentTableData =handleData.removeObject(studentTableData,handleData.getIndexObject(studentTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df2.setDataVector(displayData,headerTable);
                        studenTable.setModel(df);
                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected= studenTable.getSelectedRow();
                }

            }
        });

        editClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=classTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[][] displayData=handleData.toArray(df.getDataVector());
                    Object[] rowData=displayData[selected];

                    editActionHanle.setUpEdit(headerTable,rowData,"Class");
                }
                mainframe.setVisible(true);

            }
        });

        editStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=studenTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[][] displayData=handleData.toArray(df2.getDataVector());
                    Object[] rowData=displayData[selected];

                    editActionHanle.setUpEdit(headerTable2,rowData,"Student");
                }
                mainframe.setVisible(true);
            }
        });

        refreshClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
                classTableData=CRMclassDAO.convertToObject(new CRMclassDAO().getListObjects());
                String cur=searchClassTextField.getText();
                int selected=searchClassCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(classTableData,selected,cur);
                df.setDataVector(displayData,headerTable);
                classTable.setModel(df);
            }
        });
        refreshStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                studentTableData=StudentDAO.convertToObject(new StudentDAO().getListObjects());
                String cur=searchStudentTextField.getText();
                int selected=searchStudentCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(studentTableData,selected,cur);
                df2.setDataVector(displayData,headerTable2);
                studenTable.setModel(df2);
            }
        });

        //set up text field
        searchClassTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchClassTextField.getText();
                int selected=searchClassCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(classTableData,selected,cur);
                df.setDataVector(dataDisplay,headerTable);
                classTable.setModel(df);
                mainframe.setVisible(true);
            }
        });

        searchStudentTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchStudentTextField.getText();
                int selected=searchStudentCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(studentTableData,selected,cur);
                String[] header={"StudentID","Name","Gender","ClassID","UserID"};
                df2.setDataVector(dataDisplay,header);
                studenTable.setModel(df2);
                mainframe.setVisible(true);
            }
        });

        classChooseCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                Object[][] currentData=handleData.filterData(studentTableData,searchStudentCombo.getSelectedIndex(),searchStudentTextField.getText());
                Object[][] displayData=handleData.getDataWithValue(currentData,3,classChooseCombo.getSelectedItem());

                df2.setDataVector(displayData,headerTable2);
                studenTable.setModel(df2);
                mainframe.setVisible(true);
            }
        });



        //add to current
        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
        mainframe.setVisible(true);
    }

    public void setUpCourseDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"SubjectID","Course class","Shift","Teacher","Total"};


        DefaultTableModel df=new DefaultTableModel(courseTableData,headerTable);

        JTable courseTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        courseTable.setAutoCreateRowSorter(true);
        courseTable.setRowHeight(25);

        JScrollPane accountScroll=new JScrollPane(courseTable);
        accountScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        accountScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));

        //set up top panel
        JPanel topPanel=new JPanel();
        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        topPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(null);

        JLabel searchLabel=new JLabel("Search: ");
        searchLabel.setBounds(30,50,100,30);
        JComboBox searchCombo=new JComboBox(headerTable);
        searchCombo.setBounds(130,50,100,30);
        JTextField searchTextField=new JTextField();
        searchTextField.setBounds(230,50,350,30);
        JButton refreshButton=new JButton("Refresh");
        refreshButton.setBounds(650,50,100,30);

        topPanel.add(searchLabel);
        topPanel.add(searchCombo);
        topPanel.add(searchTextField);
        topPanel.add(refreshButton);

        //set up bottom panel
        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

        //set up edit Panel
        JPanel editPanel=new JPanel();
        editPanel.setPreferredSize(new Dimension(100,accountScroll.getHeight()));
        editPanel.setMaximumSize(new Dimension(100,accountScroll.getHeight()));
        editPanel.setBackground(Color.BLUE);
        JButton addButton=new JButton("ADD");
        JButton editButton=new JButton("EDIT");
        JButton removeButton=new JButton("REMOVE");
        editPanel.setLayout(new GridLayout(12,1,10,10));
        editPanel.add(addButton);
        editPanel.add(editButton);
        editPanel.add(removeButton);

        //set up button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=courseTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,CourseOpenDAO.class);

                        courseTableData=handleData.removeObject(courseTableData,handleData.getIndexObject(courseTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df.setDataVector(displayData,headerTable);
                       courseTable.setModel(df);
                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected=courseTable.getSelectedRow();
                }

            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=courseTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[][] displayData=handleData.toArray(df.getDataVector());
                    Object[] rowData=displayData[selected];

                    editActionHanle.setUpEdit(headerTable,rowData,"Course");
                }
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] header={"SubjectID","Class","Begin Shift","End Shift",
                        "DIW","Teacher","Max Total"};
                Object[] dataSample={"String","String",Integer.valueOf(1),Integer.valueOf(1),
                        Integer.valueOf(1),"String",Integer.valueOf(1)};
                addActionHandle.setUpAdd(header,dataSample,"CourseOpen");
            }
        });

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
               courseTableData=CourseOpenDAO.convertToObject(new CourseOpenDAO().getListObjects());

                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(courseTableData,selected,cur);
                df.setDataVector(displayData,headerTable);
                courseTable.setModel(df);
            }
        });

        //set up textfield
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchTextField.getText();
                int selected=searchCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(courseTableData,selected,cur);
                df.setDataVector(dataDisplay,headerTable);
                courseTable.setModel(df);
                mainframe.setVisible(true);
            }
        });

        //add to current
        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpSemesterDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"ID","Name","Year","Date begin","Date end","Is current year"};

        //set up Table
        DefaultTableModel df=new DefaultTableModel(semesterTableData,headerTable);
        JTable semesterTable=new JTable(df)
        {


            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        String headerTable2[]={"SemsesID","SemesterID","Date begin","Date end"};
        DefaultTableModel df2=new DefaultTableModel(semsesTableData,headerTable2);
        JTable semsesTable=new JTable(df2)
        {



            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        semesterTable.setAutoCreateRowSorter(true);
        semesterTable.setRowHeight(25);

        //set up empty Panel
        JPanel emptyPanel =new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

        //set up scrollpane
        JScrollPane semesterScroll=new JScrollPane(semesterTable);

        JScrollPane semesterSessionScroll=new JScrollPane(semsesTable);

        //set up semester Panel
        JPanel semesterPanel =new JPanel();
        semesterPanel.setSize(new Dimension(currentPanel.getWidth(),200));
        semesterPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),250));
        semesterPanel.setLayout(new BorderLayout());

        JPanel funcSemesterPanel=new JPanel();
        funcSemesterPanel.setPreferredSize(new Dimension(currentPanel.getWidth()-100,50));
        funcSemesterPanel.setMaximumSize(new Dimension(currentPanel.getWidth()-100,50));
        funcSemesterPanel.setLayout(null);

        JLabel searchSemesterLabel=new JLabel("Search: ");
        searchSemesterLabel.setBounds(10,10,100,30);
        JComboBox searchSemesterCombo=new JComboBox(headerTable);
        searchSemesterCombo.setBounds(110,10,100,30);
        searchSemesterCombo.setBackground(Color.WHITE);
        JTextField searchSemesterTextField=new JTextField();
        searchSemesterTextField.setBounds(210,10,350,30);
        JButton refreshSemesterButton=new JButton("Refresh");
        refreshSemesterButton.setBounds(680,10,100,30);

        funcSemesterPanel.add(searchSemesterLabel);
        funcSemesterPanel.add(searchSemesterCombo);
        funcSemesterPanel.add(searchSemesterTextField);
        funcSemesterPanel.add(refreshSemesterButton);

        JPanel editSemesterPanel=new JPanel();
        editSemesterPanel.setPreferredSize(new Dimension(100,semesterPanel.getHeight()));
        editSemesterPanel.setBackground(Color.BLUE);
        editSemesterPanel.setLayout(new GridLayout(5,1,10,10));

        JButton addSemesterButton=new JButton("Add");
        JButton editSemesterButton=new JButton("Edit");
        JButton removeSemesterButton=new JButton("Remove");

        editSemesterPanel.add(addSemesterButton);
        editSemesterPanel.add(editSemesterButton);
        editSemesterPanel.add(removeSemesterButton);

        JPanel bottomSemesterPanel=new JPanel();
        bottomSemesterPanel.setPreferredSize(new Dimension(semesterPanel.getWidth()-100,80));
        bottomSemesterPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        bottomSemesterPanel.setLayout(null);

        JLabel setSemesterLabel=new JLabel("Set current semester:");
        setSemesterLabel.setBounds(10,10,200,50);
        String[] idData={"Test1","Test2"};
        JComboBox semesterIDCombo=new JComboBox(handleData.getDataColumn(semesterTableData,0));
        semesterIDCombo.setBounds(210,20,100,30);
        JButton setButton=new JButton("Set");
        setButton.setBounds(310,20,100,30);

        setButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String current=(String)semesterIDCombo.getSelectedItem();
                editActionHanle.setCurrentSemester(semesterTableData,current);
                JOptionPane.showMessageDialog(null,"The current semsemter: "+current);
                //update course display
                courseTableData=handleData.getDataWithValue(orginCourseData,5,getCurrentSemester());
            }
        });

        bottomSemesterPanel.add(setSemesterLabel);
        bottomSemesterPanel.add(semesterIDCombo);
        bottomSemesterPanel.add(setButton);

        semesterScroll.setPreferredSize(new Dimension(semesterPanel.getWidth()-100,semesterPanel.getHeight()-80));
        semesterPanel.add(funcSemesterPanel,BorderLayout.NORTH);
        semesterPanel.add(semesterScroll,BorderLayout.CENTER);
        semesterPanel.add(editSemesterPanel,BorderLayout.EAST);
        semesterPanel.add(bottomSemesterPanel,BorderLayout.SOUTH);

        //set up Semester Session Panel
        JPanel semesterSessionPanel=new JPanel();
        semesterSessionPanel.setSize(new Dimension(currentPanel.getWidth(),200));
        semesterSessionPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),250));
        semesterSessionPanel.setLayout(new BorderLayout());

        JPanel funcSemsesPanel=new JPanel();
        funcSemsesPanel.setPreferredSize(new Dimension(currentPanel.getWidth()-100,50));
        funcSemsesPanel.setMaximumSize(new Dimension(currentPanel.getWidth()-100,50));
        funcSemsesPanel.setLayout(null);

        JLabel searchSemsesLabel=new JLabel("Search: ");
        searchSemsesLabel.setBounds(10,10,100,30);
        JComboBox searchSemesCombo=new JComboBox(headerTable2);
        searchSemesCombo.setBounds(110,10,100,30);
        searchSemesCombo.setBackground(Color.WHITE);
        JTextField searchSemsesTextField=new JTextField();
        searchSemsesTextField.setBounds(210,10,350,30);
        JButton refreshSemsesButton=new JButton("Refresh");
        refreshSemsesButton.setBounds(680,10,100,30);

        funcSemsesPanel.add(searchSemsesLabel);
        funcSemsesPanel.add(searchSemesCombo);
        funcSemsesPanel.add(searchSemsesTextField);
        funcSemsesPanel.add(refreshSemsesButton);

        JPanel editSemsesPanel=new JPanel();
        editSemsesPanel.setPreferredSize(new Dimension(100,semesterPanel.getHeight()));
        editSemsesPanel.setBackground(Color.BLUE);
        editSemesterPanel.setLayout(new GridLayout(5,1,10,10));

        JButton addSemsesButton=new JButton("Add");
        JButton editSemsesButton=new JButton("Edit");
        JButton removeSemsesButton=new JButton("Remove");

        editSemsesPanel.add(addSemsesButton);
        editSemsesPanel.add(editSemsesButton);
        editSemsesPanel.add(removeSemsesButton);

        semesterSessionScroll.setPreferredSize(new Dimension(semesterSessionPanel.getWidth()-100,semesterSessionPanel.getHeight()-80));
        semesterSessionPanel.add(funcSemsesPanel,BorderLayout.NORTH);
        semesterSessionPanel.add(semesterSessionScroll,BorderLayout.CENTER);
        semesterSessionPanel.add(editSemsesPanel,BorderLayout.EAST);

        //set up centerPanel
        JPanel centerPanel=new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),500));

        centerPanel.add(semesterPanel,BorderLayout.NORTH);
        centerPanel.add(semesterSessionPanel,BorderLayout.CENTER);

        //set up button
        addSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[]header={"ID","Name","Year","Date begin","Date end"};

                    Object[] dataSample = new Object[0];

                    dataSample = new Object[]{"String",Integer.valueOf(1),Integer.valueOf(1),
                            "String","String"};


                addActionHandle.setUpAdd(header,dataSample,"Semester");
            }
        });
        addSemsesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] header={"SemsesID","SemesterID","Date begin","Date end"};
                Object[] dataSample={"String","String","String","String"};
                addActionHandle.setUpAdd(header,dataSample,"Semses");
            }
        });
        removeSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=semesterTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,SemesterDAO.class);

                        semesterTableData=handleData.removeObject(semesterTableData,handleData.getIndexObject(semesterTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df.setDataVector(displayData,headerTable);
                        semesterTable.setModel(df);
                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected=semesterTable.getSelectedRow();
                }

            }
        });

        removeSemsesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=semsesTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        Object[][]displayData=handleData.toArray(df2.getDataVector());
                        //TODO: delete in database
                        Object[]rowData=displayData[selected];
                        removeActionHanle.removeData(rowData,SemesterSessionDAO.class);

                        semsesTableData=handleData.removeObject(semsesTableData,handleData.getIndexObject(semsesTableData,rowData));
                        displayData=handleData.removeObject(displayData,selected);
                        df2.setDataVector(displayData,headerTable);
                        semsesTable.setModel(df2);
                    }
                    else if(choose==JOptionPane.NO_OPTION)
                    {
                        break;
                    }
                    selected=semsesTable.getSelectedRow();
                }

            }
        });
        editSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=semesterTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[][] displayData=handleData.toArray(df.getDataVector());
                    Object[] rowData=displayData[selected];

                    editActionHanle.setUpEdit(headerTable,rowData,"Semester");
                }
            }
        });
        editSemsesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=semsesTable.getSelectedRow();
                if(selected>=0)
                {
                    Object[][] displayData=handleData.toArray(df2.getDataVector());
                    Object[] rowData=displayData[selected];


                    editActionHanle.setUpEdit(headerTable2,rowData,"SemesterSession");
                }
            }
        });

        refreshSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
                semesterTableData=SemesterDAO.convertToObject(new SemesterDAO().getListObjects());
                String cur=searchSemesterTextField.getText();
                int selected=searchSemesterCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(semesterTableData,selected,cur);
                df.setDataVector(displayData,headerTable);
                semesterTable.setModel(df);
            }
        });

        refreshSemesterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //update base data base
                semsesTableData=SemesterSessionDAO.convertToObject(new SemesterSessionDAO().getListObjects());
                String cur=searchSemsesTextField.getText();
                int selected=searchSemesCombo.getSelectedIndex();
                Object[][]displayData=handleData.filterData(semsesTableData,selected,cur);
                df2.setDataVector(displayData,headerTable2);
                semsesTable.setModel(df2);
            }
        });

        //set up textfield
        searchSemesterTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchSemesterTextField.getText();
                int selected=searchSemesterCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(semesterTableData,selected,cur);
                df.setDataVector(dataDisplay,headerTable);
                semesterTable.setModel(df);
                mainframe.setVisible(true);
            }
        });

        searchSemsesTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String cur=searchSemsesTextField.getText();
                int selected=searchSemesCombo.getSelectedIndex();
                Object[][] dataDisplay= handleData.filterData(semsesTableData,selected,cur);
                df2.setDataVector(dataDisplay,headerTable2);
                semesterTable.setModel(df2);
                mainframe.setVisible(true);
            }
        });
        

        //add to current
        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);

    }

    public void setUpMoreDisplay(JPanel currentPanel,String idStudent)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        //set up data
        Object[][]data=StudentUI.getAllRegistedCourse(idStudent);
        StudentEntity student=new StudentDAO().getObject(idStudent);

        //set up panel

        JPanel topPanel=new JPanel();
        topPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        topPanel.setLayout(null);
        topPanel.setBackground(Color.ORANGE);
        JPanel centerPanel=new JPanel();
        centerPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),600));
        centerPanel.setLayout(new BorderLayout());
        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        bottomPanel.setBackground(Color.orange);

        //set up top panel
        JButton backToClassButton=new JButton("Back to Class");
        backToClassButton.setBounds(20,20,200,30);

        topPanel.add(backToClassButton);

        //set up center panel
        JPanel inforPanel=new JPanel();
        inforPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),200));
        inforPanel.setLayout(null);

        JLabel inforLabel=new JLabel("Information: ");
        JLabel idLabel=new JLabel("ID");
        JLabel nameLabel=new JLabel("Name");
        JLabel genderLabel=new JLabel("Gender");
        JLabel classLabel=new JLabel("Class:");
        JTextField idText=new JTextField();
        JTextField nameText=new JTextField();
        JTextField genderText=new JTextField();
        JTextField classText=new JTextField();

        idText.setText(student.getStudentid());
        nameText.setText(student.getStudentname());
        if(student.getGender()==true)
            genderText.setText("Female");
        else
            genderText.setText("Male");
        classText.setText(student.getClassid());

        inforLabel.setBounds(10,10,100,30);
        idLabel.setBounds(10,60,50,30);
        idText.setBounds(60,60,100,30);
        classLabel.setBounds(300,60,50,30);
        classText.setBounds(350,60,50,30);
        nameLabel.setBounds(10,110,50,30);
        nameText.setBounds(60,110,200,30);
        genderLabel.setBounds(300,110,50,30);
        genderText.setBounds(350,110,50,30);

        idText.setEnabled(false);
        classText.setEnabled(false);
        nameText.setEnabled(false);
        genderText.setEnabled(false);

        inforPanel.add(inforLabel);
        inforPanel.add(idLabel);
        inforPanel.add(idText);
        inforPanel.add(classLabel);
        inforPanel.add(classText);
        inforPanel.add(nameLabel);
        inforPanel.add(nameText);
        inforPanel.add(genderLabel);
        inforPanel.add(genderText);

        JPanel courseRegistPanel=new JPanel();
        courseRegistPanel.setPreferredSize(new Dimension(currentPanel.getWidth(), 400));
        courseRegistPanel.setLayout(new BorderLayout());

        String header[]={"SubjectID","Name","Course class","Credit","Shift","Teacher","Date registed"};
        MyDefaultTableModel df=new MyDefaultTableModel(data,header);
        JTable table=new JTable(df);
        JScrollPane tableScroll=new JScrollPane(table);
        courseRegistPanel.add(tableScroll);

        inforPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        courseRegistPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        centerPanel.add(inforPanel,BorderLayout.NORTH);
        centerPanel.add(courseRegistPanel,BorderLayout.CENTER);
        //set up button
        backToClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpClassDisplay(currentPanel);
            }
        });

        //add to current
        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);

    }

    public static String getCurrentSemester()
    {
        int len=semesterTableData.length;
        String ret="None";
        for(int i=0;i<len;i++)
        {
            if((Boolean) semesterTableData[i][5]==true)
                ret=(String) semesterTableData[i][0];
        }
        return ret;
    }

}


