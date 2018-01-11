import java.util.*;
import java.io.*;
public class p147 
{
	static int[] coins = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		ArrayList<Integer> queries = new ArrayList<Integer>();
		int max = 0;
		for(String input = f.readLine(); !input.equals("0.00"); input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input, "\\.");
			int size = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			max = Math.max(max, size);
			queries.add(size);
		}
		long[] dp = new long[max + 1];
		dp[0] = 1;
		for(int c : coins)
			for(int i = c; i < dp.length; i++)
				dp[i] += dp[i - c];
		for(int n : queries)
		{
			out.printf("%6.2f%17d\n", (double)n / 100, dp[n]);
		}
		out.close();
	}
}
