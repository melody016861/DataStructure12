package HW6_package;

import java.io.IOException;
import java.util.ArrayList;

import HW3_Package.WordCounter;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;

	public WebPage(String url, String name) {
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}

	public double getScore() {
		return score;
	}

	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		score = 0;
		// YOUR TURN
//		1. calculate the score of this webPage
		for (Keyword keyword : keywords) {
			int keywordCount = counter.countKeyword(keyword.name);
			double keywordWeight = keyword.weight;
			score += keywordCount * keywordWeight;
		}
	}

	public WordCounter getCount() {
		return counter;
	}
}