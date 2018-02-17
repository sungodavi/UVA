import java.util.*;
import java.io.*;

public class p929
{
	static int[][] a;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int rows = Integer.parseInt(f.readLine());
			int cols = Integer.parseInt(f.readLine());
			a = new int[rows][cols];
			for(int r = 0; r < rows; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < cols; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
			}
			out.println(sp());
		}
		out.close();
	}
	
	public static int sp()
	{
		int[][] dist = new int[a.length][a[0].length];
		for(int[] temp : dist)
			Arrays.fill(temp, INF);
		Queue<Point> q = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				return a.d - b.d;
			}
		});
		dist[0][0] = a[0][0];
		q.add(new Point(0, 0, a[0][0]));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			int r = p.x;
			int c = p.y;
			int d = p.d;
			if(d > dist[r][c])
				continue;
			if(r == a.length - 1 && c == a[0].length - 1)
				return d;
			for(int i = 0; i < dx.length; i++)
			{
				int x = r + dx[i];
				int y = c + dy[i];
				if(isValid(x, y) && dist[x][y] > dist[r][c] + a[x][y])
				{
					dist[x][y] = dist[r][c] + a[x][y];
					q.add(new Point(x, y, dist[x][y]));
				}
			}
		}
		return -1;
	}
	
	static boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length;
	}
	
	static class Point
	{
		int x, y, d;
		public Point(int x, int y, int d)
		{
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}