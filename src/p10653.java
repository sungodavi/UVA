import java.util.*;
import java.io.*;


public class p10653 
{
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static boolean[][] bombed;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		while(rows > 0)
		{
			int blocked = Integer.parseInt(f.readLine());
			bombed = new boolean[rows][cols];
			for(int k = 1; k <= blocked; k++)
			{
				st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens())
				{
					int c = Integer.parseInt(st.nextToken());
					bombed[r][c] = true;
				}
			}
			Point start = load(f.readLine());
			Point end = load(f.readLine());
			
			Queue<Point> q = new LinkedList<Point>();
			q.add(start);
			
			while(!q.isEmpty())
			{
				Point p = q.poll();
				if(!isValid(p.x, p.y))
					continue;
				bombed[p.x][p.y] = true;
				if(p.equals(end))
				{
					System.out.println(p.d);
					break;
				}
				for(int i = 0; i < dx.length; i++)
				{
					int x = p.x + dx[i];
					int y = p.y + dy[i];
					if(isValid(x, y))
						q.add(new Point(x,y, p.d + 1));
				}
			}
			
			
			st = new StringTokenizer(f.readLine());
			rows = Integer.parseInt(st.nextToken());
			cols = Integer.parseInt(st.nextToken());
		}
	}
	
	public static boolean isValid(int x, int y)
	{
		return x >= 0 && x < bombed.length && y >= 0 && y < bombed[0].length && !bombed[x][y];
	}

	
	public static Point load(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		return new Point(x, y, 0);
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
		
		public boolean equals(Point p)
		{
			return x == p.x && y == p.y;
		}
	}
}
