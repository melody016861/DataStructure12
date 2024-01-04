package stage4_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import HW6_package.WebPage;

public class GoogleQuery1 
{
	public String searchKeyword;
	public String url;
	public String content;
	KeywordList1 lst = new KeywordList1();
	
	public GoogleQuery1(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		try 
		{
			// This part has been specially handled for Chinese keyword processing. 
			// You can comment out the following two lines 
			// and use the line of code in the lower section. 
			// Also, consider why the results might be incorrect 
			// when entering Chinese keywords.
			String encodeKeyword=java.net.URLEncoder.encode(searchKeyword,"utf-8");
			this.url = "https://www.google.com/search?q="+encodeKeyword+"&oe=utf8&num=20";
			
			// this.url = "https://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public void query(String s) throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
		        String rawUrl = li.select("a").attr("href");
		        String decodedUrl = java.net.URLDecoder.decode(rawUrl, "UTF-8");
//		        String doubleDecodedUrl = java.net.URLDecoder.decode(decodedUrl, "UTF-8");
		        String citeUrl = extractActualUrl(decodedUrl);
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				if (isValidUrl(citeUrl)) {

//					System.out.println("Title: " + title + " , url: " + citeUrl);

					WebPage3 webPage = new WebPage3(citeUrl, title);	
					lst.add(webPage);
				}			

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			} catch (IOException e) {
                // 處理 IOException，記錄錯誤，然後繼續下一個迴圈
//                System.err.println("Error processing a page: " + e.getMessage());
                continue;
            }
		}
		
		lst.find(s);
	}
	
	private String extractActualUrl(String rawUrl) {
	    if (rawUrl.startsWith("/url?q=")) {
	        return rawUrl.substring(7).split("&")[0];
	    }
	    return rawUrl;
	}
	
	private boolean isValidUrl(String url) {
	    try {
	        URL validUrl = new URL(url);
	        return "http".equals(validUrl.getProtocol()) || "https".equals(validUrl.getProtocol());
	    } catch (MalformedURLException e) {
	        return false;
	    }
	}
}