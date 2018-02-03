import java.util.*;
import java.io.*;

public class p10048
{
	static LinkedList<Edge>[] mst;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		boolean flag = false;
		for(String input = f.readLine(); ; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			if(size == 0)
				break;
			if(flag)
				out.println();
			else
				flag = true;
			out.printf("Case #%d\n", ++caseNo);
			int roads = Integer.parseInt(st.nextToken());
			int queries = Integer.parseInt(st.nextToken());
			mst = new LinkedList[size + 1];
			for(int i = 1; i < mst.length; i++)
				mst[i] = new LinkedList<Edge>();
			Edge[] edges = new Edge[roads];
			for(int i = 0; i < roads; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(u, v, w);
			}
			Arrays.sort(edges);
			UnionFind uf = new UnionFind(size + 1);
			uf.union(0, 1);
			for(int i = 0; i < edges.length && uf.size > 1; i++)
			{
				Edge e = edges[i];
				if(uf.union(e.u, e.v))
				{
					mst[e.u].add(e);
					mst[e.v].add(e);
				}
			}
			while(queries-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				visited = new boolean[size + 1];
				int result = dfs(u, v, 0);
				if(result != -1)
					out.println(result);
				else
					out.println("no path");
			}
		}
		out.close();
	}
	
	static int dfs(int u, int v, int max)
	{
		if(u == v)
			return max;
		if(visited[u])
			return -1;
		visited[u] = true;
		for(Edge e : mst[u])
		{
			int result = dfs(e.v(u), v, Math.max(max, e.w));
			if(result != -1)
				return result;
		}
		return -1;
	}
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size)
		{
			parent = new int[size];
			rank = new int[size];
			for(int i = 1; i < size; i++)
				parent[i] = i;
			this.size = size;
		}
		
		public int root(int p)
		{
			while(p != parent[p])
			{
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
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
			if(n == u)
				return v;
			return u;
		}
		
		public int compareTo(Edge e)
		{
			return w - e.w;
		}
	}
}