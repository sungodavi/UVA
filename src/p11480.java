import java.util.*;
import java.io.*;

public class p11480
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int caseNo = 0;
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
			out.printf("Case %d: %d\n", ++caseNo, solve(num));
		out.close();
	}
	
	public static long solve(int n)
	{
		n -= 6;
		long[] dp = new long[n + 1];
		dp[0] = 1;
		for(int i = 1; i <= 3; i++)
			for(int j = i; j < dp.length; j++)
				dp[j] += dp[j - i];
		return dp[dp.length - 1];
	}
}