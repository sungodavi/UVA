import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11487 
{
	static Point[][][][] dp;
	static char[][] a;
	static boolean[][][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static final int MOD = 20437;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))		
		{
			a = new char[size][size];
			dp = new Point[size][size][size][size * size * size];
			visited = new boolean[size][size][size];
			for(int i = 0; i < size; i++)
			{
				f.read(a[i]);
				f.readLine();
			}
			outer:
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++)
					if(a[r][c] == 'A')
					{
						Point p = dfs(r, c, 0, 0);
						out.printf("Case %d: ", ++caseNo);
						if(p == null || p.y == -1)
							out.println("Impossible");
						else
							out.println(p.x + " " + p.y);
						break outer;
					}
		}
		out.close();
	}
	
	public static Point dfs(int r, int c, int food, int len)
	{
		if(visited[r][c][food])
			return null;
		if(dp[r][c][food][len] != null)
			return dp[r][c][food][len];
		if(a[r][c] != '.')
		{
			if(a[r][c] == food + 'A')
			{
				a[r][c] = '.';
				food++;
			}
			else if(a[r][c] != '#')
				return null;
		}
		if(food == a.length)
			return new Point(len, 1);
		visited[r][c][food] = true;
		
		int sp = Integer.MAX_VALUE;
		int count = -1;
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(isValid(x, y, food))
			{
				Point p = dfs(x, y, food, len + 1);
				if(p != null && p.y != -1)
				{
					if(p.x < sp)
					{
						sp = p.x;
						count = p.y;
					}
					else if(p.x == sp)
						count = (count + p.y) % MOD;
				}
			}
		}
		return dp[r][c][food][len] = new Point(sp, count);
	}
	
	public static boolean isValid(int r, int c, int food)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && !visited[r][c][food] && a[r][c] != '#';
	}
}