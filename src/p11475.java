import java.util.*;
import java.io.*;

public class p11475
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		for(String input = f.readLine(); input != null; input = f.readLine())
			out.println(solve(input));
		
		out.close();
	}
	
	public static String solve(String s)
	{
		String reverse = reverse(s, s.length());
		int[] lps = process(reverse);
		int i = 0;
		int j = 0;
		while(i < s.length())
		{
			while(j >= 0 && s.charAt(i) != reverse.charAt(j))
				j = lps[j];
			i++;
			j++;
		}
		return s + reverse(s, i - j);
	}
	public static int[] process(String s)
	{
		int[] lps = new int[s.length() + 1];
		int j = lps[0] = -1;
		int i = 0;
		while(i < s.length())
		{
			while(j >= 0 && s.charAt(i) != s.charAt(j))
				j = lps[j];
			lps[++i] = ++j;
		}
		return lps;
	}
	
	public static String reverse(String s, int size)
	{
		char[] a = new char[size];
		for(int i = a.length - 1, k = 0; i >= 0; i--, k++)
			a[i] = s.charAt(k);
		return new String(a);
	}
	
	public static boolean isPalindrome(String s, int i)
	{
		int j = s.length() - 1;
		while(i < j)
		{
			if(s.charAt(i) != s.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}
}