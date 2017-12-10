import java.util.*;
import java.io.*;

public class p532 
{
	static char[][][] a;
	static int x, y, z;
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			a = new char[x][y][z];
			for(int i = 0; i < x; i++)
			{
				for(int j = 0; j < y; j++)
				{
					a[i][j] = f.readLine().toCharArray();
				}
				f.readLine();
			}
			outer:
			for(int i = 0; i < x; i++)
				for(int j = 0; j < y; j++)
					for(int k = 0; k < z; k++)
						if(a[i][j][k] == 'S')
						{
							int result = bfs(i, j, k);
							if(result < 0)
								System.out.println("Trapped!");
							else
								System.out.printf("Escaped in %d minute(s).\n", result);
						}
		}
	}
	
	public static int bfs(int r, int c, int d)
	{
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(r, c, d, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(!p.isValid(x, y, z))
				continue;
			if(a[p.x][p.y][p.z] == 'E')
				return p.d;
			a[p.x][p.y][p.z] = '#';
			for(int i = 0; i < dx.length; i++)
			{
				int l = p.x + dx[i];
				int m = p.y + dy[i];
				int n = p.z + dz[i];
				q.add(new Point(l, m, n, p.d + 1));
			}
		}
		return -1;
	}

	
	static class Point
	{
		int x, y, z, d;
		public Point(int x, int y, int z, int d)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.d = d;
		}
		
		public boolean isValid(int r, int c, int d)
		{
			return x >= 0 && y >= 0 && z >= 0 && 
					x < r && y < c && z < d && a[x][y][z] != '#';
		}
	}
}
