import java.util.*;
import java.io.*;

public class p10927
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int caseNo = 1;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				a[i] = new Point(x, y, h);
			}
			Arrays.sort(a);
			TreeSet<Point> covered = new TreeSet<Point>(new Comparator<Point>() {
				public int compare(Point a, Point b)
				{
					if(a.x == b.x)
						return Long.compare(a.y, b.y);
					return Long.compare(a.x, b.x);
				}
			});
			Point curr = a[0];
			for(int i = 1; i < a.length; i++)
			{
				if(curr.collinear(a[i]))
				{
					if(curr.h >= a[i].h)
						covered.add(a[i]);
					else
						curr = a[i];
				}
				else
					curr = a[i];
			}
			out.printf("Data set %d:\n", caseNo++);
			if(covered.isEmpty())
			{
				out.println("All the lights are visible.");
			}
			else
			{
				out.println("Some lights are not visible:");
				int count = 0;
				for(Point p : covered)
				{
					out.print(p);
					if(count == covered.size() - 1)
						out.println(".");
					else
						out.println(";");
					count++;
				}
			}
		}
		out.close();
	}
	
	static class Point implements Comparable<Point>
	{
		long x, y, h;
		public Point(int x, int y, int h)
		{
			this.x = x;
			this.y = y;
			this.h = h;
		}
		
		public boolean collinear(Point p)
		{
			return p.x * y == p.y * x;
		}
		
		public long dist()
		{
			return x * x + y * y;
		}
		public int compareTo(Point p)
		{
			if(collinear(p))
				return Long.compare(dist(), p.dist());
			return Long.compare(x * p.y, y * p.x);
		}
		public String toString()
		{
			//return x + " " + y + " " + h;
			return String.format("x = %d, y = %d", x, y);
		}
	}
}