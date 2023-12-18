package HW6_package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HW6_Main {

	public static void main(String[] args) throws IOException {
//		// 創建一個 WebPage 對象表示要進行排名的單個網頁
//		WebPage webpage = new WebPage("https://tw.amazingtalker.com/blog/zh-tw/zh-eng/10141/", "Toeic");

		// Create a list of web pages to rank
		ArrayList<WebPage> webPages = new ArrayList<>();

		// 設定要計算的關鍵字清單
		ArrayList<Keyword> keywords = new ArrayList<>();
		keywords.add(new Keyword("多益", 2.5));
		keywords.add(new Keyword("Toeic", 2.5));
		keywords.add(new Keyword("英文", 1.5));
		keywords.add(new Keyword("單字", 1.5));
		keywords.add(new Keyword("考試", 0.5));
		keywords.add(new Keyword("學習", 0.3));
		// 加入更多關鍵字...

		// Add web pages to the list
		webPages.add(new WebPage("https://tw.amazingtalker.com/blog/zh-tw/zh-eng/10141/", "Toeic"));
		webPages.add(new WebPage("https://www.examservice.com.tw/Product/BookList", "Toeic"));
		// Add more web pages...

		// Calculate scores for each web page
		for (WebPage webpage : webPages) {
			webpage.setScore(keywords);
		}

		// Sort the web pages based on their scores (descending order)
		Collections.sort(webPages, Comparator.comparing(WebPage::getScore).reversed());

		// Output the ranking (rank, URL, score, and keyword counts)
		System.out.println("Rank\tURL\t\t\t\t\t\t\tScore\tKeyword: Counts");
		for (int i = 0; i < webPages.size(); i++) {
			WebPage page = webPages.get(i);
			System.out.print((i + 1) + "\t");
			System.out.print(page.url + "\t");
			System.out.print(page.score + "\t");
			for (Keyword keyword : keywords) {
				int count = page.getCount().countKeyword(keyword.name);
				System.out.print(keyword.name + ": " + count + " ");
			}
			System.out.println();
		}
	}
}