package com.hhh.fund.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
/**

 * 证书信任管理器（用于https请求）

 * 这个证书管理器的作用就是让它信任我们指定的证书，上面的代码意味着信任所有证书，不管是否权威机构颁发䷢

 * @author fws

 * @date 2014-1-22

 */
public class TrustAnyTrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub

	}

	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
