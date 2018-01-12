import java.util.*;
import java.io.*;

public class p11137 
{
	static long[] dp;
	public static void main(String[] args) throws IOException 
	{
		load(9999);
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
			System.out.println(dp[scan.nextInt()]);
		scan.close();
	}
	
	static void load(int size)
	{
		dp = new long[size + 1];
		dp[0] = 1;
		for(int i = 1; i * i * i <= size; i++)
		{
			int coin = i * i * i;
			for(int k = coin; k <= size; k++)
				dp[k] += dp[k - coin];
		}
	}
}