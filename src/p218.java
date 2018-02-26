import java.util.*;
import java.io.*;

public class p218 
{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			if(caseNo > 0)
				out.println();
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				a[i] = new Point(x, y);
			}
			if(size == 1)
			{
				out.printf("Region #%d:\n%s-%s\nPerimeter length = 0.00\n", ++caseNo, a[0], a[0]);
				continue;
			}
			Point pivot = a[0];
			for(int i = 1; i < size; i++)
				if(a[i].y < pivot.y || a[i].y == pivot.y && a[i].x < pivot.x)
					pivot = a[i];
			Arrays.sort(a, new C(pivot));
			ArrayList<Point> result = new ArrayList<Point>();
			result.add(a[0]);
			result.add(a[1]);
			for(int i = 2; i < size; i++)
			{
				while(result.size() >= 2 && !ccw(result.get(result.size() - 2), result.get(result.size() - 1), a[i]))
					result.remove(result.size() - 1);
				result.add(a[i]);
			}
			out.printf("Region #%d:\n", ++caseNo);
			for(int i = result.size() - 1; i >= 0; i--)
				out.printf("%s-", result.get(i));
			out.println(result.get(result.size() - 1));
			out.printf("Perimeter length = %.2f\n", perimeter(result));
		}
		out.close();
	}
	
	public static double perimeter(ArrayList<Point> list)
	{
		double p = 0;
		for(int i = 0; i < list.size() - 1; i++)
			p += dist(list.get(i), list.get(i + 1));
		return p + dist(list.get(0), list.get(list.size() - 1));
	}
	
	public static Point toVec(Point a, Point b)
	{
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	public static double cross(Point a, Point b)
	{
		return a.x * b.y - b.x * a.y;
	}
	
	public static boolean ccw(Point p, Point q, Point r)
	{
		return cross(toVec(p, q), toVec(p, r)) > 0;
	}
	
	public static double dist(Point a, Point b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
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
			return String.format("(%.1f,%.1f)", x, y);
		}
	}
	
	static class C implements Comparator<Point>
	{
		Point pivot;
		public C(Point pivot)
		{
			this.pivot = pivot;
		}
		
		public int compare(Point a, Point b)
		{
			double cross = cross(toVec(pivot, a), toVec(pivot, b));
			if(cross == 0)
				return Double.compare(dist(pivot, a), dist(pivot, b));
			return cross > 0 ? -1 : 1;
		}
	}
}