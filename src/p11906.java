import java.util.*;
import java.io.*;

public class p11906
{
	static boolean[][] water;
	static boolean[][] visited;
	static int even, odd;
	static int[] dx;
	static int[] dy;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int i = 1; i <= times; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			dx = new int[]{m, m, -m, -m, n, n, -n, -n};
			dy = new int[]{n, -n, -n, n, m, -m, -m, m};
			even = odd = 0;
			water = new boolean[r][c];
			visited = new boolean[r][c];

			for(int k = Integer.parseInt(f.readLine()); k > 0; k--)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				water[x][y] = true;
			}
			dfs(0, 0);
			System.out.printf("Case %d: %d %d\n", i, even, odd);
		}
	}
	
	public static void dfs(int r, int c)
	{
		if(visited[r][c])
			return;
		visited[r][c] = true;
		boolean[][] checked = new boolean[water.length][water[0].length];
		int count = 0;
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(isValid(x, y) && !checked[x][y])
			{
				checked[x][y] = true;
				count++;
				dfs(x, y);
			}
		}
		if(count % 2 == 0)
			even++;
		else
			odd++;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < water.length && c >= 0 && c < water[0].length && !water[r][c];
	}
}
