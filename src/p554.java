import java.util.*;
import java.io.*;

public class p554 
{
	static TreeSet<String> set = new TreeSet<String>();
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String s = f.readLine(); !s.equals("#"); s = f.readLine())
			set.add(s);
		char[] a = f.readLine().toCharArray();
		int best = 0;
		String result = new String(a);
		for(int i = 1; i <= 26; i++)
		{
			increment(a);
			int count = check(a);
			if(count > best)
			{
				result = new String(a);
				best = count;
			}
		}
	}
	
	public static void increment(char[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == ' ')
				a[i] = 'A';
			else
			{
				int diff = (a[i] + 1 - 'A') % 26;
				if(diff == 0)
					a[i] = ' ';
				else
					a[i] = (char)(diff + 'A');
			}
		}
	}
	
	public static int check(char[] a)
	{
		StringTokenizer st = new StringTokenizer(new String(a));
		int count = 0;
		while(st.hasMoreTokens())
		{
			String s = st.nextToken();
			if(set.contains(s))
				++count;
		}
		return count;
	}
}