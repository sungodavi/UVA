import java.util.*;
import java.io.*;

public class p11310
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			long[] dp = new long[Integer.parseInt(f.readLine()) + 1];
			dp[0] = 1;
			for(int i = 1; i < dp.length; i++)
			{
				dp[i] += dp[i - 1];
				if(i > 1)
					dp[i] += 4 * dp[i - 2];
				if(i > 2)
					dp[i] += 2 * dp[i - 3];
			}
			System.out.println(dp[dp.length - 1]);
		}
	}
}