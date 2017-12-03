import java.util.*;
import java.io.*;

public class p11631 
{
	static ArrayList<Edge> list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		while(size > 0)
		{
			list = new ArrayList<Edge>(roads);
			int max = 0;			
			for(int i = 1; i <= roads; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				max += weight;
				list.add(new Edge(u, v, weight));
			}

			System.out.println(max - krusts(size));
			
			st = new StringTokenizer(f.readLine());
			size = Integer.parseInt(st.nextToken());
			roads = Integer.parseInt(st.nextToken());
		}
	}
	
	public static int krusts(int size)
	{
		Collections.sort(list);
		UnionFind uf = new UnionFind(size);
		int cost = 0;
		for(int i = 0; i < list.size() && uf.sets > 1; i++)
		{
			Edge e = list.get(i);
			if(uf.union(e.u, e.v))
				cost += e.weight;			
		}
		return cost;
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
			for(int i = 0; i < parent.length; i++)
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
			if(rank[q] < rank[p])
			{
				parent[q] = p;
			}
			else
			{
				parent[p] = q;
				if(rank[q] == rank[p])
					rank[q]++;
			}
			return true;
		}
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v;
		int weight;
		
		public Edge(int u, int v, int weight)
		{
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		public int compareTo(Edge e)
		{
			return weight - e.weight;
		}
	}
}
