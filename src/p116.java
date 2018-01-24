import java.util.*;
import java.io.*;

public class p116 {
	static int[][] dp;
	static int[][] prev;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int rows = Integer.parseInt(st.nextToken());
			int cols = Integer.parseInt(st.nextToken());
			
			int[][] a = new int[rows][cols];
			for(int r = 0; r < rows; r++)
			{
				for(int c = cols - 1; c >= 0; c--)
				{
					if(!st.hasMoreTokens())
						st = new StringTokenizer(f.readLine());
					a[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int index = solve(a);
			int[] result = rebuild(solve(a));
			for(int i = result.length - 1; i >= 0; i--)
				out.print((i == result.length - 1 ? "" : " ") + (result[i] + 1));
			out.println();
			out.println(dp[index][cols - 1]);
		}
		out.close();
	}
	
	public static int solve(int[][] a)
	{
		dp = new int[a.length][a[0].length];
		prev = new int[a.length][a[0].length];
		
		for(int r = 0; r < a.length; r++)
			dp[r][0] = a[r][0];
		
		for(int c = 1; c < a[0].length; c++)
		{
			for(int r = 0; r < a.length; r++)
			{
				int index = -1;
				for(int k = 1; k >= -1; k--)
				{
					int num = (r + a.length + k) % a.length;
					if(index < 0 || dp[num][c-1] < dp[index][c-1] || dp[num][c-1] == dp[index][c-1] && num < index)
						index = num;
				}
				dp[r][c] = dp[index][c - 1] + a[r][c];
				prev[r][c] = index;
			}
		}
		int minIndex = 0;
		for(int r = 1; r < a.length; r++)
			if(dp[r][a[0].length - 1] < dp[minIndex][a[0].length - 1])
				minIndex = r;
		
		return minIndex;
	}
	
	public static int[] rebuild(int r)
	{
		int[] a = new int[dp[0].length];
		a[a.length - 1] = r;
		for(int c = a.length - 1; c > 0; c--)
		{
			a[c - 1] = r = prev[r][c];
		}
		return a;
	}
}