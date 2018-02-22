import java.util.*;
import java.io.*;

public class p10577 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			Point[] a = new Point[3];
			for(int i = 0; i < a.length; i++)
			{
				st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				a[i] = new Point(x, y);
			}
			out.printf("Polygon %d: %.3f\n", ++caseNo, solve(size, a));
		}
		out.close();
	}
	
	public static double solve(int size, Point[] a)
	{
		Point p = center(a[0], a[1], circumRadius(a[0], a[1], a[2]));
		double rad = Math.PI * 2 / size;
		double minX = a[0].x, minY = a[0].y, maxX = a[0].x, maxY = a[0].y;
		Vector v = new Vector(p, a[0]);
		for(int i = 1; i < size; i++)
		{
			v = v.rotate(rad);
			Point temp = p.translate(v);
			minX = Math.min(minX, temp.x);
			maxX = Math.max(maxX, temp.x);
			minY = Math.min(minY, temp.y);
			maxY = Math.max(maxY, temp.y);
		}
		return (maxX - minX) * (maxY - minY);
	}
	
	public static Point center(Point a, Point b, double r)
	{
		Point m = mid(a, b);
		Vector v = new Vector(m, a);
		v.rotate();
		double h = r * r - distSq(a, m);
		double u = Math.sqrt(h / v.normSq());
		v.scale(u);
		return m.translate(v);
	}
	
	public static Point mid(Point a, Point b)
	{
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}
	
	public static double circumRadius(Point a, Point b, Point c)
	{
		return circumRadius(dist(a, b), dist(b, c), dist(c, a));
	}
	
	public static double circumRadius(double a, double b, double c)
	{
		return a * b * c / 4 / area(a, b, c);
	}
	
	public static double area(double a, double b, double c)
	{
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}
	
	static double dist(Point a, Point b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	static double distSq(Point a, Point b) 
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
	
	static class Point
	{
		double x, y;
		public Point(double a, double b)
		{
			x = a;
			y = b;
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
			this(b.x - a.x, b.y - a.y);
		}
		
		public void scale(double u)
		{
			x *= u;
			y *= u;
		}
		
		public void rotate()
		{
			double temp = x;
			x = -y;
			y = temp;
		}
		
		public double normSq()
		{
			return x * x + y * y;
		}
		
		public Vector rotate(double rad)
		{
			double cos = Math.cos(rad);
			double sin = Math.sin(rad);
			return new Vector(x * cos - y * sin, x * sin + y * cos);
		}
		
		public String toString()
		{
			return String.format("%.3f %.3f", x, y);
		}
	}
}