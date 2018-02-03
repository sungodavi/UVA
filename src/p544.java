import java.util.*;
import java.io.*;

public class p544 
{
	static LinkedList<Edge>[] list;
	static int index;
	static HashMap<String, Integer> map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Edge>();
			index = 0;
			map = new HashMap<String, Integer>();
			Edge[] edges = new Edge[roads];
			for(int i = 0; i < roads; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = get(st.nextToken());
				int v = get(st.nextToken());
				edges[i] = new Edge(u, v, Integer.parseInt(st.nextToken()));
			}
			krustals(edges, size);
			st = new StringTokenizer(f.readLine());
			int u = get(st.nextToken());
			int v = get(st.nextToken());
			visited = new boolean[size];
			out.printf("Scenario #%d\n%d tons\n\n", ++caseNo, dfs(u, v, Integer.MAX_VALUE));
		}
		out.close();
	}
	
	public static int dfs(int u, int target, int min)
	{
		if(u == target)
			return min;
		visited[u] = true;
		for(Edge e : list[u])
		{
			int v = e.v(u);
			if(!visited[v])
			{
				int result = dfs(v, target, Math.min(min, e.w));
				if(result != -1)
					return result;
			}
		}
		return -1;
	}
	
	public static int get(String s)
	{
		if(map.containsKey(s))
			return map.get(s);
		map.put(s, index);
		return index++;
	}
	
	public static void krustals(Edge[] edges, int size)
	{
		Arrays.sort(edges);
		UnionFind uf = new UnionFind(size);
		for(int i = edges.length - 1; i >= 0 && uf.size > 1; i--)
		{
			Edge e = edges[i];
			if(uf.union(e.u, e.v))
			{
				list[e.u].add(e);
				list[e.v].add(e);
			}
		}
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size)
		{
			this.size = size;
			parent = new int[size];
			rank = new int[size];
			for(int i = 1; i < size; i++)
				parent[i] = i;
		}
		
		public int root(int p)
		{
			while(p != parent[p])
				p = parent[p] = parent[parent[p]];
			return p;
		}
		
		public boolean union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return false;
			size--;
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
			}
			else
				parent[q] = p;
			return true;
		}
	}
	
	static class Edge implements Comparable<Edge>
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
		
		public int compareTo(Edge e)
		{
			return w - e.w;
		}
	}
}