import java.util.*;
import java.io.*;

public class p10589
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			Box b = new Box(a);
			int m = 0;
			for(int i = 0; i < n; i++)
			{
				st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				if(b.inside(new Point(x, y)))
					m++;
			}
			out.printf("%.5f\n", (double)m * a * a / n);
		}
		out.close();
	}
	
	static class Box
	{
		Circle[] corners;
		public Box(long a)
		{
			corners = new Circle[]{new Circle(0, 0, a), new Circle(0, a, a), new Circle(a, 0, a), new Circle(a, a, a)};
		}
		
		public boolean inside(Point p)
		{
			for(Circle c : corners)
				if(!c.inside(p))
					return false;
			return true;
		}
	}
	
	static class Circle
	{
		long x, y, r;
		public Circle(long x, long y, long r)
		{
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		public boolean inside(Point p)
		{
			double dx = x - p.x;
			double dy = y - p.y;
			return dx * dx + dy * dy <= r * r;
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