import java.util.*;
import java.io.*;

public class p1261
{
	static TreeSet<String> visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			String s = f.readLine();
			visited = new TreeSet<String>();
			System.out.println(recurse(s) ? 1 : 0);
		}
	}
	
	public static boolean recurse(String s)
	{
		if(s.isEmpty())
			return true;
		if(visited.contains(s))
			return false;
		visited.add(s);
		int start = 0;
		int end;
		char c = s.charAt(0);
		for(end = 0; end < s.length(); end++)
		{
			if(c != s.charAt(end))
			{
				c = s.charAt(end);
				if(end - start > 1 && recurse(s.substring(0, start) + s.substring(end)))
					return true;
				start = end;
			}
		}
		return end - start > 1 ? recurse(s.substring(0, start) + s.substring(end)) : false;
	}
}