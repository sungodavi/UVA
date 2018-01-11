import java.util.*;
import java.io.*;

public class p990 //START OVER
{
	static int[][] dp;
	static Point[][] next;
	static Gold[] a;
	static int w;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		boolean flag = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(flag)
				out.println();
			else
				flag = true;
			
			st = new StringTokenizer(input);
			int size = Integer.parseInt(st.nextToken()) + 1;

			w = 3 * Integer.parseInt(st.nextToken());
			
			a = new Gold[Integer.parseInt(f.readLine())];
			for(int i = 0; i < a.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int d = Integer.parseInt(st.nextToken());
				int amt = Integer.parseInt(st.nextToken());
				a[i] = new Gold(d, amt);
			}
			dp = new int[a.length][size];
			next = new Point[a.length][size];
			f.readLine();
			int result = recurse(0, 0);
			out.println(result);
			
			Queue<Gold> q = new LinkedList<Gold>();
			int r = 0;
			int c = 0;
			
			while(true)
			{
				if(r < 0 || c < 0 || next[r][c].g == null)
					break;
				q.add(next[r][c].g);
				int nextR = next[r][c].r;
				int nextC = next[r][c].c;
				r = nextR;
				c = nextC;
			}
			out.println(q.size());
			while(!q.isEmpty())
				out.println(q.poll());
		}
		out.close();
	}
	
	static int recurse(int index, int depth)
	{
		if(index == a.length)
			return 0;
		if(dp[index][depth] > 0)
			return dp[index][depth];
		int max = 0;
		
		int r = -1, c = -1;
		Gold g = null;
		for(int i = index; i < a.length; i++)
		{
			int newDepth = depth + a[i].depth * w;
			if(newDepth < dp[0].length)
			{
				int result = recurse(i + 1, newDepth) + a[i].amount;
				if(result > max)
				{
					max = result;
					r = i + 1;
					c = newDepth;
					g = a[i];
				}
			}
		}
		next[index][depth] = new Point(r, c, g);
		return max;
	}
	
	static class Point
	{
		int r, c;
		Gold g;
		
		public Point(int r, int c, Gold g)
		{
			this.r = r;
			this.c = c;
			this.g = g;
		}
		
		public String toString()
		{
			return r + " " + c + " " + g.toString();
		}
	}
	static class Gold
	{
		int depth, amount;
		
		public Gold(int depth, int amount)
		{
			this.depth = depth;
			this.amount = amount;
		}
		
		public String toString()
		{
			return depth + " " + amount;
		}
	}
}