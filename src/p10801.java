import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10801
{
	static int[][] stops;
	static int[][] map;
	static int[] cost;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			stops = new int[size][];
			map = new int[size][100];
			for(int[] temp : map)
				Arrays.fill(temp, -1);
			cost = new int[size];
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < size; i++)
				cost[i] = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				stops[i] = new int[st.countTokens()];
				for(int j = 0; j < stops[i].length; j++)
				{
					int num = Integer.parseInt(st.nextToken());
					stops[i][j] = num;
					map[i][num] = j;
				}
			}
			
			int result = sp(end);
			if(result == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
	}
	
	public static int sp(int end)
	{
		int[][] dist = new int[100][stops.length];
		for(int[] temp : dist)
			Arrays.fill(temp, (int)1e9);
		Queue<Point> q = new PriorityQueue<Point>();
		for(int i = 0; i < map.length; i++)
			if(map[i][0] != -1)
			{
				dist[0][i] = 0;
				q.add(new Point(0, i, 0));
			}
		while(!q.isEmpty())
		{
			Point p = q.poll(); //p.x = floor, p.y = elevator
			if(p.d > dist[p.x][p.y])
				continue;
			if(p.x == end)
				return p.d;
			int index = map[p.y][p.x];
			if(index < stops[p.y].length - 1)
			{
				int r = stops[p.y][index + 1];
				int c = p.y;
				int d = cost[p.y] * (Math.abs(p.x - r));
				if(dist[r][c] > dist[p.x][p.y] + d)
				{
					dist[r][c] = dist[p.x][p.y] + d;
					q.add(new Point(r, c, dist[r][c]));
				}
			}
			if(index > 0)
			{
				int r = stops[p.y][index - 1];
				int c = p.y;
				int d = cost[p.y] * (Math.abs(p.x - r));
				if(dist[r][c] > dist[p.x][p.y] + d)
				{
					dist[r][c] = dist[p.x][p.y] + d;
					q.add(new Point(r, c, dist[r][c]));
				}
			}
			
			for(int r = 0; r < map.length; r++)
			{
				if(r != p.y && map[r][p.x] != -1 && dist[p.x][r] > p.d + 60)
				{
					dist[p.x][r] = p.d + 60;
					q.add(new Point(p.x, r, dist[p.x][r]));
				}
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
}