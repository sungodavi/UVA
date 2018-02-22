import java.util.*;
import java.io.*;

public class p10005 
{
	public static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				a[i] = new Point(x, y);
			}
			double r = Double.parseDouble(f.readLine());
			if(check(a, r))
				out.println("The polygon can be packed in the circle.");
			else
				out.println("There is no way of packing that polygon.");
		}
		out.close();
	}
	
	public static boolean check(Point[] a, double r)
	{
		for(int i = 0; i < a.length - 1; i++)
			for(int j = 0; j < a.length; j++)
			{
				if(i != j)
				{
					Point center = center(a[i], a[j], r);
					if(center != null && inside(a, center, r))
						return true;
				}
			}
		return false;
	}
	
	public static boolean inside(Point[] a, Point p, double r)
	{
		for(Point temp : a)
			if(!inside(temp, p, r))
				return false;
		return true;
	}
	
	public static boolean inside(Point a, Point p, double r)
	{
		//return dist(a, p) <= r * r;
		return dist(a, p) - r * r < EPS;
	}
	
	public static Point center(Point a, Point b, double r)
	{
		if(dist(a, b) > 4 * r * r)
			return null;
		Point m = mid(a, b);
		Vector v = new Vector(m, a);
		v.rotate();
		double u = Math.sqrt((r * r - dist(a, m))  / v.normSq());
		v.scale(u);
		return m.translate(v);
	}
	
	static double dist(Point a, Point b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
	
	static Point mid(Point a, Point b)
	{
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}
	
	static class Vector
	{
		double x, y;
		public Vector(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		public double normSq()
		{
			return x * x + y * y;
		}
		
		public void rotate()
		{
			double temp = y;
			y = x;
			x = -temp;
		}
		
		public void scale(double u)
		{
			x *= u;
			y *= u;
		}
		
		public String toString()
		{
			return String.format("%.3f %.3f", x, y);
		}
	}
	
	static class Point
	{
		double x, y;
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		public String toString()
		{
			return String.format("%.3f %.3f", x, y);
		}
	}
}