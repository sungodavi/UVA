import java.util.*;
import java.io.*;
import java.awt.Point;

public class spoj0101
{
	static Point[][] dp;
	static int[][] cost, time;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			dp = new Point[size][limit + 1];
			cost = new int[size][size];
			time = new int[size][size];
			for(int r = 0; r < size; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
					time[r][c] = Integer.parseInt(st.nextToken());
			}
			f.readLine();
			for(int r = 0; r < size; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < size; c++)
					cost[r][c] = Integer.parseInt(st.nextToken());
			}
			Point result = recurse(0, 0);
			out.println(result.y + " " + result.x);
			f.readLine();
		}
		out.close();
	}
	
	public static Point recurse(int u, int t)
	{
		if(t >= dp[0].length)
			return new Point(INF, INF);
		if(u == dp.length - 1)
			return new Point(0, 0);
		if(dp[u][t] != null)
			return dp[u][t];
		int best = INF;
		int bestTime = INF;
		for(int v = 0; v < cost.length; v++)
		{
			if(u != v)
			{
				Point p = recurse(v, t + time[u][v]);
				if(p.y + cost[u][v] < best)
				{
					best = p.y + cost[u][v];
					bestTime = p.x + time[u][v];
				}
			}
		}
		return dp[u][t] = new Point(bestTime, best);
	}
}