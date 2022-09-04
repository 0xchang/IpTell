package top.oxchang.app;

import top.oxchang.ui.MainFrame;
import top.oxchang.util.GenConfig;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 初始化config文件
		GenConfig.genConf();

		// 创建窗口
		MainFrame mf = new MainFrame();
	}

}
