import java.util.*;
import java.io.*;

public class p11857
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); ; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			if(size == 0)
				break;
			int roads = Integer.parseInt(st.nextToken());
			Edge[] edges = new Edge[roads];
			for(int i = 0; i < edges.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(u, v, w);
			}
			Arrays.sort(edges);
			UnionFind uf = new UnionFind(size);
			int max = 0;
			for(int i = 0; i < edges.length && uf.size > 1; i++)
			{
				Edge e = edges[i];
				if(uf.union(e.u, e.v))
					max = e.w;
			}
			if(uf.size == 1)
				out.println(max);
			else
				out.println("IMPOSSIBLE");
		}
		out.close();
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
		
		public int compareTo(Edge e)
		{
			return w - e.w;
		}
	}
}