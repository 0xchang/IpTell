package top.oxchang.ui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WarnDialog extends JDialog {

	Font myFont = new Font("", 1, 15);
	Font warnFont = new Font("", 1, 22);
	JLabel myJLabel = new JLabel();
	String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WarnDialog() {
		this.initFrame();
		this.initImage();

	}

	private void initFrame() {
		this.setTitle("¾¯¸æ!");
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
	}

	private void initImage() {
		this.getContentPane().removeAll();
		myJLabel.setText(this.content);
		myJLabel.setBounds(100, 50, 200, 30);
		myJLabel.setFont(warnFont);

		this.getContentPane().add(myJLabel);

		this.getContentPane().repaint();

	}

	public void myUpdate() {
		this.initImage();
	}
}
