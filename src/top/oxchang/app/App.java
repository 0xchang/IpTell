package top.oxchang.app;

import top.oxchang.ui.MainFrame;
import top.oxchang.util.GenConfig;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��ʼ��config�ļ�
		GenConfig.genConf();

		// ��������
		MainFrame mf = new MainFrame();
	}

}
