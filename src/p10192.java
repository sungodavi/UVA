import java.util.*;
import java.io.*;

public class p10192
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int caseNo = 0;
		for(String a = f.readLine(); !a.equals("#"); a = f.readLine())
		{
			String b = f.readLine();
			int result = align(a, b);
			out.printf("Case #%d: you can visit at most %d cities.\n", ++caseNo, result);
		}
		out.close();
	}
	
	public static int align(String a, String b)
	{
		int[][] dp = new int[a.length() + 1][b.length() + 1];
		for(int r = 1; r <= a.length(); r++)
		{
			for(int c = 1; c <= b.length(); c++)
			{
				int max = 0;
				if(a.charAt(r - 1) == b.charAt(c - 1))
					max = dp[r - 1][c - 1] + 1;
				dp[r][c] = Math.max(max, Math.max(dp[r - 1][c], dp[r][c - 1]));
			}
		}
		return dp[a.length()][b.length()];
	}
}