import java.util.*;
import java.io.*;

public class p11284
{
	static int[][] dist;
	static int[][] dp;
	static int OK;
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
			
			int[][] floyd = floydWarshall(a);
			
			int items = Integer.parseInt(f.readLine());
			int amazon = 0;
			
			TreeSet<Integer> set = new TreeSet<Integer>();
			while(items-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int id = Integer.parseInt(st.nextToken());
				set.add(id);
				amazon += loadDecimal(st.nextToken());
			}
			dist = new int[set.size() + 1][set.size() + 1];
			dp = new int[set.size() + 1][1 << set.size() + 1];
			for(int[] temp : dp)
				Arrays.fill(temp, -1);
			OK = dp[0].length - 1;
			int[] map = new int[set.size() + 1];
			int index = 1;
			for(int num : set)
				map[index++] = num;
			
			for(int r = 0; r < dist.length; r++)
				for(int c = r + 1; c < dist.length; c++)
					dist[r][c] = dist[c][r] = floyd[map[r]][map[c]];
					
			int stores = tsp(0, 1);
			if(stores < amazon)
			{
				int difference = amazon - stores;
				System.out.printf("Daniel can save $%d.%02d\n", difference / 100, difference % 100);
			}
			else
				System.out.println("Don't leave the house");
		}
		out.close();
	}
	
	public static int tsp(int pos, int mask)
	{
		if(mask == OK)
			return dist[pos][0];
		
		//System.out.println(pos + " " + Integer.toBinaryString(mask));
		if(dp[pos][mask] != -1)
			return dp[pos][mask];
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < dist.length; i++)
		{
			int flag = 1 << i;
			if((mask & flag) == 0)
				result = Math.min(result, dist[pos][i] + tsp(i, mask | flag));
		}
		if(result == Integer.MAX_VALUE)
			System.out.println("Error: " + pos + " " + Integer.toBinaryString(mask));
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