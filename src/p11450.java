import java.util.*;
import java.io.*;

public class p11450 
{
	static int[][] garments;
	static int[][] memoTable;
	static int budget;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		StringTokenizer st;
		for(; cases > 0; cases--)
		{
			st = new StringTokenizer(f.readLine());
			budget = Integer.parseInt(st.nextToken());
			garments = new int[Integer.parseInt(st.nextToken())][];
			//memoTable = new int[garments.length][budget + 1];
			//for(int[] temp : memoTable)
				//Arrays.fill(temp, -1);
			
			for(int r = 0; r < garments.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				garments[r] = new int[Integer.parseInt(st.nextToken())];
				for(int c = 0; c < garments[r].length; c++)
					garments[r][c] = Integer.parseInt(st.nextToken());
			}
			int result = dp(budget);
			if(result < 0)
				System.out.println("no solution");
			else
				System.out.println(result);
		}
	}
	
	public static int recurse(int money, int g)
	{
		if(money < 0)
			return -1;
		if(g == garments.length)
			return budget - money;
		if(memoTable[g][money] >= 0)
			return memoTable[g][money];
		int best = -1;
		for(int c = 0; c < garments[g].length; c++)
			best = Math.max(best, recurse(money - garments[g][c], g + 1));
		return memoTable[g][money] = best;
	}
	
	public static int dp(int money)
	{
		boolean[][] dp = new boolean[garments.length + 1][money + 1];
		dp[0][0] = true;
		for(int r = 1; r < dp.length; r++)
		{
			for(int c = 0; c < dp[0].length; c++)
			{
				if(dp[r - 1][c])
					for(int price : garments[r - 1])
						if(c + price < dp[0].length)
							dp[r][c + price] = true;
			}
		}
		for(int c = dp[0].length - 1; c >= 0; c--)
			if(dp[dp.length - 1][c])
				return c;
		return -1;
	}
	
}