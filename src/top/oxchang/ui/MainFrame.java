package top.oxchang.ui;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import top.oxchang.util.StartEvent;
import top.oxchang.util.SystemProxy;

public class MainFrame extends JFrame implements ActionListener {

	String testUrl;
	JLabel iptext = new JLabel("httpbin网址");
	JTextField ipField = new JTextField("https://ip.tool.lu/");
	JLabel proxytext = new JLabel("代理");
	JTextField proxyField = new JTextField();

	JButton startbtn = new JButton("运行");
	JButton exitbtn = new JButton("退出");
	JButton sysprobtn = new JButton("使用系统代理");

	Font myfont = new Font("", 0, 16);

	public MainFrame() {
		this.initFrame();
		this.initImage();

		this.setVisible(true);
	}

	public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	private void initFrame() {
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("IpTell   v1.0       by  0xchang");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initImage() {
		this.getContentPane().removeAll();

		// 初始按钮
		iptext.setBounds(50, 20, 80, 30);
		ipField.setBounds(150, 20, 220, 30);

		proxytext.setBounds(50, 60, 80, 30);
		proxyField.setBounds(150, 60, 220, 30);

		startbtn.setBounds(40, 120, 100, 30);
		exitbtn.setBounds(340, 120, 100, 30);
		sysprobtn.setBounds(150, 120, 180, 30);

		// 设置字体
		iptext.setFont(myfont);
		ipField.setFont(myfont);
		startbtn.setFont(myfont);
		exitbtn.setFont(myfont);
		proxyField.setFont(myfont);
		proxytext.setFont(myfont);
		sysprobtn.setFont(myfont);

		// 绑定事件
		this.initEvent();

		this.getContentPane().add(ipField);
		this.getContentPane().add(iptext);
		this.getContentPane().add(startbtn);
		this.getContentPane().add(exitbtn);
		this.getContentPane().add(proxyField);
		this.getContentPane().add(proxytext);
		this.getContentPane().add(sysprobtn);

		this.getContentPane().repaint();
	}

	private void initEvent() {

		startbtn.addActionListener(this);
		exitbtn.addActionListener(this);
		sysprobtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();
		if (obj == sysprobtn) {
			SystemProxy.GetSysProxy();

			sysprobtn.setText("系统代理使用中");
		}
		if (obj == exitbtn) {
			System.exit(0);
		}
		if (obj == startbtn) {
			// 最小化图标
			TrayIcon trayIcon = null;
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray();
				Image image = Toolkit.getDefaultToolkit().getImage("image/tray.jpg");
				trayIcon = new TrayIcon(image);
				trayIcon.addMouseListener(new TrayMouseListener(this));
				try {
					tray.add(trayIcon);
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			// 执行任务
			testUrl = ipField.getText();
			StartEvent s = new StartEvent();
			s.setMyproxy(proxyField.getText());
			s.setIpaddr(testUrl);
			s.start();

			startbtn.setText("运行中");

			this.setVisible(false);
		}
	}
}

class TrayMouseListener implements MouseListener {

	MainFrame mf;

	public TrayMouseListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mf.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
