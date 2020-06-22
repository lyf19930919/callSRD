package ui;

import com.alibaba.fastjson.JSONObject;
import entity.TransportOrder;
import mainCall.OrderOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JsonUtil;
import util.RandomNum;
import util.StringMatch;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


class OperationUI extends JFrame {

    private JPanel contentPane;
    private static final Logger log = LoggerFactory.getLogger(OperationUI.class);
    private JTextField waitTime;
    private TransportOrder transportOrder;
    private JTextField roborouteIp;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OperationUI frame = new OperationUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public OperationUI() {

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowsWedth = 900;
        int windowsHeight = 600;
        setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel tabIp = new JLabel("调度服务器地址");
        Font f1 = new Font("宋体", Font.PLAIN, 28);
        tabIp.setFont(f1);
        tabIp.setForeground(Color.red);
        tabIp.setBounds(430, 30, 280, 80);
        contentPane.add(tabIp);

        roborouteIp = new JTextField();
        roborouteIp.setBounds(500, 100, 80, 38);
        contentPane.add(roborouteIp);


        JLabel tab = new JLabel("起始等待时间(ms)");
        Font f = new Font("宋体", Font.PLAIN, 28);
        tab.setFont(f);
        tab.setForeground(Color.red);
        tab.setBounds(430, 150, 280, 80);
        contentPane.add(tab);

        waitTime = new JTextField();
        waitTime.setBounds(500, 222, 80, 38);
        contentPane.add(waitTime);

        JButton jb1 = new JButton("下料产线一");//产线一按钮
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ConditionMatch("LOC-AP303");
            }
        });
        jb1.setBounds(126, 88, 100, 62);
        contentPane.add(jb1);

        JButton jb2 = new JButton("下料产线二");
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ConditionMatch("LOC-AP304");
            }
        });
        jb2.setBounds(126, 210, 100, 62);
        contentPane.add(jb2);

        JButton jb3 = new JButton("下料产线三");
        jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ConditionMatch("LOC-AP305");
            }
        });
        jb3.setBounds(126, 320, 100, 62);
        contentPane.add(jb3);


        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        URL resource = this.getClass().getResource("/seerOperation.jpg"); // 获取背景图片路径
        log.info("操作界面背景图的路径是--" + resource.getPath());
        ImageIcon icon = new ImageIcon(resource); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, width, height); // 设置组件的显示位置及大小
        contentPane.add(lblBackground); // 将组件添加到面板中

    }

    private void ConditionMatch(String locationName) {
        String waitTimeValue = waitTime.getText();
        String roborouteIdAddres = roborouteIp.getText();
//              修改json文件总中其实工位的等待时间
        if (!StringMatch.matchIp4Address(roborouteIdAddres, "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}")) {
            JOptionPane.showMessageDialog(contentPane, "请输入正确的IP地址", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!StringMatch.isNumeric(waitTimeValue)) {
            JOptionPane.showMessageDialog(contentPane, "请输入正确的时间格式", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        transportOrder = JsonUtil.updateOrderBody(waitTimeValue, locationName);
        OrderOperations.createOrderByPost(RandomNum.getRandomByNowtime(), transportOrder,roborouteIdAddres);
    }
}

