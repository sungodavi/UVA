import java.util.*;
import java.io.*;
import java.awt.Point;

public class p920 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			Point[] a = new Point[Integer.parseInt(f.readLine())];
			for(int i = 0; i < a.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y);
			}
			Arrays.sort(a, new Comparator<Point>() {
				public int compare(Point a, Point b)
				{
					return a.x - b.x;
				}
			});
			
//			System.out.println(Arrays.toString(a));
			
			int y = 0;
			double result =0;
			for(int i = a.length - 2; i >= 0; i--)
			{
				Point p = a[i];
				if(p.y > y)
				{
//					System.out.println(p + " " + a[i + 1]);
//					System.out.println(dist(a[i], a[i + 1], y));
					result += dist(p, a[i + 1], y);
					y = p.y;
				}
			}
			System.out.printf("%.2f\n", result);
		}
	}
	
	public static double dist(Point p1, Point p2, int y)
	{
		double m = (double)(p1.y - p2.y) / (p1.x - p2.x);
		double b = p1.y - m * p1.x;
		double x = (y - b) / m;
		
		double dx = p1.x - x;
		double dy = p1.y - y;
		
//		System.out.println(m + " " + b + " " + x + " " + dx + " " + dy);
		
		return Math.sqrt(dx * dx + dy * dy);
	}
}