package UI;

import javax.swing.*;
import DAO.*;
import POJO.CrmuserEntity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AcademicUI
{
    private JFrame mainframe;
    private Object[][] courseTableData;
    private Object[][] userTableData;
    private Object[][] subjectTableData;
    private Object[][] classTableData;

    public AcademicUI()
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");

        CRMuserDAO crMuserDAO=new CRMuserDAO();
        CRMclassDAO crMclassDAO=new CRMclassDAO();
        SubjectDAO subjectDAO=new SubjectDAO();
        CourseOpenDAO courseOpenDAO=new CourseOpenDAO();

        userTableData=CRMuserDAO.convertToObject(crMuserDAO.getListObjects());
        classTableData=CRMclassDAO.convertToObject(crMclassDAO.getListObjects());
        subjectTableData=SubjectDAO.convertToObject(subjectDAO.getListObjects());
        courseTableData=CourseOpenDAO.convertToObject(courseOpenDAO.getListObjects());

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
                return column==userTableData[0].length-1;
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

        //set up button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=accountTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        df.removeRow(selected);
                        accountTable.setModel(df);
                        //TODO: delete in database

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
                if(selected>=0)
                    setUpEdit(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAdd(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
            }
        });
        
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

        JPanel emptyPanel=new JPanel();
        emptyPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel.setBackground(Color.ORANGE);

        JPanel emptyPanel2=new JPanel();
        emptyPanel2.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        emptyPanel2.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        emptyPanel2.setBackground(Color.ORANGE);

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
                        df.removeRow(selected);
                        subjectTable.setModel(df);
                        //TODO: delete in database

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
                    setUpEdit(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAdd(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
            }
        });

        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(subjectScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpClassDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(new BorderLayout());

        String headerTable[]={"ClassID","Male","Female","Total","SchoolYear"};

        DefaultTableModel df=new DefaultTableModel(classTableData,headerTable);
        JTable classTable=new JTable(df)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        classTable.setAutoCreateRowSorter(true);
        classTable.setRowHeight(25);

        JScrollPane accountScroll=new JScrollPane(classTable);
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

        //set up button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=classTable.getSelectedRow();
                while (selected>=0)
                {
                    int choose=JOptionPane.showConfirmDialog(null,"Do you want to delete selected row","Delete row",JOptionPane.YES_NO_OPTION);
                    if(choose==JOptionPane.YES_OPTION) {
                        df.removeRow(selected);
                        classTable.setModel(df);
                        //TODO: delete in database

                    }
                    selected=classTable.getSelectedRow();
                }

            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selected=classTable.getSelectedRow();
                if(selected>=0)
                    setUpEdit(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAdd(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
            }
        });

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
                        df.removeRow(selected);
                        courseTable.setModel(df);
                        //TODO: delete in database

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
                    setUpEdit(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
                mainframe.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setUpAdd(new String[]{"TEST1","Tets2"},new Object[]{"data1","data2"},"subject");
            }
        });
        
        currentPanel.add(emptyPanel,BorderLayout.NORTH);
        currentPanel.add(accountScroll,BorderLayout.CENTER);
        currentPanel.add(editPanel,BorderLayout.EAST);
        currentPanel.add(emptyPanel2,BorderLayout.SOUTH);
    }

    public void setUpEdit(String[]labelData,Object[]data,String CMD)
    {
        System.out.println("Setting up edit");
        JFrame editFrame=new JFrame("Edit "+CMD);
        editFrame.setDefaultLookAndFeelDecorated(true);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setSize(new Dimension(400,200));
        editFrame.setPreferredSize(new Dimension(400,200));


        int len=data.length;

        JPanel editPanel=new JPanel();
        editPanel.setPreferredSize(new Dimension(300,200));
        editPanel.setLayout(new GridLayout(2,len));
        for(int i=0;i<len;i++)
        {
            JLabel curLabel=new JLabel(labelData[i]);
            curLabel.setHorizontalAlignment(JLabel.CENTER);
            editPanel.add(curLabel);
        }
        for(int i=0;i<len;i++)
        {
            JTextField curTextField=new JTextField();
            curTextField.setText((String) data[i]);
            editPanel.add(curTextField);
        }

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
                //TODO: do sth with database
            }
        });

        editFrame.setVisible(true);
    }

    public void setUpAdd(String[]labelData,Object[]data,String CMD)
    {
        System.out.println("Setting up add"+CMD);
        JFrame addFrame=new JFrame("Add "+CMD);
        addFrame.setDefaultLookAndFeelDecorated(true);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setSize(new Dimension(400,200));
        addFrame.setPreferredSize(new Dimension(400,200));


        int len=data.length;

        JPanel addPanel=new JPanel();
        addPanel.setPreferredSize(new Dimension(300,200));
        addPanel.setLayout(new GridLayout(2,len));
        for(int i=0;i<len;i++)
        {
            JLabel curLabel=new JLabel(labelData[i]);
            curLabel.setHorizontalAlignment(JLabel.CENTER);
            addPanel.add(curLabel);
        }
        for(int i=0;i<len;i++)
        {
            JTextField curTextField=new JTextField();
            curTextField.setText((String) data[i]);
            addPanel.add(curTextField);
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
                //TODO: do sth with database
            }
        });

        addFrame.setVisible(true);
    }

}
