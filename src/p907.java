import java.util.*;
import java.io.*;

public class p907
{
	static int[][] dp;
	static int[] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken()) + 1;
			int nights = Integer.parseInt(st.nextToken());
			a = new int[size];
			dp = new int[size][nights + 1];
			for(int[] temp : dp)
				Arrays.fill(temp, -1);
			for(int i = 0; i < size; i++)
				a[i] = Integer.parseInt(f.readLine());
			System.out.println(recurse(0, nights));
		}
	}
	
	public static int recurse(int pos, int nights)
	{
		if(pos == a.length)
			return 0;
		if(dp[pos][nights] != -1)
			return dp[pos][nights];
		if(nights == 0)
		{
			int sum = 0;
			for(int i = pos; i < a.length; i++)
				sum += a[i];
			return dp[pos][nights] = sum;
		}
		int sum = 0;
		int best = Integer.MAX_VALUE;
		for(int i = pos; i < a.length; i++)
		{
			sum += a[i];
			best = Math.min(best, Math.max(sum, recurse(i + 1, nights - 1)));
		}
		return dp[pos][nights] = best;
	}
}