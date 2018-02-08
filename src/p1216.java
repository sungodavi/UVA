import java.util.*;
import java.io.*;
import java.awt.Point;

public class p1216
{
	static LinkedList<Edge>[] mst;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int count = Integer.parseInt(f.readLine());
			ArrayList<Point> list = new ArrayList<Point>();
			for(String input = f.readLine(); !input.equals("-1"); input = f.readLine())
				list.add(load(input));
			int size = list.size();
			mst = new LinkedList[size];
			for(int i = 0; i < size; i++)
				mst[i] = new LinkedList<Edge>();
			Edge[] edges = new Edge[size * (size - 1) >> 1];
			int index = 0;
			for(int i = 0; i < list.size() - 1; i++)
				for(int j = i + 1; j < list.size(); j++)
					edges[index++] = new Edge(i, j, dist(list.get(i), list.get(j)));
			double result = mst(edges, size, count);
			out.println((long)Math.ceil(result));
		}
		out.close();
	}
	
	public static Point load(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		return new Point(x, y);
	}
	
	public static double dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public static double mst(Edge[] edges, int size, int count)
	{
		Arrays.sort(edges);
		UnionFind uf = new UnionFind(size);
		double max = 0;
		for(int i = 0; i < edges.length && uf.size > count; i++)
		{
			Edge e = edges[i];
			if(uf.union(e.u, e.v))
			{
				mst[e.u].add(e);
				mst[e.v].add(e);
				max = e.w;
			}
		}
		return max;
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
			this.size = size;
			for(int i = 0; i < size; i++)
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
		int u, v;
		double w;
		public Edge(int u, int v, double w)
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
			return Double.compare(w, e.w);
		}
	}
}