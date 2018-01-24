import java.util.*;
import java.io.*;

public class p10465
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			System.out.println(solve(m, n, t));
		}
	}
	
	public static long solve(int m, int n, int t)
	{
		long[] dp = new long[t + 1];
		long result = 0;
		for(int i = 1; i <= t; i++)
		{
			long best = 0;
			if(i >= m)
				best = Math.max(best, dp[i - m] + 1);
			if(i >= n)
				best = Math.max(best, dp[i - n] + 1);
			dp[i] = best;
			result = Math.max(best, result);
		}
		return result;
	}
}