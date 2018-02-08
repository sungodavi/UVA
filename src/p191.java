import java.util.*;
import java.io.*;

public class p191
{
	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int x0 = Integer.parseInt(st.nextToken());
			int y0 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int xl = Integer.parseInt(st.nextToken());
			int yt = Integer.parseInt(st.nextToken());
			int xr = Integer.parseInt(st.nextToken());
			int yb = Integer.parseInt(st.nextToken());
			Line l = new Line(new Point(x0, y0), new Point(x1, y1));
			Rect rect = new Rect(xl, yt, xr, yb);
			out.println(rect.intersects(l) ? "T" : "F");
		}
		out.close();
	}
	
	static class Rect
	{
		Line top, bot, left, right;
		int xL, yT, xR, yB;
		public Rect(int xl, int yt, int xr, int yb)
		{
			xL = xl;
			yT = yt;
			xR = xr;
			yB = yb;
			top = new Line(new Point(xl, yt), new Point(xr, yt));
			bot = new Line(new Point(xl, yb), new Point(xr, yb));
			left = new Line(new Point(xl, yt), new Point(xl, yb));
			right = new Line(new Point(xr, yt), new Point(xr, yb));
		}
		
		public boolean inside(Point p)
		{
			return Line.inRange(xL, p.x, xR) && Line.inRange(yB, p.y, yT);
		}
		
		public boolean intersects(Line l)
		{
			Point p = new Point(0, 0);
			if(l.intersects(top))
				return true;
			if(l.intersects(bot))
				return true;
			if(l.intersects(left))
				return true;
			if(l.intersects(right))
				return true;
			return inside(l.p1) && inside(l.p2);
		}
	}
	
	static class Line
	{
		double a, b, c;
		Point p1, p2;
		public Line(Point p1, Point p2)
		{
			this.p1 = p1;
			this.p2 = p2;
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
				a = p1.y - p2.y;
				b = p2.x - p1.x;
				c = -a * p1.x - b * p1.y;
			}
		}
		
		public boolean intersects(Line l)
		{
			return intersects(l, new Point(0, 0));
		}
		
		public boolean intersects(Line l, Point p)
		{
			if(a * l.b == b * l.a)
				return inRange(l.p1) || inRange(l.p2);
			double d = a * l.b - l.a * b;
			double x = (l.c * b - c * l.b) / d;
			double y = -(l.c * a - c * l.a) / d;
			p = new Point(x, y);
			return inRange(p) && l.inRange(p);
		}
		
		public boolean inRange(Point p)
		{
			return inRange(p1.x, p.x, p2.x) && inRange(p1.y, p.y, p2.y);
		}
		
		public static boolean inRange(double a, double b, double c)
		{
			double min = Math.min(a, c);
			double max = Math.max(a, c);
			return min <= b && b <= max;
		}
		
		public String toString()
		{
			return String.format("%.3f + %.3f + %.3f = 0", a, b, c);
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
		
		public String toString()
		{
			return String.format("%.3f %.3f", x, y);
		}
	}
}