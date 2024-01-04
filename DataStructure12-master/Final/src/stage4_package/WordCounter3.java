package stage4_package;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WordCounter3
{
	private String urlStr;
	private String content;

	public WordCounter3(String urlStr)
	{
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException
	{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		if (conn instanceof HttpsURLConnection) {
		    ((HttpsURLConnection) conn).setSSLSocketFactory(getTrustingSSLSocketFactory());
		    ((HttpsURLConnection) conn).setHostnameVerifier((hostname, session) -> true);
		}
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String retVal = "";

		String line = null;

		while ((line = br.readLine()) != null)
		{
			retVal = retVal + line + "\n";
		}

		return retVal;
	}

	public int countKeyword(String keyword) throws IOException
	{
		if (content == null)
		{
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(keyword, fromIdx)) != -1)
		{
			retVal++;
			fromIdx = found + keyword.length();
		}

		return retVal;
	}
	
	private SSLSocketFactory getTrustingSSLSocketFactory() {
	    try {
	        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }

	            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
	            }

	            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
	            }
	        }};

	        SSLContext sslContext = SSLContext.getInstance("SSL");
	        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	        return sslContext.getSocketFactory();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
