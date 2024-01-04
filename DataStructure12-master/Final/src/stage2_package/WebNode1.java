package stage2_package;
import java.io.IOException;
import java.util.ArrayList;

public class WebNode1
{
	public WebNode1 parent;
	public ArrayList<WebNode1> children;
	public WebPage1 webPage;
	public double nodeScore;// This node's score += all its children's nodeScore

	public WebNode1(WebPage1 webPage)
	{
		this.webPage = webPage;
		this.children = new ArrayList<WebNode1>();
	}
	
	public double getScore() {
		return nodeScore;
	}

	public void setNodeScore(ArrayList<Keyword1> keywords) throws IOException
	{
		// YOUR TURN
		// 2. calculate the score of this node
		// this method should be called in post-order mode

		// You should do something like:
		// 		1.compute the score of this webPage
		// 		2.set this score to initialize nodeScore
		//		3.nodeScore must be the score of this webPage 
		//		  plus all children's nodeScore
		
		webPage.setScore(keywords);
		nodeScore = webPage.score;
		for(int i = 0; i < children.size(); i++) {
			nodeScore += children.get(i).nodeScore;
		}
	}

	public void addChild(WebNode1 child)
	{
		// add the WebNode to its children list
		this.children.add(child);
		child.parent = this;
	}

	public boolean isTheLastChild()
	{
		if (this.parent == null)
			return true;
		ArrayList<WebNode1> siblings = this.parent.children;

		return this.equals(siblings.get(siblings.size() - 1));
	}

	public int getDepth()
	{
		int retVal = 1;
		WebNode1 currNode = this;
		while (currNode.parent != null)
		{
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}
