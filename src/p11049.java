import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11049 
{
	static boolean[][] a;
	static int[][] walls, prev;
	static int[] dx = {1, -1, 0, 0}; //S, N, E, W
	static int[] dy = {0, 0, 1, -1};
	static char[] map = {'S', 'N', 'E', 'W'};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int sr = Integer.parseInt(st.nextToken()) - 1;
			st = new StringTokenizer(f.readLine());
			int ec = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			walls = new int[6][6];
			a = new boolean[6][6];
			prev = new int[6][6];
			for(int i = 1; i <= 3; i++)
			{
				st = new StringTokenizer(f.readLine());
				int wsc = Integer.parseInt(st.nextToken());
				int wsr = Integer.parseInt(st.nextToken());
				int wec = Integer.parseInt(st.nextToken());
				int wer = Integer.parseInt(st.nextToken());
				if(wsr == wer)
					addHor(wsc, wec, wsr);
				else
					addVert(wsr, wer, wsc);
			}
			
			int len = bfs(sr, sc, er, ec);
			System.out.println(reconstruct(er, ec, len));
		}
	}
	
	public static int bfs(int sr, int sc, int er, int ec)
	{
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(sr, sc, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(!isValid(p.x, p.y))
				continue;
			a[p.x][p.y] = true;
			if(p.x == er && p.y == ec)
				return p.d;
			int wall = walls[p.x][p.y];
			for(int i =0; i < dx.length; i++)
			{
				if((wall & 1 << i) == 0)
				{
					int x = p.x + dx[i];
					int y = p.y + dy[i];
					if(isValid(x, y))
					{
						prev[x][y] = i;
						q.add(new Point(x, y, p.d + 1));
					}
				}
			}
		}
		return -1;
	}
	
	public static String reconstruct(int er, int ec, int len)
	{
		char[] a = new char[len];
		for(int i = a.length - 1; i >= 0; i--)
		{
			int index = prev[er][ec];
			a[i] = map[index];
			er -= dx[index];
			ec -= dy[index];
		}
		return new String(a);
	}
	
	static void addVert(int sr, int er, int c)
	{
		for(int r = sr; r < er; r++)
		{
			walls[r][c] += 8;
			if(c > 0)
				walls[r][c - 1] += 4;
		}
	}
	
	static void addHor(int sc, int ec, int r)
	{
		for(int c = sc; c < ec; c++)
		{
			walls[r][c] += 2;
			if(r > 0)
				walls[r - 1][c] += 1;
		}
	}
	
	static boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && !a[r][c];
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
