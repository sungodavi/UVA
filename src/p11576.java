import java.util.*;
import java.io.*;

public class p11576
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int len = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			String[] words = new String[size];
			for(int i = 0; i < size; i++)
				words[i] = f.readLine();
			long result = size * len;
			for(int i = 0; i < size - 1; i++)
				result -= lsp(words[i], words[i + 1]);
			out.println(result);	
		}
		out.close();
	}
	
	public static int lsp(String a, String b)
	{
		int[] lps = process(b);
		int i = 0;
		int j = 0;
		while(i < a.length())
		{
			while(j >= 0 && a.charAt(i) != b.charAt(j))
				j = lps[j];
			i++; j++;
		}
		return j;
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
}