import java.util.*;
import java.io.*;

public class p10263
{
	static Point c;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			Point m = new Point(Double.parseDouble(input), Double.parseDouble(f.readLine()));
			Point[] a = new Point[Integer.parseInt(f.readLine()) + 1];
			for(int i = 0; i < a.length; i++)
				a[i] = new Point(Double.parseDouble(f.readLine()), Double.parseDouble(f.readLine()));
			double best = Double.MAX_VALUE;
			Point p = null;
			for(int i = 0; i < a.length - 1; i++)
			{
				//System.out.println(a[i] + "  " + a[i + 1]);
				double d = dist(a[i], a[i + 1], m);
				//System.out.println(d + " " + c);
				if(d < best)
				{
					best = d;
					p = c;
				}
			}
			System.out.printf("%.4f\n%.4f\n", p.x, p.y);
		}
		out.close();
	}
	
	public static double dist(Point a, Point b, Point p)
	{
		Vector ab = new Vector(a, b);
		Vector ap = new Vector(a, p);
		double u = Vector.dot(ab, ap) / ab.normSq();
		//System.out.println(u);
		if(u > 1)
		{
			c = b;
			return dist(p, b);
		}
		if(u < 0)
		{
			c = a;
			return dist(p, a);
		}
		c = a.translate(ab.scale(u));
		return dist(p, c);
	}
	
	
	static double dist(Point a, Point b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return dx * dx + dy * dy;
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
			return x + " " + y;
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
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		public Vector scale(double u)
		{
			return new Vector(x * u, y * u);
		}
		
		public static double dot(Vector a, Vector b)
		{
			return a.x * b.x + a.y * b.y;
		}
		
		public double normSq()
		{
			return x * x + y * y;
		}
	}
}