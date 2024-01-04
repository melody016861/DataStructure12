package stage4_package;

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

public class Stage4_Main
{
	public static void main(String[] args) throws IOException
	{	
		System.out.println("Please input keywords:");
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextLine())
		{
			String keyword = scanner.next();

			new GoogleQuery1("多益單字").query(keyword);
		}
		scanner.close();
	}

}