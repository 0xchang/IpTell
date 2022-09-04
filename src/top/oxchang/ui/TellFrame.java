package top.oxchang.ui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class TellFrame extends JDialog {

	JLabel jLabelipaddr;
	JLabel jLabelipaddr2;
	String ipaddr;
	String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	Font myfont = new Font("", 0, 18);

	public TellFrame(String ipaddr) {
		this.ipaddr = ipaddr;
		this.initFrame();
		this.initData();
		this.initImage();
		this.setVisible(false);
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	private void initData() {
		this.jLabelipaddr = new JLabel(this.ipaddr, JLabel.CENTER);
		this.jLabelipaddr2 = new JLabel(this.addr, JLabel.CENTER);

		this.jLabelipaddr.setBounds(0, 10, 220, 15);
		this.jLabelipaddr2.setBounds(1, 50, 220, 15);

		jLabelipaddr.setFont(myfont);
		jLabelipaddr2.setFont(myfont);

	}

	private void initFrame() {
		this.setSize(220, 130);
		this.setLocation(1500, 100);
		this.setTitle("IP��ַ");
		this.setAlwaysOnTop(true);

	}

	private void initImage() {
		this.getContentPane().removeAll();

		this.getContentPane().add(this.jLabelipaddr);
		this.getContentPane().add(this.jLabelipaddr2);

		this.getContentPane().repaint();
	}

	public void Show() {
		jLabelipaddr.setText(this.ipaddr);
		jLabelipaddr2.setText(this.addr);
		this.initImage();
		this.setVisible(true);
	}

	public void Hide() {
		this.setVisible(false);
	}

}
