package top.oxchang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeDecode {
	private UnicodeDecode() {

	}

	public static String decode(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
		Matcher matcher = pattern.matcher(str);

		// 迭代，将str中的所有unicode转换为正常字符
		while (matcher.find()) {
			String unicodeFull = matcher.group(1); // 匹配出的每个字的unicode，比如\u67e5
			String unicodeNum = matcher.group(2); // 匹配出每个字的数字，比如\u67e5，会匹配出67e5

			// 将匹配出的数字按照16进制转换为10进制，转换为char类型，就是对应的正常字符了
			char singleChar = (char) Integer.parseInt(unicodeNum, 16);

			// 替换原始字符串中的unicode码
			str = str.replace(unicodeFull, singleChar + "");
		}
		return str;
	}
}
