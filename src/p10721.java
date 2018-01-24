import java.util.*;
import java.io.*;

public class p10721
{
	static long[][][] dp;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		dp = new long[51][51][51];
		for(long[][] temp : dp)
			for(long[] temp2 : temp)
				Arrays.fill(temp2, -1);
		for(int m = 1; m < dp[0][0].length; m++)
			dp[0][0][m] = 1;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			System.out.println(recurse(n, k, m));
		}
	}
	
	public static long recurse(int n, int k, int m)
	{
		if(dp[n][k][m] != -1)
			return dp[n][k][m];
		if(n == 0 || k == 0)
			return 0;
		long result = 0;
		for(int i = Math.max(0, n - m); i < n; i++)
		{
			result += recurse(i, k - 1, m);			
		}
		return dp[n][k][m] = result;
	}
}