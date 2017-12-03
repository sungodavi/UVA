import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;
public class p10135 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			f.readLine();
			int size = Integer.parseInt(f.readLine());
			Point[] a = new Point[size + 1];
			a[0] = new Point(0, 0);
			for(int i = 1; i <= size; i++)
			{
				StringTokenizer st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				a[i] = new Point(x, y);
			}
			Arrays.sort(a);
			double cost = 0;
			for(int i = 0; i < a.length; i++)
			{
				cost += a[i].distance(a[(i + 1) % a.length]);
			}
			System.out.println(cost);
		}
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y, angle;
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
			angle = Math.atan2(y, x);
			if(angle < 0)
				angle += 2 * Math.PI;
		}
		
		public int compareTo(Point p)
		{
			return Double.compare(angle, p.angle);
		}
		
		public double distance(Point p)
		{
			return Point2D.distance(x, y, p.x, p.y);
		}
	}
}