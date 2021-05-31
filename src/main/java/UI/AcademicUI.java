package UI;

import javax.swing.*;
import DAO.*;
import POJO.CrmuserEntity;
import java.util.List;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AcademicUI
{
    private JFrame mainframe;
    public AcademicUI()
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");
    }

    public JFrame getMainframe()
    {
        return mainframe;
    }

    public void setUpDisplay()
    {
        //remove all old componet

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
        accountButton.setForeground(Color.white);
        subjectButton.setForeground(Color.white);
        classButton.setForeground(Color.white);
        courseButton.setForeground(Color.white);
        exitButton.setForeground(Color.white);
        accountButton.setFont(new Font("Open Sans",Font.BOLD,16));
        subjectButton.setFont(new Font("Open Sans",Font.BOLD,16));
        classButton.setFont(new Font("Open Sans",Font.BOLD,16));
        courseButton.setFont(new Font("Open Sans",Font.BOLD,16));
        exitButton.setFont(new Font("Open Sans",Font.BOLD,16   ));

        leftPanel.add(accountButton);
        leftPanel.add(subjectButton);
        leftPanel.add(classButton);
        leftPanel.add(courseButton);
        leftPanel.add(emptylabel1);
        leftPanel.add(emptylabel2);
        leftPanel.add(emptylabel3);
        leftPanel.add(exitButton);

        //set up center panel
        JPanel centerPanel=new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(800,800));

        //setup for mainframe
        mainframe.add(leftPanel, BorderLayout.WEST);
        setUpCourseDisplay(centerPanel);
        mainframe.add(centerPanel,BorderLayout.CENTER);
        mainframe.setVisible(true);
    }

    public void setUpAccountDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());



        String headerTable[]={"UserID","Account","Password","isAdmin"};
        CRMuserDAO crMuserDAO=new CRMuserDAO();
        Object[][] dataTable=CRMuserDAO.convertToObject(crMuserDAO.getListObject());


        DefaultTableModel df=new DefaultTableModel(dataTable,headerTable);
        JTable accountTable=new JTable(df) {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                return dataTable[0][columnIndex].getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return column==dataTable[0].length-1;
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

        JPanel emptyPanel=new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

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

        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpSubjectDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"SubjectID","Name","Credit","Faculty"};

        SubjectDAO subjectDAO=new SubjectDAO();
        Object[][] dataTable=SubjectDAO.convertToObject(subjectDAO.getListObject());

        DefaultTableModel df=new DefaultTableModel(dataTable,headerTable);
        JTable accountTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setRowHeight(25);

        JScrollPane accountScroll=new JScrollPane(accountTable);
        accountScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        accountScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));
//        accountTable.setSize(accountScroll.getWidth()-50,accountScroll.getHeight()-50);
//        accountScroll.setLayout(null);

        JPanel emptyPanel=new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

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

        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpClassDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"ClassID","Male","Female","Total","SchoolYear"};

        CRMclassDAO crMclassDAO=new CRMclassDAO();
        Object[][] dataTable=CRMclassDAO.convertToObject(crMclassDAO.getListObject());

        DefaultTableModel df=new DefaultTableModel(dataTable,headerTable);
        JTable accountTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setRowHeight(25);

        JScrollPane accountScroll=new JScrollPane(accountTable);
        accountScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        accountScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));
//        accountTable.setSize(accountScroll.getWidth()-50,accountScroll.getHeight()-50);
//        accountScroll.setLayout(null);

        JPanel emptyPanel=new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

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

        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpCourseDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"SubjectID","Course class","Shift","Teacher","Total"};

        CourseOpenDAO courseOpenDAO=new CourseOpenDAO();
        Object[][] dataTable=CourseOpenDAO.convertToObject(courseOpenDAO.getListObject());

        DefaultTableModel df=new DefaultTableModel(dataTable,headerTable);
        JTable accountTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setRowHeight(25);

        JScrollPane accountScroll=new JScrollPane(accountTable);
        accountScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        accountScroll.setPreferredSize(new Dimension(currentPanel.getWidth(),250));
//        accountTable.setSize(accountScroll.getWidth()-50,accountScroll.getHeight()-50);
//        accountScroll.setLayout(null);

        JPanel emptyPanel=new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

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

        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

}
