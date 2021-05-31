package UI;

import javax.swing.*;
import java.awt.*;

public class MainUI
{
    private JFrame mainframe;
    public static final int _TEXTFIELD_WITDH= 200;
    public static final int _TEXTFIELD_HEIGHT =40;
    public static final String _FONT_STYLE ="Times New Roman";
    public MainUI()
    {
        mainframe=new JFrame("COURSE REGISTARION");
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainframe.setSize(new Dimension(800,600));
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setBackground(Color.WHITE);
        mainframe.setIconImage(new ImageIcon("resources/main_logo_crm.png").getImage());
        mainframe.setResizable(false);
        mainframe.setVisible(true);
    }

    public JFrame getMainframe()
    {
        return mainframe;
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
        acc_label.setBounds(50,80,100,_TEXTFIELD_HEIGHT);
        acc_label.setFont(new Font(_FONT_STYLE,Font.PLAIN,16));
        pass_label.setBounds(50,80+_TEXTFIELD_HEIGHT+10,100,_TEXTFIELD_HEIGHT);
        pass_label.setFont(new Font(_FONT_STYLE,Font.PLAIN,16));
        JTextField account=new JTextField();
        JTextField pass=new JTextField();
        account.setBounds(200,80,_TEXTFIELD_WITDH,_TEXTFIELD_HEIGHT);
        account.setFont(new Font(_FONT_STYLE,Font.BOLD,18));
        pass.setBounds(200,account.getY()+10+_TEXTFIELD_HEIGHT,_TEXTFIELD_WITDH,_TEXTFIELD_HEIGHT);
        pass.setFont(new Font(_FONT_STYLE,Font.BOLD,18));

        centerPanel.add(account);
        centerPanel.add(acc_label);
        centerPanel.add(pass);
        centerPanel.add(pass_label);
        centerPanel.setBorder(BorderFactory.createLineBorder( Color.BLACK));

        JButton signin=new JButton("SIGN IN");
        signin.setBounds(account.getX(),pass.getY()+20+_TEXTFIELD_HEIGHT,200,_TEXTFIELD_HEIGHT);
        signin.setBackground(new Color(10,130,180));
        signin.setForeground(Color.WHITE);
        signin.setFont(new Font(_FONT_STYLE,Font.BOLD,20));
        centerPanel.add(signin);

        loginPanel.add(topPanel,BorderLayout.NORTH);
        loginPanel.add(centerPanel,BorderLayout.CENTER);


        return loginPanel;
    }


}
