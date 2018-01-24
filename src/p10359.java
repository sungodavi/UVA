import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p10359
{
	static BigInteger[] dp;
	public static void main(String[] args) throws IOException
	{
		load(250);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int num = Integer.parseInt(input);
			System.out.println(dp[num]);
		}
	}
	
	public static void load(int size)
	{
		dp = new BigInteger[size + 1];
		dp[0] = dp[1] = BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		for(int i = 2; i < dp.length; i++)
		{
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(two));
		}
	}
}