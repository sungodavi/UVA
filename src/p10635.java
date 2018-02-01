import java.util.*;
import java.io.*;

public class p10635
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int[] a = new int[s1 + 1];
			int[] b = new int[s2 + 1];
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < b.length; j++)
				b[j] = Integer.parseInt(st.nextToken());
			out.printf("Case %d: %d\n", t, align(a, b));
		}
		out.close();
	}
	
	public static int align(int[] a, int[] b)
	{
		int[][] dp = new int[a.length + 1][b.length + 1];
		for(int r = 1; r <= a.length; r++)
		{
			for(int c = 1; c <= b.length; c++)
			{
				int max = 0;
				if(a[r - 1] == b[c - 1])
					max = dp[r - 1][c - 1] + 1;
				dp[r][c] = Math.max(max, Math.max(dp[r - 1][c], dp[r][c - 1]));
			}
		}
		return dp[a.length][b.length];
	}
}