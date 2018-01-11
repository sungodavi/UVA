import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10496
{
	static int[][] dp;
	static Point[] a;
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
			Point start = load(f.readLine());
			a = new Point[1 + Integer.parseInt(f.readLine())];
			a[0] = start;
			for(int i = 1; i < a.length; i++)
				a[i] = load(f.readLine());
			dp = new int[a.length][1 << a.length];
			for(int[] temp : dp)
				Arrays.fill(temp, -1);
			OK = (1 << a.length) - 1;
			out.printf("The shortest path has length %d\n", tsp(0, 1));
		}
		out.close();
	}
	
	public static Point load(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		return new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
	
	public static int tsp(int pos, int mask)
	{
		if(mask == OK)
			return dist(pos, 0);
		
		if(dp[pos][mask] != -1)
			return dp[pos][mask];
		
		int result = Integer.MAX_VALUE;		
		for(int i = 0; i < a.length; i++)
		{
			int flag = 1 << i;
			if((mask & flag) == 0)
			{
				result = Math.min(result, dist(pos, i) + tsp(i, mask | flag));
			}
		}
		return dp[pos][mask] = result;
	}
	
	public static int dist(int i, int j)
	{
		Point p1 = a[i];
		Point p2 = a[j];
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}