import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10986
{
	static LinkedList<Edge>[] list;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list = new LinkedList[size];
			for(int i = 0; i < size; i++)
				list[i] = new LinkedList<Edge>();
			for(int i = 0; i < roads; i++)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				Edge e = new Edge(u, v, d);
				list[u].add(e);
				list[v].add(e);
			}
			int result = sp(start, end);
			out.printf("Case #%d: ", t);
			if(result == -1)
				out.println("unreachable");
			else
				out.println(result);
		}
		out.close();
	}
	
	public static int sp(int start, int end)
	{
		PriorityQueue<Point> q = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				return a.y - b.y;
			}
		});
		int[] dist = new int[list.length];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		q.add(new Point(start, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			int u = p.x;
			int d = p.y;
			if(d > dist[u])
				continue;
			if(u == end)
				return d;
			for(Edge e : list[u])
			{
				int v = e.v(u);
				if(dist[v] > dist[u] + e.w)
				{
					dist[v] = dist[u] + e.w;
					q.add(new Point(v, dist[v]));
				}
			}
		}
		return -1;
	}
	
	static class Edge
	{
		int u, v, w;
		public Edge(int u, int v, int w)
		{
			this.u = u;;
			this.v = v;
			this.w = w;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
	}
}