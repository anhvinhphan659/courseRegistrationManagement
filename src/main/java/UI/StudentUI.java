package UI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentUI
{
    private JFrame mainframe;
    private Object[][] coursetabledata;
    public StudentUI()
    {
        mainframe=new JFrame();
        mainframe.setTitle("Course Registration");
    }

    public StudentUI(JFrame currentFrame)
    {
        mainframe=currentFrame;

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
        JButton courseButton=new JButton("Course open");
        JButton myaccountButton=new JButton("MY ACCOUNT");
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



        //add component to panel
        leftPanel.add(courseButton);
        leftPanel.add(myaccountButton);
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
        setUpCourseRegistDisplay(centerPanel);

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
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainframe.dispose();
            }
        });
        mainframe.add(centerPanel,BorderLayout.CENTER);
        mainframe.setVisible(true);
    }

    public void setUpCourseRegistDisplay(JPanel currentPanel)
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

    public void setUpMyAccountDisplay(JPanel currentPanel)
    {
        currentPanel.removeAll();
        currentPanel.setLayout(null);
        JLabel myaccountLabel=new JLabel("My account 's information:");
        JLabel accountLabel=new JLabel("Account");
        JTextField accountTextField=new JTextField();
        //add some infor

        JLabel changpassLabel=new JLabel("Change password: ");
        JLabel oldpasswordLabel=new JLabel("Old password: ");
        JTextField oldpassTextField=new JTextField();
        JLabel newpasswodLabel=new JLabel("New password: ");
        JTextField newpassTextField=new JTextField();

        //set up label and textfield
        myaccountLabel.setBounds(20,20,200,50);
        accountLabel.setBounds(20,20+50,100,50);
        accountTextField.setBounds(20+100+20,
                20+50,100,50);
        changpassLabel.setBounds(20,250,100,50);
        oldpasswordLabel.setBounds(20,250+50,100,50);
        oldpassTextField.setBounds(20+ 100+20,250+50,100,50);
        newpasswodLabel.setBounds(20,300+50,100,50);
        newpassTextField.setBounds(20+ 100+20,300+50,100,50);

        currentPanel.add(myaccountLabel);
        currentPanel.add(accountLabel);
        currentPanel.add(accountTextField);
        currentPanel.add(changpassLabel);
        currentPanel.add(oldpasswordLabel);
        currentPanel.add(oldpassTextField);
        currentPanel.add(newpasswodLabel);
        currentPanel.add(newpassTextField);

        System.out.println("Set up My Account");
    }
}



