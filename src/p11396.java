import java.util.*;
import java.io.*;

public class p11396
{
	static LinkedList<Integer>[] list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Integer>();
			for(String input = f.readLine();; input = f.readLine())
			{
				st = new StringTokenizer(input);
				int u = Integer.parseInt(st.nextToken()) - 1;
				if(u < 0)
					break;
				int v = Integer.parseInt(st.nextToken()) - 1;
				list[u].add(v);
				list[v].add(u);
			}
			out.println(isBipartite() ? "YES" : "NO");
		}
		out.close();
	}
	
	public static boolean isBipartite()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		int[] colors = new int[list.length];
		Arrays.fill(colors, -1);
		q.add(0);
		colors[0] = 0;
		boolean result = true;
		while(!q.isEmpty() && result)
		{
			int u = q.poll();
			for(int v : list[u])
			{
				if(colors[v] == -1)
				{
					colors[v] = colors[u] ^ 1;
					q.add(v);
				}
				else if(colors[v] == colors[u])
				{
					result = false;
					break;
				}
			}
		}
		return result;
	}
}