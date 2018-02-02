import java.util.*;
import java.io.*;

public class p11258
{
	static long[] dp;
	static String s;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			s = f.readLine();
			dp = new long[s.length()];
			Arrays.fill(dp, -1);
			out.println(recurse(0));
		}
		out.close();
	}
	
	public static long recurse(int index)
	{
		if(index == dp.length)
			return 0;
		if(dp.length - index <= 9)
			return Integer.parseInt(s.substring(index));
		if(dp[index] != -1)
			return dp[index];
		long num = 0;
		long max = 0;
		for(int i = index; i < dp.length; i++)
		{
			num = num * 10 + (s.charAt(i) - '0');
			if(num > Integer.MAX_VALUE)
				break;
			//System.out.println(num);
			max = Math.max(max, num + recurse(i + 1));
		}
		return dp[index] = max;
	}
}