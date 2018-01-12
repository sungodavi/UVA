import java.util.*;
import java.io.*;

public class p11284
{
	static int[][] dist;
	static int[][] dp;
	static int[] savings, map;
	static int OK;
	static int recordSize;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken()) + 1;
			int roads = Integer.parseInt(st.nextToken());
			System.out.println(size + " " + roads);
			int[][] a = new int[size][size];
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if(a[r][c] > 0)
					a[r][c] = a[c][r] = Math.min(a[r][c], loadDecimal(st.nextToken()));
				else
					a[r][c] = a[c][r] = loadDecimal(st.nextToken());
			}
			dist = floydWarshall(a);
			
			int items = Integer.parseInt(f.readLine());
			savings = new int[size];
			map = new int[items + 1];
			recordSize = 1;
			int index = 0;
			while(items-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int id = Integer.parseInt(st.nextToken());
				if(savings[id] == 0)
				{
					recordSize++;
					map[++index] = id;
				}
				savings[id] += loadDecimal(st.nextToken());
			}

			dp = new int[recordSize][1 << recordSize];
			for(int[] temp : dp)
				Arrays.fill(temp, Integer.MAX_VALUE);
			OK = (1 << recordSize) - 1;
			
			int result = tsp(0, 1);
			if(result > 0)
				System.out.printf("Daniel can save $%d.%02d\n", result / 100, result % 100);
			else
				System.out.println("Don't leave the house");
		}
		out.close();
	}
	
	public static int tsp(int pos, int mask)
	{
		if(mask == OK)
			return -dist[map[pos]][0];
		
		if(dp[pos][mask] != Integer.MAX_VALUE)
			return dp[pos][mask];
		
		int result = -dist[map[pos]][0];
		for(int i = 0; i < recordSize; i++)
		{
			int flag = 1 << i;
			if((mask & flag) == 0)
			{
				result = Math.max(result, savings[map[i]] - dist[map[pos]][map[i]] + tsp(i, mask | flag));
			}
		}
		return dp[pos][mask] = result;
	}
	
	public static int[][] floydWarshall(int[][] a)
	{
		int[][] dist = new int[a.length][a.length];
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
					dist[r][c] = a[r][c];
				else if(r != c)
					dist[r][c] = (int)1e9;
			}
		
		for(int k = 0; k < a.length; k++)
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a.length; c++)
					dist[r][c] = Math.min(dist[r][c], dist[r][k] + dist[k][c]);
		
		return dist;
	}
	
	public static int loadDecimal(String s)
	{
		StringTokenizer st = new StringTokenizer(s, "\\.");
		return Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
	}
}