import java.util.*;
import java.io.*;

public class p10688
{
	static int[][] dp = new int[501][2000];
	public static void main(String[] args) throws IOException
	{
		load();
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		for(int caseNo = 1; caseNo <= times; caseNo++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			out.printf("Case %d: %d\n", caseNo, dp[r][c]);
		}
		out.close();
	}
	
	public static void load()
	{
		for(int r = 2; r < dp.length; r++)
		{
			for(int k = 0; k < dp.length; k++)
			{
				int best = Integer.MAX_VALUE;
				for(int picked = 1; picked <= r; picked++)
				{
					int cost = picked + k;
					int size1 = picked - 1;
					int size2 = r - picked;
					//System.out.println(r + " " + k + " " + picked);
					int total = r * cost + dp[size1][k] + dp[size2][k + picked];
					best = Math.min(best, total);
				}
				dp[r][k] = best;
			}
		}
	}
}