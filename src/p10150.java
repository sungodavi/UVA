import java.util.*;
import java.io.*;

public class p10150 
{
	static boolean[] visited;
	static String[] words;
	static int[] prev;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> list = new ArrayList<String>();
		for(String s = f.readLine(); s != null && !s.isEmpty(); s = f.readLine())
		{
			list.add(s);
		}
		words = (String[]) list.toArray(words = new String[list.size()]);
		boolean flag = true;
		for(String s = f.readLine(); s != null; s = f.readLine())
		{
			if(flag)
				flag = false;
			else
				System.out.println();
			StringTokenizer st = new StringTokenizer(s);
			String temp = st.nextToken();
			int start = list.indexOf(temp);
			String end = st.nextToken();
			int result = bfs(start, end);
			if(result < 0)
				System.out.println("No solution.");
			else
			{
				System.out.println(temp);
				recreate(result);
			}
		}
	}
	
	public static void recreate(int num)
	{
		if(num == prev[num])
			return;
		recreate(prev[num]);
		System.out.println(words[num]);
	}
	
	public static int bfs(int s, String e)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visited = new boolean[words.length];
		prev = new int[words.length];
		prev[s] = s;
		while(!q.isEmpty())
		{
			int i = q.poll();
			if(visited[i])
				continue;
			visited[i] = true;
			if(words[i].equals(e))
				return i;
			for(int j = 0; j < words.length; j++)
			{
				if(!visited[j] && equals(words[i], words[j]))
				{
					prev[j] = i;
					q.add(j);
				}
			}
		}
		return -1;
	}
	
	public static boolean equals(String a, String b)
	{
		int min = Math.min(a.length(), b.length());
		int errors = Math.abs(a.length() - b.length());
		for(int i = 0; i < min; i++)
		{
			if(a.charAt(i) != b.charAt(i))
			{
				errors++;
				if(errors > 1)
					return false;
			}
		}
		return errors <= 1;
	}
}
