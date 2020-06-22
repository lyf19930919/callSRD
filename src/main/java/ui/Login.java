package ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

class Login extends JFrame {

    public static final Logger logger = LoggerFactory.getLogger(Logger.class);
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField jtf1;

    public Login() {
//        设置窗口可见
        this.setVisible(true);
        this.setTitle("仙知机器人下单登录");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowsWedth = 450;
        int windowsHeight = 300;
        setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel jlb1 = new JLabel("User");
        jlb1.setBounds(70, 59, 56, 44);
        contentPane.add(jlb1);

        JLabel jlb2 = new JLabel("Password");
        jlb2.setBounds(45, 125, 81, 35);
        contentPane.add(jlb2);

        passwordField = new JPasswordField();
        passwordField.setBounds(143, 129, 187, 31);
        contentPane.add(passwordField);

//        设置密码可见
        final JRadioButton radpass = new JRadioButton("可见");
        radpass.setBounds(335, 116, 60, 60);
        radpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (radpass.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        contentPane.add(radpass);

        jtf1 = new JTextField();
        jtf1.setBounds(143, 72, 186, 31);
        contentPane.add(jtf1);
        jtf1.setColumns(15);


        JButton jb1 = new JButton("登录");        //登录按钮
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String userName = jtf1.getText();
                String password = String.valueOf(passwordField.getPassword());
                logger.info("username:" + userName + "password:" + password);
                if ("dingxin".equals(userName) && "dingxin666".equals(password)) {
//                    setVisible(false);
                    logger.info("密码正确可进入操作界面");
                    OperationUI operationUI = new OperationUI();
                    operationUI.setVisible(true);
                    operationUI.setTitle("仙知远程呼叫系统");
//                    关闭当前界面
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "用户名或者密码错误", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        jb1.setBounds(168, 199, 93, 23);
        contentPane.add(jb1);

        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        URL resource = this.getClass().getResource("/seerRobot.png"); // 获取背景图片路径
        ImageIcon icon = new ImageIcon(resource); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(45, -60, windowsWedth,windowsHeight); // 设置组件的显示位置及大小
        contentPane.add(lblBackground); // 将组件添加到面板中

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

