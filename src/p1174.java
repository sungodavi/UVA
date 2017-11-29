import java.util.*;
import java.io.*;

public class p1174 
{
	static HashMap<String, Integer> map;
	static ArrayList<Edge> list;
	static int count;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			map = new HashMap<String, Integer>();
			int size = Integer.parseInt(f.readLine());
			int edges = Integer.parseInt(f.readLine());
			count = 0;
			list = new ArrayList<Edge>(size);
			for(int i = 1; i <= edges; i++)
			{
				st = new StringTokenizer(f.readLine());
				String u = st.nextToken();
				String v = st.nextToken();
				add(u);
				add(v);
				int weight = Integer.parseInt(st.nextToken());
				list.add(new Edge(map.get(u), map.get(v), weight));
			}
			System.out.println(krusts(size));
			if(times > 0)
				System.out.println();
		}
	}
	
	static int krusts(int size)
	{
		UnionFind uf = new UnionFind(size);
		Collections.sort(list);
		int result = 0;
		for(int i = 0; i < list.size() && uf.sets > 1; i++)
		{
			Edge e = list.get(i);
			if(uf.union(e.u, e.v))
				result += e.weight;
		}
		return result;
	}
	static void add(String s)
	{
		if(!map.containsKey(s))
			map.put(s, count++);
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
		
		public int find(int p)
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
			int p = find(a);
			int q = find(b);
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
				if(rank[p] == rank[q])
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
		
		public int compareTo(Edge e)
		{
			return weight - e.weight;
		}
	}
}
