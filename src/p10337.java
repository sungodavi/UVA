import java.util.*;
import java.io.*;

public class p10337 
{
	static int[] costs = {20, 30, 60};
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			int size = Integer.parseInt(f.readLine()) / 100;
			int[][] w = new int[10][size];
			for(int i = w.length - 1; i >= 0; i--)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
					w[i][c] = Integer.parseInt(st.nextToken());
			}
			int[][] dp = new int[Math.min(10, size + 1)][size + 1];
			for(int r = 0; r < dp.length; r++)
				for(int c = r; c < dp[0].length; c++)
					dp[r][c] = Integer.MAX_VALUE;
			dp[0][0] = 0;
			for(int c = 0; c < size; c++)
			{
				for(int r = 0; r <= Math.min(dp.length - 1, c); r++)
				{
					for(int k = 0; k < costs.length; k++)
					{
						if(r + k - 1 < 0 || r + k - 1 >= dp.length)
							continue;
						dp[r + k - 1][c + 1] = Math.min(dp[r + k - 1][c + 1], dp[r][c] + costs[k] - w[r][c]); 
					}
				}
			}
			out.println(dp[0][size] + "\n");
		}
		out.close();
	}
}