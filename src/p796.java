import java.util.*;
import java.io.*;

public class p796
{
	static LinkedList<Integer>[] list;
	public static int[] parent, dfs_low, dfs_num;
	static int counter;
	static ArrayList<Point> bridges;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int size = Integer.parseInt(input);
			parent = new int[size];
			dfs_low = new int[size];
			dfs_num = new int[size];
			counter = 0;
			Arrays.fill(dfs_num, -1);
			list = new LinkedList[size];
			bridges = new ArrayList<Point>();
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Integer>();
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				st.nextToken();
				while(st.hasMoreTokens())
				{
					int v = Integer.parseInt(st.nextToken());
					list[u].add(v);
					list[v].add(u);
				}				
			}
			f.readLine();
			for(int i = 0; i < size; i++)
				if(dfs_num[i] < 0)
					dfs(i);
			
			Collections.sort(bridges);
			out.printf("%d critical links\n", bridges.size());
			for(Point p : bridges)
				out.println(p);
			out.println();
		}
		out.close();
	}
	
	public static void dfs(int u)
	{
		dfs_num[u] = dfs_low[u] = counter++;
		for(int v : list[u])
		{
			if(dfs_num[v] < 0)
			{
				parent[v] = u;
				dfs(v);
				
				if(dfs_low[v] > dfs_num[u])
					bridges.add(new Point(u, v));
				dfs_low[u] = Math.min(dfs_low[v], dfs_low[u]);
			}
			else if(v != parent[u])
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
		}
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		public Point(int x, int y)
		{
			if(x < y)
			{
				this.x = x;
				this.y = y;
			}
			else
			{
				this.x = y;
				this.y = x;
			}
		}
		
		public int compareTo(Point p)
		{
			if(x == p.x)
				return y - p.y;
			return x - p.x;
		}
		
		public String toString()
		{
			return x + " - " + y;
		}
	}
}