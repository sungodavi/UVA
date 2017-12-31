import java.util.*;
import java.io.*;
import java.awt.Point;

public class p11517
{
	static int[] coins;
	static int target;
	static Point[][] dp;
	static C c;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			target = Integer.parseInt(f.readLine());
			coins = new int[Integer.parseInt(f.readLine())];
			for(int i = 0; i < coins.length; i++)
				coins[i] = Integer.parseInt(f.readLine());
			dp = new Point[coins.length][target];
			c = new C();
			Point result = recurse(0, 0, 0);
			System.out.printf("%d %d\n", result.x, result.y);
		}
	}
	
	public static Point recurse(int index, int val, int count)
	{
		if(val >= target)
			return new Point(val, count);
		if(index == coins.length)
			return null;
		if(dp[index][val] != null)
			return dp[index][val];
		Point best = null;
		for(int i = index; i < coins.length; i++)
		{
			Point p = recurse(i + 1, val + coins[i], count + 1);
			if(p != null && c.compare(p, best) < 0)
				best = p;
		}
		//System.out.println(index + " " + val + " " + count + " " + best);
		return dp[index][val] = best;
	}
	
	static class C implements Comparator<Point>
	{
		public int compare(Point a, Point b)
		{
			if(b == null)
				return -1;
			if(a.x == b.x)
				return a.y - b.y;
			return a.x - b.x;
		}
	}
}