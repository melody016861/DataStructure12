package stage3_package;

import java.util.ArrayList;
import java.util.Collections;

public class KeywordList
{
	private ArrayList<WebPage2> lst;

	public KeywordList()
	{
		this.lst = new ArrayList<WebPage2>();
	}
	
	public void add(WebPage2 webPage)
	{
		lst.add(webPage);
	}

	public void sort()
	{
		if (lst.size() == 0)
		{
			System.out.println("InvalidOperation");
		}
		else
		{
			quickSort(0, lst.size() - 1);
		}
	}

	// YOUR TURN
	// 1. Implement QuickSort algorithm
	// We assume that you are using an in-place approach, hence the return type is
	// void. If you prefer to use a different approach, you can modify this return
	// type (e.g., change it to return ArrayList<Keyword>)
	private void quickSort(int leftbound, int rightbound)
	{
		if (leftbound >= rightbound) {
	        return;
	    }

	    // Set rightBound as the pivot (x = S[righBound])
	    int pivotIndex = rightbound;
	    double pivot = lst.get(pivotIndex).score;

	    int j = leftbound;
	    int k = rightbound - 1;

	    while (j <= k) {
	        // Scan j to the right (j++) until j >= k or the element S[j] > x
	        while (j <= k && lst.get(j).score <= pivot) {
	            j++;
	        }

	        // Scan k to the left (k--) until j >= k or the element S[k] <= x
	        while (j <= k && lst.get(k).score > pivot) {
	            k--;
	        }

	        // Swap elements if j < k
	        if (j < k) {
	            swap(j, k);
	        }
	    }

	    // Swap pivot with j
	    swap(j, pivotIndex);

	    // Quicksort(leftBound, j-1, S); Quicksort(j+1, rightBound, S)
	    quickSort(leftbound, j - 1);
	    quickSort(j + 1, rightbound);
	}

	private void swap(int aIndex, int bIndex)
	{
		WebPage2 temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}

	public ArrayList<WebPage2> output()
	{
		Collections.reverse(lst);
		return lst;
	}
}