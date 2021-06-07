package UI;

import DAO.CRMuserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import HibernateUtil.hibernateUtil;

public class MainUI
{
    private JFrame mainframe;
    public static final int _TEXTFIELD_WITDH= 200;
    public static final int _TEXTFIELD_HEIGHT =40;
    public static final String _FONT_STYLE ="Times New Roman";
    private static ImageIcon logoImage;
    public MainUI()
    {
        mainframe=new JFrame("COURSE REGISTARION");
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainframe.setSize(new Dimension(800,600));
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setBackground(Color.WHITE);
        mainframe.setIconImage(new ImageIcon("image/main_logo_crm.png").getImage());
        mainframe.setResizable(false);
        mainframe.setVisible(true);

        logoImage=new ImageIcon("image/half_logo.png");
        Image img=logoImage.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        logoImage.setImage(img);
    }

    public JFrame getMainframe()
    {
        return mainframe;
    }
    public void setMainframe(JFrame anotherFrame)
    {
        mainframe=anotherFrame;
    }

    public void setUpMain()
    {
        mainframe.setVisible(true);
        JPanel mainPanel=new JPanel();

        mainframe.setLayout(null);
        mainPanel=setUpLogin();
        mainPanel.setBounds(100,50,600,400);
        mainframe.add(mainPanel);


        mainframe.setVisible(true);
        //initialize empty session
        hibernateUtil init=new hibernateUtil();
    }

    public JPanel setUpLogin()
    {
        JPanel loginPanel=new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setPreferredSize(new Dimension(600,400));
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel topPanel=new JPanel();
        topPanel.setSize(loginPanel.getWidth(),150);
        topPanel.setBackground(Color.WHITE);
        JLabel info=new JLabel("WELCOME TO CRM");
        info.setFont(new Font(_FONT_STYLE,Font.BOLD,30));
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setForeground(Color.WHITE);
        topPanel.add(info);
        topPanel.setBackground(new Color(10,130,180));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel centerPanel=new JPanel();
        centerPanel.setMaximumSize(new Dimension(loginPanel.getWidth(),150));
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);
        JLabel acc_label=new JLabel("Account: ");
        JLabel pass_label=new JLabel("Password: ");
        acc_label.setBounds(40,80,100,_TEXTFIELD_HEIGHT);
        acc_label.setFont(new Font(_FONT_STYLE,Font.PLAIN,16));
        pass_label.setBounds(40,80+_TEXTFIELD_HEIGHT+10,100,_TEXTFIELD_HEIGHT);
        pass_label.setFont(new Font(_FONT_STYLE,Font.PLAIN,16));
        JTextField account=new JTextField();
        JTextField pass=new JTextField();
        account.setBounds(150,80,_TEXTFIELD_WITDH,_TEXTFIELD_HEIGHT);
        account.setFont(new Font(_FONT_STYLE,Font.BOLD,18));
        pass.setBounds(150,account.getY()+10+_TEXTFIELD_HEIGHT,_TEXTFIELD_WITDH,_TEXTFIELD_HEIGHT);
        pass.setFont(new Font(_FONT_STYLE,Font.BOLD,18));
        JLabel logoLabel=new JLabel(logoImage);
        logoLabel.setBounds(350,60,200,200);

        centerPanel.add(account);
        centerPanel.add(acc_label);
        centerPanel.add(pass);
        centerPanel.add(pass_label);
        centerPanel.add(logoLabel);
        centerPanel.setBorder(BorderFactory.createLineBorder( Color.BLACK));

        JButton signin=new JButton("SIGN IN");
        signin.setBounds(account.getX(),pass.getY()+20+_TEXTFIELD_HEIGHT,200,_TEXTFIELD_HEIGHT);
        signin.setBackground(new Color(10,130,180));
        signin.setForeground(Color.WHITE);
        signin.setFont(new Font(_FONT_STYLE,Font.BOLD,20));



        centerPanel.add(signin);

        loginPanel.add(topPanel,BorderLayout.NORTH);
        loginPanel.add(centerPanel,BorderLayout.CENTER);
        signin.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                String acc=account.getText();
                String pas=pass.getText();
                if(acc.isEmpty()||pas.isEmpty())
                    JOptionPane.showMessageDialog(null,"Fill in account and password to sign in!");
                else
                {
                    CRMuserDAO crMuserDAO=new CRMuserDAO();
                    int check=crMuserDAO.checkAccount(acc,pas);

                    switch (check)

                    {
                        case 0:
                            JOptionPane.showMessageDialog(null,"Password is not correct!");
                            break;
                        case -1:
                            JOptionPane.showMessageDialog(null,"Account is not existed! Please contact with Academic Faculty to get account!");
                            break;
                        case 1:
                            StudentUI studentUI=new StudentUI(acc,pas);

                            mainframe.dispose();


                            studentUI.setUpDisplay();
                            break;
                        default:
                            AcademicUI academicUI=new AcademicUI();
                            mainframe.dispose();
                            academicUI.setUpDisplay();
                    }


                }
            }
        });

        return loginPanel;
    }


}
