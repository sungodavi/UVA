public class Geometry 
{
	static class Point
	{
		double x, y;
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public Point(Point p)
		{
			x = p.x;
			y = p.y;
		}
		
		public static double dist(Point a, Point b)
		{
			double dx = a.x - b.x;
			double dy = a.y - b.y;
			return Math.sqrt(dx * dx + dy * dy);
		}
		
		public static Point translate(Point p, Vector v)
		{
			return new Point(p.x + v.x, p.y + v.y);
		}
	}
	
	static class Line
	{
		double a, b, c;
		public Line(Point p1, Point p2)
		{
			if(p1.x == p2.x)
			{
				a = 1;
				b = 0;
				c = -p1.x;
			}
			else if(p1.y == p2.y)
			{
				a = 0;
				b = 1;
				c = -p1.y;
			}
			else
			{
				a = -(p2.y - p1.y);
				b = -(p2.x - p1.x);
				c = -(p1.x * a + p1.y * b);
			}
		}
		
		public Line(Point p1, double m)
		{
			a = -m;
			b = 1;
			c = -(m * p1.x + p1.y);
		}
		
		public boolean isParallel(Line l)
		{
			return a == l.a && b == l.b;
		}
		
		public boolean equals(Line l)
		{
			return isParallel(l) && c == l.c;
		}
		
		public static boolean areIntersect(Line l1, Line l2, Point result)
		{
			if(l1.isParallel(l2))
				return false;
			result.x = (l1.c * l2.b - l2.c * l1.b) / (l2.a * l1.b - l1.a * l2.b);
			if(l1.b != 0)
				result.y = (l1.c + result.x * l1.a) / -l1.b;
			else
				result.y = (l2.c + result.x * l2.a) / -l2.b;
			return true;
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
		
		public Vector(Point p1, Point p2)
		{
			x = p2.x - p1.x;
			y = p2.y - p1.y;
		}
		
		public void scale(double u)
		{
			x *= u;
			y *= u;
		}
		
		public static Vector scale(Vector v, double u)
		{
			return new Vector(v.x * u, v.y * u);
		}
		
		public void translate(double c)
		{
			x += c;
			y += c;
		}
		
		public static double dot(Vector a, Vector b)
		{
			return a.x * b.x + a.y + b.y;
		}
		
		public static double normSquared(Vector v)
		{
			return v.x * v.x + v.y * v.y;
		}
		
		public static double distToLine(Point p, Point a, Point b)
		{
			Vector ap = new Vector(a, p);
			Vector ab = new Vector(a, b);
			double u = dot(ap, ab) / normSquared(ab);
			Point c = new Point(a);
			c = Point.translate(a, scale(ab, u));
			return Point.dist(p, c);
		}
	}
}
