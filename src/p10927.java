import java.util.*;
import java.io.*;

public class p10927
{
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
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
						return a.y - b.y;
					return a.x - b.x;
				}
			});
			for(int i = 0; i < a.length - 1; i++)
			{
				if(a[i].collinear(a[i + 1]) && a[i].h >= a[i + 1].h)
					covered.add(a[i + 1]);
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
		int x, y, h;
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
		
		public int compareTo(Point p)
		{
			int temp;
			if(p.x == 0 && x == 0)
				temp = 0;
			else if(p.x == 0)
				temp = 
			int dx = Math.abs(x) - Math.abs(p.x);
			int dy = Math.abs(y) - Math.abs(p.y);
			return temp != 0 ? temp : dx != 0 ? dx : dy;
		}
		
		public String toString()
		{
			return String.format("x = %d, y = %d", x, y);
		}
	}
}