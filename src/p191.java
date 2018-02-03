import java.util.*;
import java.io.*;

public class p191
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
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
			else
			{
				a = (p1.y - p2.y) / (p2.x - p1.x);
				b = 1;
				c = a * p1.x - p1.y;
			}
		}
		
		
		public static boolean intersects(Line l)
		{
			return true; //asdf
		}
		
		public boolean inRange(Point p)
		{
			return (p1.x <= p.x && p.x <= p2.x || p2.x <= p.x && p.x <= p1.x)
					&& (p1.y <= p.y && p.y <= p2.y || p2.y <= p.y && p.y <= p1.y);
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
	}
}