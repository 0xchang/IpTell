package top.oxchang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeDecode {
	private UnicodeDecode() {

	}

	public static String decode(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
		Matcher matcher = pattern.matcher(str);

		// ��������str�е�����unicodeת��Ϊ�����ַ�
		while (matcher.find()) {
			String unicodeFull = matcher.group(1); // ƥ�����ÿ���ֵ�unicode������\u67e5
			String unicodeNum = matcher.group(2); // ƥ���ÿ���ֵ����֣�����\u67e5����ƥ���67e5

			// ��ƥ��������ְ���16����ת��Ϊ10���ƣ�ת��Ϊchar���ͣ����Ƕ�Ӧ�������ַ���
			char singleChar = (char) Integer.parseInt(unicodeNum, 16);

			// �滻ԭʼ�ַ����е�unicode��
			str = str.replace(unicodeFull, singleChar + "");
		}
		return str;
	}
}
