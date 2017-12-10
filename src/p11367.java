import java.util.*;
import java.io.*;

public class p11367 
{
	static int[] fuel;
	static ArrayList<Edge>[] list;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		list = new ArrayList[Integer.parseInt(st.nextToken())];
		fuel = new int[list.length];
		int roads = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		fuel = new int[list.length];
		for(int i = 0; i < list.length; i++)
			fuel[i] = Integer.parseInt(st.nextToken());
		
		while(roads-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Edge e = new Edge(u, v, weight);
			if(list[u] == null)
				list[u] = new ArrayList<Edge>();
			if(list[v] == null)
				list[v] = new ArrayList<Edge>();
			list[u].add(e);
			list[v].add(e);
		}
		int queries = Integer.parseInt(f.readLine());
		while(queries-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int gas = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int result = dijkstras(start, end, gas);
			if(result < 0)
				System.out.println("impossible");
			else
				System.out.println(result);
			
		}
	}
	
	static int dijkstras(int start, int end, int tank)
	{
		Queue<Point> q = new PriorityQueue<Point>();
		int[][] dist = new int[list.length][tank + 1];
		for(int[] temp : dist)
			Arrays.fill(temp, (int)1e9);
		
		q.add(new Point(start, 0, 0));
		dist[start][0] = 0;
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(p.x == end)
				return p.d;
			if(p.d > dist[p.x][p.y])
				continue;
			if(list[p.x] != null)
			{
				for(Edge e : list[p.x])
				{
					int c = e.other(p.x);
					int f = p.y - e.weight;
					if(f >= 0 && dist[c][f] > dist[p.x][p.y])
					{
						dist[c][f] = dist[p.x][p.y];
						q.add(new Point(c, f, dist[c][f]));
					}
				}
			}
			if(p.y < tank && dist[p.x][p.y] + fuel[p.x] < dist[p.x][p.y + 1])
			{
				dist[p.x][p.y + 1] = dist[p.x][p.y] + fuel[p.x];
				q.add(new Point(p.x, p.y + 1, dist[p.x][p.y + 1]));
			}
		}
		return -1;
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y, d;
		public Point(int x, int y, int d)
		{
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public int compareTo(Point p)
		{
			return d - p.d;
		}
		
		public String toString()
		{
			return x + " " + y + " " + d;
		}
	}
	
	static class Edge
	{
		int u, v, weight;
		public Edge(int u, int v, int weight)
		{
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		public int other(int x)
		{
			if(x == u)
				return v;
			return u;
		}
		public String toString()
		{
			return u + "-" + weight + "-" + v;
		}
	}
}
