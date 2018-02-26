import java.util.*;
import java.io.*;

public class p10308 
{
	static boolean[] visited;
	static int[] dist;
	static LinkedList<Edge>[] list;
	static int min = Integer.MAX_VALUE, max = 0;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
				edges.add(convert(input));
			if(edges.isEmpty())
				break;
			list = new LinkedList[max + 1];
			for(int i = min; i <= max; i++)
				list[i] = new LinkedList<Edge>();
			for(Edge e : edges)
			{
				list[e.u].add(e);
				list[e.v].add(e);
			}
			System.out.println(diameter());
		}
	}
	
	public static Edge convert(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		min = Math.min(min, Math.min(u, v));
		max = Math.max(max, Math.max(u, v));
		return new Edge(u, v, w);
	}
	
	public static int diameter()
	{
		dist = new int[list.length];
		visited = new boolean[list.length];
		int u = dfs(min);
		dist = new int[list.length];
		visited = new boolean[list.length];
		int v = dfs(u);
		return dist[u];		
	}
	
	public static int dfs(int u)
	{
		visited[u] = true;
		int best = u;
		if(list[u] != null)
			for(Edge e : list[u])
			{
				int v = e.v(u);
				if(!visited[v])
				{
					int end = dfs(v);
					if(dist[v] + e.w > dist[u])
					{
						dist[u] = dist[v] + e.w;
						best = end;
					}
				}
			}
		return best;
	}
	
	static class Edge
	{
		int u, v, w;
		public Edge(int u, int v, int w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
	}
}