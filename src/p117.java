import java.util.*;
import java.io.*;

public class p117 
{
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		outer:
		while(true)
		{
			int cost = 0;
			int[] degree = new int[26];
			int[][] best = new int[26][26];
			for(int[] temp : best)
				Arrays.fill(temp, INF);
			for(String input = f.readLine();; input = f.readLine())
			{
				if(input == null)
					break outer;
				if(input.equals("deadend"))
					break;
				int len = input.length();
				int u = input.charAt(0) - 'a';
				int v = input.charAt(input.length() - 1) - 'a';
				degree[u]++; degree[v]++;
				best[u][v] = best[v][u] = len;
				cost += len;
			}
			int u = -1;
			int v = -1;
			for(int i = 0; i < degree.length; i++)
			{
				if(degree[i] % 2 != 0)
				{
					if(u < 0)
						u = i;
					else
					{
						v = i;
						break;
					}
				}
			}
			if(v >= 0)
				cost += floydWarshall(best, u, v);
			System.out.println(cost);
		}
	}
	
	static int floydWarshall(int[][] a, int u, int v)
	{
		for(int r = 0; r < a.length; r++)
			a[r][r] = 0;
		
		int n = a.length;
		for(int k = 0; k < n; k++)
			for(int r = 0; r < n; r++)
				for(int c = 0; c < n; c++)
					a[r][c] = Math.min(a[r][c], a[r][k] + a[k][c]);
		return a[u][v];
	}
}