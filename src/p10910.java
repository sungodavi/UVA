import java.util.*;
import java.io.*;

public class p10910
{
	public static long[][] dp;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		load();
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int top = t - n * p + n - 1;
			int bottom = n - 1;
			if(top < bottom)
				System.out.println(0);
			else
				System.out.println(dp[top][bottom]);
		}
	}
	
	public static void load()
	{
		dp = new long[71][71];
		dp[0][0] = 1;
		for(int r = 1; r < dp.length; r++)
		{
			dp[r][0] = dp[r][r] = 1;
			for(int c = 1; c < r; c++)
			{
				dp[r][c] = dp[r - 1][c] + dp[r - 1][c - 1];
			}
		}
	}
}