import java.util.*;
import java.io.*;
import java.awt.Point;

public class p216
{
	static int OK;
	static double[][] dist;
	static double[][] dp;
	static Point[] points;
	static PrintWriter out;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			out.println("**********************************************************");
			out.printf("Network #%d\n", ++caseNo);
			dist = new double[size][size];
			dp = new double[size][1 << size];
			points = new Point[size];
			for(int i = 0; i < points.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				points[i] = new Point(x, y);
			}
			for(int r = 0; r < size; r++)
				for(int c = r + 1; c < size; c++)
					dist[r][c] = dist[c][r] = distance(points[r], points[c]) + 16;
			
			for(double[] temp : dp)
				Arrays.fill(temp, -1);
			OK = (1 << size) - 1;
			
			int best = 0;
			double route = Double.MAX_VALUE;
			for(int i = 0; i < size; i++)
			{
				double path = tsp(i, 1 << i);
				if(path < route)
				{
					best = i;
					route = path;
				}
			}
			rebuild(best, 1 << best);
			out.printf("Number of foot of cable required is %.2f\n", route);
		}
		out.close();
	}
	
	public static void rebuild(int pos, int mask)
	{
		if(mask == OK)
			return;
		for(int i = 0; i < dist.length; i++)
		{
			int flag = 1 << i;
			if((mask & flag) == 0 && tsp(i, mask | flag) + dist[pos][i] == dp[pos][mask])
			{
				out.println(new State(points[pos], points[i], dist[pos][i]));
				rebuild(i, mask | flag);
				return;
			}
		}
	}
	
	public static double tsp(int pos, int mask)
	{
		if(mask == OK)
			return 0;
		if(dp[pos][mask] != -1)
			return dp[pos][mask];
		double result = Double.MAX_VALUE;
		for(int index = 0; index < dist.length; index++)
		{
			int flag = 1 << index;
			if((flag & mask) == 0)
			{
				result = Math.min(result, dist[pos][index] + tsp(index, mask | flag));
			}
		}
		return dp[pos][mask] = result;
			
	}
	
	public static double distance(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	static class State
	{
		Point a, b;
		double dist;
		
		public State(Point a, Point b, double dist)
		{
			this.a = a;
			this.b = b;
			this.dist = dist;
		}
		
		public String toString()
		{
			return String.format("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.", a.x, a.y, b.x, b.y, dist);
		}
	}
}