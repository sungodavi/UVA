import java.util.*;
import java.io.*;

public class p12455 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			int target = Integer.parseInt(f.readLine());
			int[] a = new int[Integer.parseInt(f.readLine())];
			StringTokenizer st = new StringTokenizer(f.readLine());
			int sum = 0;
			for(int i =0; i < a.length; i++)
			{
				a[i] = Integer.parseInt(st.nextToken());
				sum += a[i];
			}
			if(check(a, target, sum))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	public static boolean check(int[] a, int target, int sum)
	{
		if(target > sum)
			return false;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for(int bar : a)
		{
			for(int i = dp.length - 1; i >= bar; i--)
			{
				if(dp[i - bar])
				{
					dp[i] = dp[i-bar];
					if(i == target)
						return true;
				}
			}
		}
		return dp[target];
		
	}
}
