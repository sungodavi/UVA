import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11228 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			long threshold = Integer.parseInt(st.nextToken());
			threshold *= threshold;
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y);
			}
			
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for(int i = 0; i < size - 1; i++)
				for(int j = i + 1; j < size; j++)
					edges.add(new Edge(i, j, dist(a[i], a[j])));
			Collections.sort(edges);
			
			double roads = 0;
			double railroads = 0;
			UnionFind states = new UnionFind(size);
			UnionFind uf = new UnionFind(size);
			for(int i = 0; i < edges.size() && uf.size > 1; i++)
			{
				Edge e = edges.get(i);
				if(uf.union(e.u, e.v))
				{
					if(e.w > threshold)
						railroads += Math.sqrt(e.w);
					else
					{
						roads += Math.sqrt(e.w);
						states.union(e.u, e.v);
					}
				}
			}
			out.printf("Case #%d: %d %.0f %.0f\n", t, states.size, roads, railroads);
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
		int u, v;
		long w;
		
		public Edge(int u, int v, long w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e)
		{
			return Long.compare(w, e.w);
		}
		
		public String toString()
		{
			return u + " " + v + " " + w;
		}
	}
	
	static long dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
}