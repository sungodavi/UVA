import java.util.*;
import java.io.*;

public class p10790 
{
	static int[][] dp;
	public static void main(String[] args) throws IOException 
	{
		load(300, 300);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			System.out.println(dp[m][n]);
		}
	}
	
	public static void load(int m, int n)
	{
		dp = new int[m + 1][n + 1];
		for(int i = 1; i <= m; i++)
			dp[i][1] = i - 1;
		for(int i = 1; i <= n; i++)
			dp[1][i] = i - 1;
		for(int r = 2; r <= m; r++)
			for(int c = 2; c <= n; c++)
			{
				int min = Integer.MAX_VALUE;
				for(int i = 1; i < r; i++)
					min = Math.min(min, dp[i][c] + dp[r - i][c] + 1);
				for(int i = 1; i < c; i++)
					min = Math.min(min, dp[r][i] + dp[r][c - i] + 1);
				dp[r][c] = min;
			}
	}
}