import java.util.*;
import java.io.*;

public class p10401
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
			System.out.println(solve(scan.nextLine()));
	}
	
	public static long solve(String s)
	{
		int n = s.length();
		long[][] dp = new long[n][n];
		if(s.charAt(0) == '?')
		{
			for(int r = 0; r < n; r++)
				dp[r][0] = 1;
		}
		else
		{
			int r = convert(s.charAt(0));
			dp[r][0] = 1;
		}
		for(int c = 1; c < n; c++)
		{
			if(s.charAt(c) == '?')
			{
				for(int r = 0; r < n; r++)
				{
					long sum = 0;
					for(int k = 0; k < r - 1; k++)
						sum += dp[k][c - 1];
					for(int k = r + 2; k < n; k++)
						sum += dp[k][c - 1];
					dp[r][c] = sum;
				}
			}
			else
			{
				int r = convert(s.charAt(c));
				long sum = 0;
				for(int k = 0; k < r - 1; k++)
					sum += dp[k][c -1];
				for(int k = r + 2; k < n; k++)
					sum += dp[k][c - 1];
				dp[r][c] = sum;
			}
		}
		long sum = 0;
		for(int r = 0; r < n; r++)
			sum += dp[r][n - 1];
		return sum;
	}
	
	public static int convert(char c)
	{
		if(c <= '9')
			return c - '1';
		return c - 'A' + 9;
		
	}
}