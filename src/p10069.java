import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p10069 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			String s = f.readLine();
			String sub = f.readLine();
			BigInteger[][] dp = new BigInteger[sub.length()][s.length() + 1];
			int max = 0;
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == sub.charAt(0))
					max++;
				dp[0][i] = BigInteger.valueOf(max);
			}
			for(int r = 1; r < dp.length; r++)
				dp[r][0] = BigInteger.ZERO;
			
			for(int r = 1; r < dp.length; r++)
			{
				char target = sub.charAt(r);
				for(int c = 1; c < dp[0].length; c++)
				{
					if(s.charAt(c - 1) == target)
						dp[r][c] = dp[r - 1][c-1].add(dp[r][c - 1]);
					else
						dp[r][c] = dp[r][c-1];
				}
			}
			System.out.println(dp[dp.length - 1][dp[0].length - 1]);
		}
	}
}
