import java.util.*;
import java.io.*;

public class p10977 
{
	static Puff[] puffs;
	static boolean[][] a;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			a = new boolean[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			int blocked = Integer.parseInt(f.readLine());
			while(blocked-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				a[x][y] = true;
			}
			puffs = new Puff[Integer.parseInt(f.readLine())];
			for(int i = 0; i < puffs.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int range = Integer.parseInt(st.nextToken());
				puffs[i] = new Puff(x, y, range);
			}
			int result = bfs();
			if(result < 0)
				System.out.println("Impossible.");
			else
				System.out.println(result);
		}
	}
	
	static int bfs()
	{
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(0, 0, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(!isValid(p.x, p.y))
				continue;
			a[p.x][p.y] = true;
			if(p.x == a.length - 1 && p.y == a[0].length - 1)
				return p.d;
			for(int i = 0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				q.add(new Point(x, y, p.d + 1));
			}
		}
		return -1;
	}
	
	static boolean isValid(int r, int c)
	{
		if(r < 0 || c < 0 || r >= a.length || c >= a[0].length || a[r][c])
			return false;
		for(Puff p : puffs)
			if(p.contains(r, c))
				return false;
		return true;
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
	
	static class Puff
	{
		int r, c, range;
		public Puff(int r, int c, int range)
		{
			this.r = r;
			this.c = c;
			this.range = range;
		}
		
		public boolean contains(int x, int y)
		{
			int dx = r - x;
			int dy = c - y;
			return dx * dx + dy * dy <= range * range;
		}
	}
}
