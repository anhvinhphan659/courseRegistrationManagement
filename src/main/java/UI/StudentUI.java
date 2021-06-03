package UI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
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
        JTable courseTable=new JTable(df) {
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

        JTable resultTable=new JTable(df) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return column == 5;
            }
        };

        //set up bottomPanel
        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(currentPanel.getWidth(),100));
        bottomPanel.setMaximumSize(new Dimension(currentPanel.getWidth(),150));
        bottomPanel.setBackground(Color.ORANGE);

        //set up scroll
        courseTable.setAutoCreateRowSorter(true);
        courseTable.setRowHeight(25);


        JScrollPane courseOpenScroll=new JScrollPane(courseTable);
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

        JLabel resultAnnounceLabel=new JLabel("Course open this semester:");
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
        centerPanel.setBackground(Color.YELLOW);
        centerPanel.setLayout(null);



//        currentPanel.setLayout(null);
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

        centerPanel.add(changpassLabel);
        centerPanel.add(oldpasswordLabel);
        centerPanel.add(oldpassTextField);
        centerPanel.add(newpasswodLabel);
        centerPanel.add(newpassTextField);
        centerPanel.add(myaccountLabel);

        currentPanel.add(topPanel,BorderLayout.NORTH);
        currentPanel.add(centerPanel,BorderLayout.CENTER);
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);
        System.out.println("Finish setting account!");
//

//
//        System.out.println("Set up My Account");
    }
}



