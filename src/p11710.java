import java.util.*;
import java.io.*;

public class p11710 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			if(size == 0)
				break;
			int roads = Integer.parseInt(st.nextToken());
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String[] indexes = new String[size];
			for(int i = 0; i < size; i++)
			{
				String s = f.readLine();
				map.put(s, i);
				indexes[i] = s;
			}
			ArrayList<Edge> edges = new ArrayList<Edge>();
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = map.get(st.nextToken());
				int v = map.get(st.nextToken());
				edges.add(new Edge(u, v, Integer.parseInt(st.nextToken())));
			}
			f.readLine();
			Collections.sort(edges);
			UnionFind uf = new UnionFind(size);
			int cost = 0;
			for(int i = 0; i < edges.size() && uf.size > 1; i++)
			{
				Edge e = edges.get(i);
				if(uf.union(e.u, e.v))
					cost += e.w;
			}
			out.println(uf.size == 1 ? cost : "Impossible");
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
			for(int i = 1; i < size; i++)
				parent[i] = i;
			rank = new int[size];
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