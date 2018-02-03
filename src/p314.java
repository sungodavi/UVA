import java.util.*;
import java.io.*;

public class p314
{
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1}; //NESW
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int rows = Integer.parseInt(st.nextToken());
			int cols = Integer.parseInt(st.nextToken());
			boolean[][] blocked = new boolean[rows + 1][cols + 1];
			for(int r = 0; r < rows; r++)
				blocked[r][0] = blocked[r][cols - 1] = true;
			for(int c = 0; c < cols; c++)
				blocked[0][c] = blocked[rows - 1][c] = true;
			for(int r = 0; r < rows; r++)
			{
				for(int c = 0; c < cols; c++, f.read())
				{
					int flag = f.read() - '0';
					if(flag == 1)
					{
						blocked[r][c] = blocked[r + 1][c] = blocked[r][c + 1] = blocked[r + 1][c + 1] = true;
					}
				}
				f.readLine();
			}
			for(boolean[] temp : blocked)
				System.out.println(Arrays.toString(temp));
			st = new StringTokenizer(f.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int dir = -1;
			String temp = st.nextToken();
			switch(temp.charAt(0))
			{
			case 'n':
				dir = 0; break;
			case 'e':
				dir = 1; break;
			case 's':
				dir = 2; break;
			case 'w':
				dir = 3; break;
			}
			Point start = new Point(sx, sy, dir, 0);
			Point end = new Point(ex, ey, 0, 0);
			int result = bfs(blocked, start, end);
			out.println(result);
		}
		out.close();
	}
	
	public static int bfs(boolean[][] blocked, Point start, Point end)
	{
		boolean[][][] visited = new boolean[blocked.length][blocked[0].length][4];
		Queue<Point> q = new LinkedList<Point>();
		q.add(start);
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(p.equals(end))
				return p.d;
			if(p.x < 0 || p.x >= blocked.length || p.y < 0 || p.y >= blocked[0].length 
					|| blocked[p.x][p.y] || visited[p.x][p.y][p.dir])
				continue;
			visited[p.x][p.y][p.dir] = true;
			for(int i = 1; i <= 3; i++)
				q.add(new Point(p.x + i * dx[p.dir], p.y + i * dy[p.dir], p.dir, p.d + 1));
			q.add(new Point(p.x, p.y, (p.dir + 1) % 4, p.d + 1));
			q.add(new Point(p.x, p.y, (p.dir + 3) % 4, p.d + 1));
		}
		return -1;
	}
	
	static class Point
	{
		int x, y, dir, d;
		public Point(int x, int y, int dir, int d)
		{
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.d = d;
		}
		
		public boolean equals(Point p)
		{
			return x == p.x && y == p.y;
		}
	}
}