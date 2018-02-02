import java.util.*;
import java.io.*;

public class p1056
{
	public static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		int caseNo = 0;
		boolean flag = false;
		while(scan.hasNext())
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			int size = scan.nextInt();
			if(size == 0)
				break;
			int connections = scan.nextInt();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int index = 0;
			int[][] a = new int[size][size];
			for(int i = 0; i < connections; i++)
			{
				String u = scan.next();
				String v = scan.next();
				int r, c;
				if(map.containsKey(u))
					r = map.get(u);
				else
				{
					map.put(u, index++);
					r = index - 1;
				}
				if(map.containsKey(v))
					c = map.get(v);
				else
				{
					map.put(v, index++);
					c = index - 1;
				}
				a[r][c] = a[c][r] = 1;
			}
			int result = max(a);
			System.out.printf("Network %d: ", ++caseNo);
			if(result == INF)
				System.out.println("DISCONNECTED");
			else
				System.out.println(result);
		}
	}
	
	public static int max(int[][] a)
	{
		int[][] dist = floydWarshall(a);
		int max = dist[0][0];
		for(int[] row : dist)
			for(int c : row)
				max = Math.max(max, c);
		return max;
	}
	
	public static int[][] floydWarshall(int[][] a)
	{
		int[][] dp = new int[a.length][a.length];
		for(int r = 0; r < a.length; r++)
		{
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
					dp[r][c] = a[r][c];
				else if(r != c)
					dp[r][c] = INF;
			}
		}
		for(int k = 0; k < a.length; k++)
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a.length; c++)
					dp[r][c] = Math.min(dp[r][c], dp[r][k] + dp[k][c]);
		return dp;
	}
}