import java.util.*;
import java.io.*;


public class p10131 
{
	static ArrayList<Point> list = new ArrayList<Point>(1000);
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String s = f.readLine(); s != null && !s.isEmpty(); s = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(s);
			int w = Integer.parseInt(st.nextToken());
			int iq = Integer.parseInt(st.nextToken());
			list.add(new Point(w, iq));
		}
		
		Collections.sort(list, new Comparator<Point>(){
			public int compare(Point a, Point b)
			{
				return b.iq - a.iq;
			}
		});
		int[] dp = new int[list.size()];
		int[] prevs = new int[list.size()];
		dp[0] = 1;
		prevs[0] = -1;
		int bestIndex = 0;
		for(int i = 1; i < list.size(); i++)
		{
			int best = 1;
			int prev = -1;
			for(int j = 0; j < i; j++)
			{
				if(list.get(j).iq > list.get(i).iq && list.get(j).w < list.get(i).w)
				{
					if(dp[j] + 1 > best)
					{
						best = dp[j] + 1;
						prev = j;
					}
				}
			}
			dp[i] = best;
			prevs[i] = prev;
			if(dp[bestIndex] < dp[i])
				bestIndex = i;
		}
		int[] result = new int[dp[bestIndex]];
		int index = 0;
		for(int i = bestIndex; i >= 0; i = prevs[i])
			result[index++] = list.get(i).index;
		System.out.println(result.length);
		for(int i = result.length - 1; i >= 0; i--)
			System.out.println(result[i]);
	}
	
	static class Point
	{
		int w, iq, index;
		static int i = 0;
		public Point(int w, int iq)
		{
			this.w = w;
			this.iq = iq;
			this.index = ++i;
		}
	}
}
