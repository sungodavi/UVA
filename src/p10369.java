import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10369
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int forest = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y);
			}
			Edge[] edges = new Edge[size * (size - 1) >> 1];
			int index = 0;
			for(int r = 0; r < size; r++)
				for(int c = r + 1; c < size; c++)
					edges[index++] = new Edge(r, c, dist(a[r], a[c]));
			Arrays.sort(edges);
			UnionFind uf = new UnionFind(size);
			long max = 0;
			for(int i = 0; i < edges.length && uf.size > forest; i++)
			{
				Edge e = edges[i];
				if(uf.union(e.u, e.v))
					max = e.w;
			}
			out.printf("%.2f\n", Math.sqrt(max));
		}
		out.close();
	}
	
	public static long dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return dx * dx + dy * dy;
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
		
	}
}