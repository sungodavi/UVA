import java.util.*;
import java.io.*;

public class p10285
{
	static int[][] dp;
	static int[][] a;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			String name = st.nextToken();
			int rows = Integer.parseInt(st.nextToken());
			int cols = Integer.parseInt(st.nextToken());
			a = new int[rows][cols];
			dp = new int[rows][cols];
			for(int[] temp : dp)
				Arrays.fill(temp, -1);
			for(int r = 0; r < rows; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < cols; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
			}
			int max = 0;
			for(int r = 0; r < rows; r++)
				for(int c = 0; c < cols; c++)
					if(dp[r][c] == -1)
						max = Math.max(max, recurse(r, c));
			out.printf("%s: %d\n", name, max);
		}
		out.close();
	}
	
	public static int recurse(int r, int c)
	{
		if(dp[r][c] != -1)
			return dp[r][c];
		int best = 0;
		for(int i = 0; i < dx.length; i++)
		{
			int x = r + dx[i];
			int y = c + dy[i];
			if(x >= 0 && x < a.length && y >= 0 && y < a[0].length && a[x][y] < a[r][c])
				best = Math.max(best, recurse(x, y));
		}
		return dp[r][c] = best + 1;
	}
}