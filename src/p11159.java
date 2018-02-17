import java.util.*;
import java.io.*;

public class p11159 
{
	static LinkedList<Integer>[] list;
	static int[] match;
	static boolean[] visited;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			int[] a = convert(f.readLine());
			int[] b = convert(f.readLine());
			list = new LinkedList[a.length];
			for(int i = 0; i < a.length; i++)
				list[i] = new LinkedList<Integer>();
			for(int i = 0; i < a.length; i++)
				for(int j = 0; j < b.length; j++)
					if(a[i] == b[j] || (a[i] != 0 && b[j] % a[i] == 0))
						list[i].add(j + a.length);
			match = new int[a.length + b.length];
			Arrays.fill(match, -1);
			int count = 0;
			for(int i = 0; i < a.length; i++)
			{
				visited = new boolean[a.length];
				if(augment(i))
					count++;
			}
			out.printf("Case %d: %d\n", t, count);
		}
		out.close();
	}
	
	public static boolean augment(int u)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
			if(match[v] == -1 || augment(match[v]))
			{
				match[v] = u;
				return true;
			}
		return false;
	}
	
	public static int[] convert(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int[] a = new int[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		return a;
	}
}