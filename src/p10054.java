import java.util.*;
import java.io.*;

public class p10054 
{
	static LinkedList<Edge>[] list;
	static ArrayList<Integer> path;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			if(t > 1)
				out.println();
			int roads = Integer.parseInt(f.readLine());
			list = new LinkedList[51];
			for(int i = 1; i < list.length; i++)
				list[i] = new LinkedList<Edge>();
			int root = -1;
			path = new ArrayList<Integer>(roads);
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(root == -1)
					root = u;
				Edge e = new Edge(u, v);
				list[u].add(e);
				list[v].add(e);
			}
			out.printf("Case #%d\n", t);
			if(check())
			{
				build(root);
				for(int i = path.size() - 1; i > 0; i--)
				{
					out.printf("%d %d\n", path.get(i), path.get(i - 1));
				}
			}
			else
				out.println("some beads may be lost");
		}
		out.close();
	}
	
	public static void build(int u)
	{
		for(Edge e : list[u])
		{
			if(e.flag)
			{
				e.flag = false;
				build(e.v(u));
			}
		}
		path.add(u);
	}
	
	public static boolean check()
	{
		for(int i = 1; i < list.length; i++)
			if(list[i].size() % 2 != 0)
				return false;
		return true;
	}
	
	static class Edge
	{
		int u, v;
		boolean flag;
		
		public Edge(int u, int v)
		{
			this.u = u;
			this.v = v;
			flag = true;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
	}
}