import java.util.*;
import java.io.*;


public class p11624 
{
	static char[][] a;
	static int[][] fires;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			a = new char[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			for(int r = 0; r < a.length; r++)
				a[r] = f.readLine().toCharArray();
			fires = new int[a.length][a[0].length];
			Point fire = null, start = null;
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a.length; c++)
				{
					if(a[r][c] == 'F')
						fire = new Point(r, c, 0);
					else if(a[r][c] == 'J')
						start = new Point(r, c, 0);
				}
			bfs(fire, false);
			for(int[] temp : fires)
				System.out.println(Arrays.toString(temp));
			int result = bfs(start, true);
			if(result == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
	}
	
	public static int bfs(Point start, boolean flag)
	{
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] visited = new boolean[a.length][a[0].length];
		q.add(start);
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(flag)
			{
				if(!p.isValid())
				{
					return p.d;
				}
				if(visited[p.x][p.y] || p.d >= fires[p.x][p.y])
					continue;
			}
			else if(!p.isValid() || a[p.x][p.y] == '#' || visited[p.x][p.y])
				continue;
			visited[p.x][p.y] = true;
			if(!flag)
				fires[p.x][p.y] = p.d;
			for(int i =0 ; i < dx.length; i++)
				q.add(new Point(p.x + dx[i], p.y + dy[i], p.d + 1));
			
		}
		return -1;
		
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
		public boolean isValid()
		{
			return x >= 0 && y >= 0 && x < a.length && y < a[0].length;	
		}
	}
}
