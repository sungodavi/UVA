import java.util.*;
import java.io.*;

public class p11420
{
	static long[][][] dp = new long[66][66][2];
	public static void main(String[] args) throws IOException
	{
		load();
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine();; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(n < 0)
				break;
			out.println(dp[n][k][1] + dp[n][k][0]);
		}
		out.close();
	}
	
	public static void load()
	{
		dp[1][1][1] = dp[1][0][0] = 1;
		for(int r = 2; r < dp.length; r++)
		{
			for(int c = 0; c <= r; c++)
			{
				dp[r][c][0] = dp[r - 1][c][0] + dp[r - 1][c][1];
				if(c > 0)
					dp[r][c][1] = dp[r - 1][c - 1][1] + dp[r - 1][c][0];
				else
					dp[r][c][1] = dp[r - 1][c][0];
			}
		}
	}
}