import java.util.*;
import java.io.*;

public class p10765 
{
	static LinkedList<Integer>[] list;
	static int[] num, low, parent, cc;
	static int counter;
	static PriorityQueue<Target> q;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Integer>();
			for(String s = f.readLine(); s.charAt(0) != '-'; s = f.readLine())
			{
				st = new StringTokenizer(s);
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				list[u].add(v);
				list[v].add(u);
			}
			init(size);
			for(int i = 0; i < size; i++)
				if(num[i] == -1)
					dfs(i);
			for(int i = 0; i < m && !q.isEmpty(); i++)
				out.println(q.poll());
			out.println();
		}
		out.close();
	}
	
	public static void init(int size)
	{
		counter = 0;
		num = new int[size];
		cc = new int[size];
		parent = new int[size];
		low = new int[size];
		q = new PriorityQueue<Target>();
		for(int i = 0; i < size; i++)
			num[i] = parent[i] = -1;
	}
	
	public static void dfs(int u)
	{
		num[u] = low[u] = counter++;
		int children = 0;
		for(int v : list[u])
		{
			if(num[v] == -1)
			{
				children++;
				parent[v] = u;
				dfs(v);
				if(low[v] >= num[u])
					cc[u]++;
				low[u] = Math.min(low[u], low[v]);
			}
			else if(parent[u] != v)
				low[u] = Math.min(low[u], num[v]);
		}
		if(parent[u] == -1)
			cc[u] = children - 1;
		q.add(new Target(u, cc[u] + 1));
	}
	
	static class Target implements Comparable<Target>
	{
		int id, val;
		
		public Target(int id, int val)
		{
			this.id = id;
			this.val = val;
		}
		
		public int compareTo(Target t)
		{
			if(val != t.val)
				return t.val - val;
			return id - t.id;
		}
		
		public String toString()
		{
			return id + " " + val;
		}
	}
}