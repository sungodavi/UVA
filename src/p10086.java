import java.util.*;
import java.io.*;

public class p10086
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		boolean flag = false;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			if(flag)
				out.println();
			else
				flag = true;
			st = new StringTokenizer(input);
			int t1 = Integer.parseInt(st.nextToken()); int t2 = Integer.parseInt(st.nextToken());
			int sites = Integer.parseInt(f.readLine());
			int[][] dp = new int[sites][t1 + 1];
			int[][] prev = new int[sites][t1 + 1];
			int[][] a = new int[sites][];
			for(int r = 0; r < sites; r++)
			{
				int size = Integer.parseInt(f.readLine());
				a[r] = new int[size + 1];
				st = new StringTokenizer(f.readLine());
				for(int c = 1; c <= size; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(f.readLine());
				for(int c = size - 1; c >= 0; c--)
					a[r][c] += Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[0], (int)1e9);
			for(int c = 0; c < Math.min(a[0].length, dp[0].length); c++)
				dp[0][c] = a[0][prev[0][c] = c];
			for(int r = 1; r < sites; r++)
			{
				for(int c = 0; c <= t1; c++)
				{
					int best = (int)1e9;
					int last = -1;
					for(int k = 0; k < a[r].length; k++)
					{
						if(c < k)
							break;
						if(dp[r - 1][c - k] + a[r][k] < best)
						{
							best = dp[r - 1][c - k] + a[r][k];
							last = c - k;
						}
					}
					dp[r][c] = best;
					prev[r][c] = last;
				}
			}
			out.println(dp[sites - 1][t1]);
			int c = t1;
			int[] result = new int[sites];
			for(int r = sites - 1; r >= 0; r--)
			{
				result[r] = c;
				c = prev[r][c];
			}
			//out.println("\n" + Arrays.toString(result));
			int curr = 0;
			for(int i = 0; i < result.length; i++)
			{
				if(i != 0)
					out.print(" ");
				out.print(result[i] - curr);
				curr = result[i];
			}
			out.println();
		}
		out.close();
	}
}