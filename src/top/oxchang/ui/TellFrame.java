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
		this.setVisible(true);
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	private void initData() {
		this.jLabelipaddr = new JLabel(this.ipaddr);
		this.jLabelipaddr2 = new JLabel(this.addr);

		this.jLabelipaddr.setBounds(20, 5, 200, 15);
		this.jLabelipaddr2.setBounds(20, 20, 200, 15);

		jLabelipaddr.setFont(myfont);
		jLabelipaddr2.setFont(myfont);

	}

	private void initFrame() {
		this.setSize(220, 110);
		this.setLocation(1500, 100);
		this.setTitle("IPµÿ÷∑");
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
	}

	public void Hide() {
		this.setVisible(false);
	}

}
