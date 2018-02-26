import java.util.*;
import java.io.*;

public class p109 
{
	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException 
	{
		Scanner scan = new Scanner(System.in);
		ArrayList<Point[]> cities = new ArrayList<Point[]>();
		for(int size = scan.nextInt(); size != -1; size = scan.nextInt())
		{
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
				a[i] = new Point(scan.nextInt(), scan.nextInt());
			cities.add(ch(a));
		}
		boolean[] bombed = new boolean[cities.size()];
		while(scan.hasNext())
		{
			Point p = new Point(scan.nextInt(), scan.nextInt());
			for(int i = 0; i < cities.size(); i++)
			{
				if(!bombed[i] && inside(cities.get(i), p))
					bombed[i] = true;
			}
		}
		double area = 0;
		for(int i = 0; i < cities.size(); i++)
		{
			if(bombed[i])
				area += area(cities.get(i));
		}
		System.out.printf("%.2f\n", area);
	}
	
	static boolean inside(Point[] a, Point p)
	{
		double sum = 0;
		for(int i = 0; i < a.length - 1; i++)
		{
			if(ccw(a[i], a[i + 1], p))
				sum += angle(a[i], p, a[i + 1]);
			else
				sum -= angle(a[i], p, a[i + 1]);
		}
		return Math.abs(Math.abs(sum) - 2 * Math.PI) < EPS;
	}
	
	public static double angle(Point a, Point o, Point b)
	{
		Point oa = toVec(o, a), ob = toVec(o, b);
		return Math.acos(dot(oa, ob) / Math.sqrt(normSq(oa) * normSq(ob)));
	}
	
	public static double dot(Point a, Point b)
	{
		return a.x * b.x + a.y * b.y;
	}
	
	public static double normSq(Point a)
	{
		return a.x * a.x + a.y * a.y;
	}
	
	static double area(Point[] a)
	{
		double sum = 0;
		for(int i = 0; i < a.length - 1; i++)
		{
			sum += a[i].x * a[i + 1].y - a[i + 1].x * a[i].y;
		}
		return Math.abs(sum) / 2;
	}
	
	static Point[] ch(Point[] a)
	{
		Arrays.sort(a);
		int n = a.length;
		int k = 0;
		Point[] result = new Point[n << 1];
		for(int i = 0; i < n; i++)
		{
			while(k >= 2 && !ccw(result[k - 2], result[k - 1], a[i]))
				k--;
			result[k++] = a[i];
		}
		
		int start = k + 1;
		for(int i = n - 2; i >= 0; i--)
		{
			while(k >= start && !ccw(result[k - 2], result[k - 1], a[i]))
				k--;
			result[k++] = a[i];
		}
		return Arrays.copyOf(result, k);
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		Point pq = toVec(p, q), pr = toVec(p, r);
		return cross(pq, pr) > 0;
	}
	
	static int cross(Point a, Point b)
	{
		return a.x * b.y - a.y * b.x;
	}
	
	static Point toVec(Point a, Point b)
	{
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Point p)
		{
			if(x == p.x)
				return y - p.y;
			return x - p.x;
		}
		
		public String toString()
		{
			return String.format("(%d,%d)", x, y);
		}
	}
}