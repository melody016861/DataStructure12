package stage3_package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import HW6_package.WebPage;
import stage2_package.WebNode1;
import stage2_package.WebPage1;
import stage2_package.WebTree1;

public class Stage3_Main
{
	public static void main(String[] args) throws IOException
	{	
		System.out.println("Please input (1)num of keywords (2)name and weight:");
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextLine())
		{
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword2> keywords = new ArrayList<Keyword2>();

			for (int i = 0; i < numOfKeywords; i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword2 k = new Keyword2(name, weight);
				keywords.add(k);
			}

			ArrayList<WebPage2> webPages = new GoogleQuery("多益單字").query(keywords);

			for(int i = 1; i <= webPages.size(); i++) {
				System.out.println("Rank:" + i);
				System.out.println("NodeName:" + webPages.get(i-1).name);
				System.out.println("NodeURL:" + webPages.get(i-1).url);
				System.out.println("Score:" + webPages.get(i-1).score);
				System.out.print("Times:");
				for(int j = 0; j < keywords.size(); j++) {
					System.out.print("[" + keywords.get(j).name + ":" + webPages.get(i-1).keywordTimes.get(j) + "]");
				}
				System.out.println("\n---------------------");
			}	
		}
		scanner.close();
	}

}