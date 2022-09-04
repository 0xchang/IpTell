package top.oxchang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.oxchang.ui.MainFrame;
import top.oxchang.ui.TellFrame;
import top.oxchang.ui.WarnDialog;

public class StartEvent implements Runnable {

	Thread t1;
	String ipaddr;
	String beforeip, afterip;
	String addr;
	URL url;
	URL iptoaddrurl;
	Pattern patternip = Pattern
			.compile("((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))");
	Pattern patterniptoaddr = Pattern.compile("(?<=(?:location\":\")).*?(?=(?:\",))");

	TellFrame tl;
	WarnDialog wd = new WarnDialog();
	String myproxy;
	String apiDefaultString = "https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=%s&resource_id=5809";
	boolean runflag = true;
	MainFrame mf;

	public MainFrame getMf() {
		return mf;
	}

	public void setMf(MainFrame mf) {
		this.mf = mf;
	}

	public TellFrame getTl() {
		return tl;
	}

	public void setTl(TellFrame tl) {
		this.tl = tl;
	}

	public String getMyproxy() {
		return myproxy;
	}

	public void setMyproxy(String myproxy) {
		this.myproxy = myproxy;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				afterip = this.Urlconn();
				this.Result();
				Thread.sleep(2500);
				if (!this.runflag) {
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.tl.dispose();
		System.out.println("线程结束");
	}

	public void start() {
		// TODO Auto-generated method stub
		System.out.println("线程开始");
		if (!this.myproxy.equals("")) {
			String[] res = myproxy.split(":");
			System.setProperty("http.proxyHost", res[0]);
			System.setProperty("http.proxyPort", res[1]);
			System.setProperty("https.proxyHost", res[0]);
			System.setProperty("https.proxyport", res[1]);

		}
		try {
			url = new URL(ipaddr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (t1 == null) {
			t1 = new Thread(this);
			t1.start();
		}
		// tl = new TellFrame(beforeip);
	}

	private String Urlconn() {
		// System.out.println("here urlconn");

		URLConnection conn = null;
		try {

			conn = url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.27 Safari/537.36 Edg/102.0.1245.7");
		} catch (IOException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.removeServiceProxy();
			br = null;
		}
		// System.out.println(br);
		String line;
		String result = null;

		if (br != null) {
			try {
				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					Matcher mip = patternip.matcher(line);
					Matcher maddr = patterniptoaddr.matcher(line);
					if (mip.find()) {
						result = mip.group();
					}
					if (maddr.find()) {
						String chiStr;
						chiStr = UnicodeDecode.decode(maddr.group());
						this.tl.setAddr(chiStr);
					}
				}
				// System.out.println(result + addr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}

		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return result;

	}

	private void removeServiceProxy() {
		System.getProperties().remove("http.proxyHost");
		System.getProperties().remove("http.proxyPort");
		System.getProperties().remove("https.proxyHost");
		System.getProperties().remove("https.proxyPort");
	}

	private boolean Result() {
		if (afterip == null) {
			// 如果连接异常导致，结束线程
			this.runflag = false;
			this.tl.setAddr("");
			this.tl.setIpaddr("");
			wd.setContent("无法连接到目标服务器");
			wd.myUpdate();
			wd.setVisible(true);

			// 修改主窗口属性值,弹出主窗口
			this.mf.getStartbtn().setText("运行");
			this.mf.setVisible(true);
		} else if (beforeip == null) {
			// 初始化ip
			beforeip = afterip;
			tl.setIpaddr(beforeip);
			tl.Show();
		} else if (!beforeip.equals(afterip)) {
			// 判断ip是否相等
			wd.setContent("IP地址发生改变");
			wd.myUpdate();
			wd.setVisible(true);
		}

		beforeip = afterip;
		tl.setIpaddr(beforeip);
		tl.Show();

		return true;
	}

}
