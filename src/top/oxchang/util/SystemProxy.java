package top.oxchang.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

public class SystemProxy {
	private SystemProxy() {

	}

	public static String GetSysProxy() {
		try {
			System.setProperty("java.net.useSystemProxies", "true");
			List<Proxy> l = ProxySelector.getDefault().select(new URI("http://www.baidu.com/"));

			for (Iterator<Proxy> iter = l.iterator(); iter.hasNext();) {
				Proxy proxy = iter.next();
				System.out.println("proxy hostname : " + proxy.type());
				InetSocketAddress addr = (InetSocketAddress) proxy.address();

				if (addr == null) {
					System.out.println("No Proxy");
					return null;
				} else {
					System.out.println("proxy hostname : " + addr.getHostName());
					System.out.println("proxy port : " + addr.getPort());
					return addr.getHostName() + ":" + addr.getPort();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
