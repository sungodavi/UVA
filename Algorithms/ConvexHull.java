import java.util.*;

public class ConvexHull 
{
	public static Point[] ch(Point[] a)
	{
		int n = a.length;
		Arrays.sort(a);
		Point[] result = new Point[n << 1];
		int k = 0;
		int start = 0;
		
		for(int i =0; i < n; i++)
		{
			Point p = a[i];
			while(k >= 2 && !ccw(result[k - 2], result[k - 1], p))
				k--;
			result[k++] = p;
		}
		
		start = --k;
		for(int i = n - 1; i >= 0; i--)
		{
			Point p = a[i];
			while(k - start >= 2 && !ccw(result[k - 2], result[k - 1], p))
				k--;
			result[k++] = p;
		}
		return Arrays.copyOf(result, k - 1);
	}
	
	public static boolean ccw(Point p, Point q, Point r)
	{
		Point pq = toVec(p, q);
		Point pr = toVec(p, r);
		return cross(pq, pr) > 0;
	}
	
	public static Point toVec(Point a, Point b)
	{
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	public static int cross(Point a, Point b)
	{
		return a.x * b.y - b.x * a.y;
	}
	static class Point implements Comparable<Point>
	{
		int x, y;
		public Point(int a, int b)
		{
			x = a;
			y = b;
		}
		
		public int compareTo(Point p)
		{
			if(x == p.x)
				return y - p.y;
			return x - p.y;
		}
	}
}
