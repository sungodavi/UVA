import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11094 
{
	static char[][] a;
	static char land;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			int rows = Integer.parseInt(st.nextToken());
			int columns = Integer.parseInt(st.nextToken());
			dy = new int[]{1, columns - 1, 0, 0};
			a = new char[rows][columns];
			for(int r = 0; r < rows; r++)
				a[r] = f.readLine().toCharArray();
			
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			land = a[x][y];
			fill(x, y);
			int max = 0;
			for(int r = 0; r < a.length; r++)
				for(int c =0; c < a[0].length; c++)
					if(a[r][c] == land)
						max = Math.max(max, fill(r, c));
			System.out.println(max);
			f.readLine();
		}
	}
	
	public static int fill(int r, int c)
	{
		int size = 0;
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(r, c));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(a[p.x][p.y] == 0)
				continue;
			a[p.x][p.y] = 0;
			size++;
			for(int i = 0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = (p.y + dy[i]) % a[0].length;
				if(isValid(x, y))
					q.add(new Point(x, y));
			}
		}

		return size;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && a[r][c] == land;
	}
}