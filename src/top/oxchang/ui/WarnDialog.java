package top.oxchang.ui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WarnDialog extends JDialog {

	Font myFont = new Font("", 1, 15);
	Font warnFont = new Font("", 1, 22);
	JLabel myJLabel = new JLabel();

	public WarnDialog() {
		this.initFrame();
		this.initImage();

	}

	private void initFrame() {
		this.setTitle("警告:IP地址改变");
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
	}

	private void initImage() {
		myJLabel.setText("警告:IP地址改变");
		myJLabel.setBounds(100, 50, 200, 30);
		myJLabel.setFont(warnFont);

		this.getContentPane().add(myJLabel);

	}
}
