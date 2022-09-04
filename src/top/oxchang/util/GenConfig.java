package top.oxchang.util;

import java.io.File;
import java.io.IOException;

public class GenConfig {
	private GenConfig() {

	}

	private static final String dataDir = "data/";
	private static final String dataFile = "config.txt";

	public static void genConf() {
		File dir = new File(dataDir);
		File config = new File(dataDir + dataFile);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
