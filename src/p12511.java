import java.util.*;
import java.io.*;

public class p12511
{
	public static void main(String[] args) throws IOException
	{
//		System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int[] a = new int[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			int[] b = new int[Integer.parseInt(st.nextToken())];
			for(int i = 0; i < b.length; i++)
				b[i] = Integer.parseInt(st.nextToken());
			
			System.out.println(lis(lcs(a, b)));
			
		}
	}
	
	public static int lis(int[] a)
	{
		if(a.length == 0)
			return 0;
		int[] dp = new int[a.length];
		dp[0] = 1;
		int best = 1;
		for(int i = 1; i < a.length; i++)
		{
			int index = -1;
			for(int j = 0; j < i; j++)
			{
				if(a[j] < a[i])
				{
					if(index < 0 || dp[j] > dp[index])
						index = j;
				}
			}
			if(index >= 0)
				best = Math.max(best, dp[i] = dp[index] + 1);
			else
				dp[i] = 1;
		}
		return best;
	}
	
	public static int[] lcs(int[] a, int[] b)
	{
		int[][] dp = new int[a.length + 1][b.length + 1];
		for(int r = 1; r <= a.length; r++)
			for(int c = 1; c <= b.length; c++)
			{
				if(a[r - 1] == b[c - 1])
					dp[r][c] = dp[r - 1][c - 1] + 1;
				else
					dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
			}
		
		for(int[] temp : dp)
			System.out.println(Arrays.toString(temp));

		int[] result = new int[dp[a.length][b.length]];
		int index = result.length - 1;
		int r = a.length;
		int c = b.length;
		while(index >= 0 && r > 0 && c > 0)
		{
			if(a[r - 1] == b[c - 1])
			{
				result[index--] = a[r - 1];
				r--;
				c--;
			}
			else
			{
				if(dp[r - 1][c] > dp[r][c - 1])
					r--;
				else
					c--;
			}
		}
		System.out.println(Arrays.toString(result));
		return result;
	}
}