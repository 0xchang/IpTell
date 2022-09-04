package top.oxchang.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfig {
	private ReadConfig() {

	}

	public static String getUrl() throws IOException {
		FileReader file = new FileReader("data/config.txt");
		BufferedReader br = new BufferedReader(file);
		String line = br.readLine();
		br.close();
		file.close();
		return line;
	}

}
