import java.util.*;
import java.io.*;

public class p11151
{
	static int[][] dp;
	static char[] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			a = f.readLine().toCharArray();
			dp = new int[a.length][a.length];
			for(int[] temp : dp)
				Arrays.fill(temp, -1);
			out.println(recurse(0, a.length - 1));
		}
		out.close();
		
	}
	
	public static int recurse(int l, int r)
	{
		if(l > r)
			return 0;
		if(l == r)
			return 1;
		if(dp[l][r] != -1)
			return dp[l][r];
		if(r == l + 1)
			return a[l] == a[r] ? 2 : 1;
		if(a[l] == a[r])
			return dp[l][r] = 2 + recurse(l + 1, r - 1);
		return dp[l][r] = Math.max(recurse(l + 1, r), recurse(l, r - 1));
	}
}