package stage3_package;
import java.io.IOException;
import java.util.ArrayList;

public class WebPage2
{
	public String url;
	public String name;
	public WordCounter2 counter;
	public double score;
	public ArrayList<Integer> keywordTimes = new ArrayList<Integer>();

	public WebPage2(String url, String name)
	{
		this.url = url;
		this.name = name;
		this.counter = new WordCounter2(url);
	}

	public void setScore(ArrayList<Keyword2> keywords) throws IOException
	{
		score = 0;
		// YOUR TURN
//		1. calculate the score of this webPage
		for(int i = 0; i < keywords.size(); i++) {
			double weight = keywords.get(i).weight;
			int times = counter.countKeyword(keywords.get(i).name);
			keywordTimes.add(times);
			score += weight * times;
		}
	}
}