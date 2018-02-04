import java.util.*;
import java.io.*;

public class p10099 
{
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int caseNo = 0;
		while(scan.hasNext())
		{
			int size = scan.nextInt();
			if(size == 0)
				break;
			int roads = scan.nextInt();
			int[][] a = new int[size][size];
			while(roads-- > 0)
			{
				int u = scan.nextInt() - 1;
				int v = scan.nextInt() - 1;
				a[u][v] = a[v][u] = scan.nextInt() - 1;
			}
			int u = scan.nextInt() - 1;
			int v = scan.nextInt() - 1;
			int target = scan.nextInt();
			int max = maximin(a, u, v);
			int result = max == 1e9 ? 0 : (target + max - 1) / max;
			out.printf("Scenario #%d\nMinimum Number of Trips = %d\n\n", ++caseNo, result);
		}
		out.close();
	}
	
	static int maximin(int[][] dp, int u, int v)
	{
		int n = dp.length;
		for(int r = 0; r < n; r++)
			dp[r][r] = (int)1e9;
		
		for(int k = 0; k < n; k++)
			for(int r = 0; r < n; r++)
				for(int c = 0; c < n; c++)
					dp[r][c] = Math.max(dp[r][c], Math.min(dp[r][k], dp[k][c]));
		return dp[u][v];
	}
}