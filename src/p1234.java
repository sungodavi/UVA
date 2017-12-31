import java.util.*;
import java.io.*;

public class p1234
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			UnionFind uf = new UnionFind(size);
			Edge[] edges = new Edge[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < edges.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(u, v, d);
			}
			Arrays.sort(edges);
//			System.out.println(Arrays.toString(edges));
			int i;
			int cost = 0;
			for(i = 0; i < edges.length && uf.sets > 1; i++)
				uf.union(edges[i].u, edges[i].v);
//			System.out.println(i);
			for(; i < edges.length; i++)
				cost += edges[i].d;
			System.out.println(cost);
		}
	}
	static class Edge implements Comparable<Edge>
	{
		int u, v, d;
		public Edge(int u, int v, int d)
		{
			this.u = u;
			this.v = v;
			this.d = d;
		}
		public int compareTo(Edge e)
		{
			return e.d - d;
		}
		
		public String toString()
		{
			return u + " " + v + " " + d;
		}
	}
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int sets;
		public UnionFind(int size)
		{
			parent = new int[size];
			for(int i = 0; i < parent.length; i++)
				parent[i] = i;
			rank = new int[size];
			sets = size;
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
		
		public void union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return;
			sets--;
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
			}
			else
				parent[q] = p;
		}
	}
}