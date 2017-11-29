import java.util.*;
import java.io.*;

public class p908 
{
	static ArrayList<Edge> edges;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null && !input.isEmpty();)
		{
			int size = Integer.parseInt(input);
			int before = 0;
			for(int i = 1; i <= size - 1; i++)
			{
				String s = f.readLine();
				before += Integer.parseInt(s.substring(s.lastIndexOf(' ') + 1));
			}
			int k = Integer.parseInt(f.readLine());
			edges = new ArrayList<Edge>(k);
			for(int i = 1; i <= k; i++)
			{
				addEdge(f.readLine());
			}
			int m = Integer.parseInt(f.readLine());
			edges.ensureCapacity(k + m);
			for(int i = 1; i <= m; i++)
				addEdge(f.readLine());
			System.out.println(before);
			System.out.println(mst(size));
			f.readLine();
			input = f.readLine();
			if(input != null && !input.isEmpty())
				System.out.println();
		}
	}
	
	static long mst(int size)
	{
		UnionFind uf = new UnionFind(size);
		Collections.sort(edges);
		long total = 0;
		for(int i = 0; i < edges.size() && uf.sets > 1; i++)
		{
			Edge e = edges.get(i);
			if(uf.union(e.u, e.v))
				total += e.weight;
		}
		return total;
	}
	
	static void addEdge(String input)
	{
		StringTokenizer st = new StringTokenizer(input);
		int u = Integer.parseInt(st.nextToken()) - 1;
		int v = Integer.parseInt(st.nextToken()) - 1;
		int weight = Integer.parseInt(st.nextToken());
		edges.add(new Edge(u, v, weight));
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int sets;
		public UnionFind(int size)
		{
			parent = new int[size];
			rank = new int[size];
			sets = size;
			for(int i = 0; i < size; i++)
				parent[i] = i;
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
			sets--;
			if(rank[p] < rank[q])
			{
				parent[p] = q;
			}
			else
			{
				parent[q] = p;
				if(rank[q] == rank[p])
					rank[p]++;
			}
			return true;
		}
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, weight;
		public Edge(int u, int v, int weight)
		{
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		public int other(int node)
		{
			if(node == u)
				return v;
			return u;
		}
		
		public int compareTo(Edge e)
		{
			return weight - e.weight;
		}
	}
	
}
