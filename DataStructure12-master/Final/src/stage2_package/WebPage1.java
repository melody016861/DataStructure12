package stage2_package;
import java.io.IOException;
import java.util.ArrayList;

public class WebPage1
{
	public String url;
	public String name;
	public WordCounter1 counter;
	public double score;
	public ArrayList<Integer> keywordTimes = new ArrayList<Integer>();

	public WebPage1(String url, String name)
	{
		this.url = url;
		this.name = name;
		this.counter = new WordCounter1(url);
	}

	public void setScore(ArrayList<Keyword1> keywords) throws IOException
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