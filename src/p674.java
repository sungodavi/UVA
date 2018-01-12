import java.util.*;
import java.io.*;

public class p674 
{
	static long[] dp;
	static int[] coins = {1, 5, 10, 25, 50};
	public static void main(String[] args) throws IOException 
	{
		load(7489);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
			out.println(dp[Integer.parseInt(input)]);
		
		out.close();
	}
	
	static void load(int size)
	{
		dp = new long[size + 1];
		dp[0] = 1;
		for(int c : coins)
		{
			for(int i = c; i <= size; i++)
			{
				dp[i] += dp[i - c];
			}
		}
	}
}