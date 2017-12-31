import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10911 
{
	static double dp[];
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int count = 1;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			Point[] a = new Point[size << 1];
			for(int i = 0; i < a.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y);
			}
			dp = new double[(1 <<(size << 1))];
			for(int i = 3; i < dp.length; i++)
			{
				int bits = countBits(i);
				double best = Double.MAX_VALUE;
				if((bits & 1) == 0)
				{
					for(int j = 1, index1 = 0; j < i; j <<= 1, index1++)
					{
						if((i & j) > 0)
						{
							for(int k = j << 1, index2 = index1 + 1; k <= i; k <<= 1, index2++)
							{
								//System.out.println(Integer.toBinaryString(i) + " " + Integer.toBinaryString(j) + " " + Integer.toBinaryString(k));
								if((i & k) > 0)
								{
									//System.out.println(Integer.toBinaryString(i));
									//System.out.println(Integer.toBinaryString(i - j - k));
									best = Math.min(best, dp[i - j - k] + distance(a[index1], a[index2]));
								}
							}
						}
					}
					dp[i] = best;
				}
			}
			//System.out.println(Arrays.toString(dp));
			System.out.printf("Case %d: %.2f\n", count++, dp[dp.length - 1]);
		}
	}
	
	public static double distance(Point a, Point b)
	{
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public static int countBits(int num)
	{
		int count = 0;
		while(num > 0)
		{
			count++;
			num &= num - 1;
		}
		return count;
	}
}