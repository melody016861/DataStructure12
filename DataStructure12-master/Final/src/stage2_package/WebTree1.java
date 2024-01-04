package stage2_package;

import java.io.IOException;
import java.util.ArrayList;

public class WebTree1
{
	public WebNode1 root;
	ArrayList<WebNode1> nodes = new ArrayList<WebNode1>();

	public WebTree1(WebPage1 rootPage)
	{
		this.root = new WebNode1(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword1> keywords) throws IOException
	{
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode1 startNode, ArrayList<Keyword1> keywords) throws IOException
	{
		// YOUR TURN
		// 3. compute the score of children nodes via post-order, then setNodeScore for
		// startNode
		for(int i = 0; i < startNode.children.size(); i++) {
			if(startNode.children.get(i).children.size() > 0 ) {
				setPostOrderScore(startNode.children.get(i), keywords);
			}
			startNode.children.get(i).setNodeScore(keywords);
		}
		startNode.setNodeScore(keywords);
	}
	
	public ArrayList<WebNode1> getAllNodes()
	{
		return getAllNodes(root);
	}	
	
	private ArrayList<WebNode1> getAllNodes(WebNode1 startNode) {
		nodes.add(startNode);
		for(int i = 0; i < startNode.children.size(); i++) {
			getAllNodes(startNode.children.get(i));
		}

		return nodes;
	}

//	public void eularPrintTree()
//	{
//		eularPrintTree(root);
//	}

	public void eularPrintTree(WebNode1 startNode, ArrayList<Keyword1> keywords)
	{
		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1)
			System.out.print("\n" + repeat("\t", nodeDepth - 1));

		System.out.print("(");
		System.out.print(startNode.webPage.url);
		for(int i = 0; i < keywords.size(); i++) {
			System.out.print("," + keywords.get(i).name + ":" + startNode.webPage.keywordTimes.get(i));
		}
		
		for(int i = 0; i < startNode.children.size(); i++) {
			eularPrintTree(startNode.children.get(i), keywords);
		}

		System.out.print(")");

		if (startNode.isTheLastChild())
			System.out.print("\n" + repeat("\t", nodeDepth - 2));
	}

	private String repeat(String str, int repeat)
	{
		String retVal = "";
		for (int i = 0; i < repeat; i++)
		{
			retVal += str;
		}
		return retVal;
	}
}