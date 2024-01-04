package HW3_Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class WordCounter {
	private String urlStr;
	private String content;

	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		// URL
		String retVal = "https://blog.wordup.com.tw/blog/2023/05/22/2023-toeic-vocabulary-list-13-types-toeic-vocabularies-memorize-method-vocabulary-books-learning-resources/";

		String line = null;

		while ((line = br.readLine()) != null) {
			retVal = retVal + line + "\n";
		}

		return retVal;
	}

	public int BoyerMoore(String T, String P, char sum) {
		// int m = P.length();
		// int n = T.length();
		int L = last(sum, P);
		int i = P.length() - 1;// backward
		int j = P.length() - 1;

		// Bonus: Implement Boyer-Moore Algorithm
		while (i <= T.length() - 1) {
			if (T.charAt(i) == P.charAt(j)) {
				if (j == 0)
					return i;// match at i
				else {
					i -= 1;
					j -= 1;
				}
			} else {
				// character-jump
				int l = last(T.charAt(i), P);
				i = i + P.length() - Math.min(j, l + l);
				j = P.length() - 1;
			}
		}

		return -1;// no match
	}

	public int last(char c, String P) {
		// Bonus: Implement last occurrence function
		int lastPos = -1; // last position to -1 (not found)

		for (int i = 0; i < P.length(); i++) {
			if (P.charAt(i) == c) {
				lastPos = i; // when a match is found
			}
		}

		return -1;
	}

	public int min(int a, int b) {
		if (a < b)
			return a;
		else if (b < a)
			return b;
		else
			return a;
	}

	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
		int keywordLength = keyword.length();
		int contentLength = content.length();
		int i = keywordLength - 1; // Start from the end of the keyword
		int j = keywordLength - 1;

		while (i < contentLength) {
			if (content.charAt(i) == keyword.charAt(j)) {
				if (j == 0) {
					// Keyword match found, increment count and continue searching
					retVal++;
					i += keywordLength; // Skip the matched keyword
				} else {
					// Continue matching
					i--;
					j--;
				}
			} else {
				// Character-jump using Boyer-Moore heuristic
				int charJump = j - last(content.charAt(i), keyword);
				int keywordJump = keywordLength - Math.min(j, 1 + charJump);
				i += keywordJump;
				j = keywordLength - 1;
			}
		}

		return retVal;
	}
}
