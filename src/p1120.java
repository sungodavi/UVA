import java.util.*;
import java.io.*;

public class p1120
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			f.readLine();
			st = new StringTokenizer(f.readLine());
			int val = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			int[] a = new int[size];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			out.println(solve(a, val) ? "YES" : "NO");
		}
		out.close();
	}
	
	public static boolean solve(int[] coins, int val)
	{
		int[] sums = new int[coins.length];
		sums[0] = coins[0];
		for(int i = 1; i < coins.length; i++)
			sums[i] = sums[i - 1] + coins[i];
		boolean[] dp = new boolean[val + 1];
		dp[0] = true;
		for(int i = 1; i <= val; i++)
			for(int c : sums)
			{
				if(c > i)
					break;
				if(dp[i - c])
				{
					dp[i] = true;
					break;
				}
			}
		return dp[val];
	}
}