package stage2_package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import HW6_package.WebPage;

public class Stage2_Main
{
	public static void main(String[] args) throws IOException
	{
		// root node
		WebPage1 rootPage = new WebPage1("https://blog.wordup.com.tw/blog/2023/05/22/2023-toeic-vocabulary-list-13-types-toeic-vocabularies-memorize-method-vocabulary-books-learning-resources/", "wordUp");
		WebTree1 tree = new WebTree1(rootPage);

		// build childnode
		tree.root.addChild(new WebNode1(new WebPage1("https://tw.amazingtalker.com/blog/zh-tw/zh-eng/10141/", "amazingtalker")));
		tree.root.addChild(new WebNode1(new WebPage1("https://biojoanna.pixnet.net/blog/post/318698216", "blog")));
		tree.root.children.get(1).addChild(new WebNode1(new WebPage1("https://www.englishok.com.tw/toeic/toeic-vocabulary", "englishok")));
		tree.root.addChild(new WebNode1(new WebPage1("https://plus.winningenglishschool.com/多益單字/", "winningEnglish")));
		tree.root.addChild(new WebNode1(new WebPage1("https://study-language.com.tw/2020/07/21/2021多益單字必考範圍這篇通通整理好了！/", "vocabulazy")));
		
		System.out.println("Please input (1)num of keywords (2)name and weight:");
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextLine())
		{
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword1> keywords = new ArrayList<Keyword1>();

			for (int i = 0; i < numOfKeywords; i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword1 k = new Keyword1(name, weight);
				keywords.add(k);
			}

			tree.setPostOrderScore(keywords);
			
			ArrayList<WebNode1> nodes = tree.getAllNodes();
			Collections.sort(nodes, Comparator.comparing(WebNode1::getScore).reversed());

			for(int i = 1; i <= nodes.size(); i++) {
				System.out.println("Rank:" + i);
				System.out.println("NodeName:" + nodes.get(i-1).webPage.name);
				System.out.println("NodeURL:" + nodes.get(i-1).webPage.url);
				System.out.println("Score:" + nodes.get(i-1).nodeScore);
				System.out.println("Tree structure:");
				nodes.get(i-1).parent = null;
				tree.eularPrintTree(nodes.get(i-1), keywords);
				System.out.println();
				System.out.println("---------------------");
			}	
		}
		scanner.close();
	}

}