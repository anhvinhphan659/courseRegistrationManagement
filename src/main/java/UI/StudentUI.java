package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView;
import java.awt.*;

public class StudentUI
{
    private JFrame mainframe;
    public StudentUI()
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
        JButton courseopen=new JButton("Course open");
        JButton courseregist=new JButton("Course regist");
        JButton exitButton=new JButton("Exit");
        JLabel emptylabel1=new JLabel();
        JLabel emptylabel2=new JLabel();
        JLabel emptylabel3=new JLabel();
        JLabel emptylabel4=new JLabel();
        JLabel emptylabel5=new JLabel();
        JLabel emptylabel6=new JLabel();

        courseopen.setForeground(Color.WHITE);
        courseopen.setBackground(new Color(55,65,55));
        courseregist.setForeground(Color.WHITE);
        courseregist.setBackground(new Color(55,65,55));
        exitButton.setBackground(new Color(55,65,55));
        exitButton.setForeground(Color.white);
        leftPanel.add(courseopen);
        leftPanel.add(courseregist);

        leftPanel.add(emptylabel1);
        leftPanel.add(emptylabel2);
        leftPanel.add(emptylabel3);
        leftPanel.add(emptylabel4);
        leftPanel.add(emptylabel5);
        leftPanel.add(exitButton);
        //set up center panel
        JPanel centerPanel=new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(800,800));

        //setup for mainframe

        mainframe.add(leftPanel, BorderLayout.WEST);
        setUpCourseRegist(centerPanel);
        mainframe.add(centerPanel,BorderLayout.CENTER);
        mainframe.setVisible(true);
    }

//    public void setUpCourseOpen(JPanel currentPanel)
//    {
//        currentPanel.removeAll();
//        currentPanel.setLayout(null);
//        String course[]={"Course ID","Course Name","Teacher","Credit","INT1 ","INT2"};
//        Object data[][]={{"test1","test12","test13","test14","test15","test16"},
//                {"test2","test21","test21","test21","test21",new JButton("test")},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"},
//                {"test2","test21","test21","test21","test21","test21"}};
//        JTable table=new JTable(data,course);
//        table.setAutoCreateRowSorter(true);
//
//
//        table.setRowHeight(25);
//        JScrollPane scrollPane=new JScrollPane(table);
//        scrollPane.setBounds(20,60,600,300);
//
//        scrollPane.setBorder(new EtchedBorder(EtchedBorder.RAISED));
//
//        JLabel currentSemester =new JLabel("Semester: ");
//        JLabel semester =new JLabel("3");
//        JLabel currentYear=new JLabel("Year: ");
//        JLabel year=new JLabel("2021");
//        currentSemester.setBounds(20,20,80,30);
//        semester.setBounds(100,20,30,30);
//        currentYear.setBounds(120,20,60,30);
//        year.setBounds(180,20,30,30);
//        table.setFillsViewportHeight(true);
//        currentPanel.add(scrollPane);
//        currentPanel.add(currentSemester);
//        currentPanel.add(semester);
//        currentPanel.add(currentYear);
//        currentPanel.add(year);
//
//    }

    public void setUpCourseRegist(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(null);
        String course[]={"Course ID","Course Name","Teacher","Credit","INT1 ","INT2"};
        Object data[][]={{"test1","test12","test13","test14","test15",false},
                {"test2","test21","test21","test21","test21", false},
                {"test2","test21","test21","test21","test21",true},
                {"test2","test21","test21","test21","test21",false},
                {"test2","test21","test21","test21","test21",false},
                {"test2","test21","test21","test21","test21",false},
                {"test2","test21","test21","test21","test21",false},
                {"test2","test21","test21","test21","test21",false},
                {"test2","test21","test21","test21","test21",false}};


        DefaultTableModel df=new DefaultTableModel(data,course);
        JTable courseOpen=new JTable(df) {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                return data[0][columnIndex].getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return column==5;
            }
        } ;

        courseOpen.setAutoCreateRowSorter(true);

        courseOpen.setRowHeight(25);
        JScrollPane courseOpenScroll=new JScrollPane(courseOpen);
        courseOpenScroll.setBounds(20,60,600,120);
        courseOpenScroll.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        JTable courseRegist =new JTable();
        courseRegist.setAutoCreateRowSorter(true);
        courseRegist.setRowHeight(25);
        JScrollPane courseRegistScroll=new JScrollPane(courseRegist);
        courseRegistScroll.setBounds(20,300,600,200);
        courseRegistScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED));


        currentPanel.add(courseOpenScroll);
        currentPanel.add(courseRegistScroll);
    }
}



