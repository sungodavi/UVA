import java.util.*;
import java.io.*;

public class p10313 
{
	public static long[][] dp;
	public static void main(String[] args) throws IOException 
	{
		load(300, 1000);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			if(st.countTokens() == 1)
			{
				int size = Integer.parseInt(st.nextToken());
				out.println(solve(size, 0, 300));
			}
			else if(st.countTokens() == 2)
			{
				int size = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				out.println(solve(size, 0, end));
			}
			else
			{
				int size = Integer.parseInt(st.nextToken());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				out.println(solve(size, start, end));
			}
		}
		out.close();
	}
	
	public static long solve(int size, int start, int end)
	{
		long sum = 0;
		for(int i = start; i <= end; i++)
			sum += dp[i][size];
		return sum;
	}
	
	public static void load(int size, int end)
	{
		dp = new long[end + 1][size + 1];
		dp[0][0] = 1;
		for(int coin = 1; coin <= Math.min(size, 300); coin++)
		{
			for(int r = 1; r <= end; r++)
			{
				for(int c = coin; c <= size; c++)
				{
					dp[r][c] += dp[r - 1][c - coin];
				}
			}
		}
	}
}