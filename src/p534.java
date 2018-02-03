import java.util.*;
import java.awt.Point;
import java.io.*;

public class p534
{
	static LinkedList<Edge>[] mst;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y);
			}
			mst = new LinkedList[size];
			for(int i = 0; i < size; i++)
				mst[i] = new LinkedList<Edge>();
			Edge[] edges = new Edge[size * (size - 1) >> 1];
			int index = 0;
			for(int r = 0; r < size - 1; r++)
				for(int c = r + 1; c < size; c++)
					edges[index++] = new Edge(r, c, dist(a[r], a[c]));
			Arrays.sort(edges);
			UnionFind uf = new UnionFind(size);
			for(int i = 0; i < edges.length && uf.size > 1; i++)
			{
				Edge e = edges[i];
				if(uf.union(e.u, e.v))
				{
					mst[e.u].add(e);
					mst[e.v].add(e);
				}
			}
			visited = new boolean[size];
			long result = dfs(0, 1, 0);
			out.printf("Scenario #%d\nFrog Distance = %.3f\n", ++caseNo, Math.sqrt(result));
			f.readLine();
			out.println();
		}
		out.close();
	}
	
	public static long dfs(int u, int v, long max)
	{
		if(visited[u])
			return -1;
		visited[u] = true;
		if(u == v)
			return max;
		for(Edge e : mst[u])
		{
			long result = dfs(e.v(u), v, Math.max(max, e.w));
			if(result != -1)
				return result;
		}
		return -1;
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
		
		public int v(int n)
		{
			if(n == u)
				return v;
			return u;
		}
		
		public int compareTo(Edge e)
		{
			return Long.compare(w, e.w);
		}
		
	}
}