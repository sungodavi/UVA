import java.util.*;
import java.io.*;

public class p166 
{
	static int[] coins = {5, 10, 20, 50, 100, 200};
	static int[] shopkeeper;
	public static void main(String[] args) throws IOException 
	{
		load(10000);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); !input.equals("0 0 0 0 0 0"); input = f.readLine())
		{
			int size = 0;
			int[] a = new int[6];
			st = new StringTokenizer(input);
			for(int i =0 ; i < a.length; i++)
			{
				int n = Integer.parseInt(st.nextToken());
				size += n * coins[i];
				a[i] = n;
			}
			
			int[] dp = new int[size + 1];
			Arrays.fill(dp, (int)1e9);
			dp[0] = 0;
			for(int i = 0; i < a.length; i++)
			{
				int c = coins[i];
				for(int times = 0; times < a[i]; times++)
				{
					for(int k = size; k >= c; k--)
					{
						if(dp[k] > dp[k - c] + 1)
							dp[k] = dp[k - c] + 1;
					}
				}
			}
			//System.out.println(Arrays.toString(dp));
			st = new StringTokenizer(st.nextToken(), "\\.");
			int target = 100 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			int result = Integer.MAX_VALUE;
			for(int i = target; i < dp.length; i++)
			{
				if(dp[i] != 1e9)
					result = Math.min(result, dp[i] + shopkeeper[i - target]);
			}
			out.printf("%3d\n", result);
		}
		out.close();
	}
	
	public static void load(int size)
	{
		shopkeeper = new int[size + 1];
		for(int i = 5; i <= size; i += 5)
		{
			int best = (int)1e9;
			for(int c : coins)
			{
				if(c > i)
					break;
				best = Math.min(best, shopkeeper[i - c] + 1);
			}
			shopkeeper[i] = best;
		}
	}
}