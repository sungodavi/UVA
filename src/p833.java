import java.util.*;
import java.io.*;

public class p833
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			f.readLine();
			int size = Integer.parseInt(f.readLine());
			Segment[] a = new Segment[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				a[i] = new Segment(load(st), load(st));
			}
			Arrays.sort(a);
			int queries = Integer.parseInt(f.readLine());
			while(queries-- > 0)
			{
				Point p = load(new StringTokenizer(f.readLine()));
				out.println(run(p, a));
			}
		}
		out.close();
	}
	
	public static int run(Point p, Segment[] a)
	{
		boolean done = false;
		boolean[] visited = new boolean[a.length];
		while(!done)
		{
			done = true;
			for(int i = 0; i < a.length; i++)
			{
				if(!visited[i] && a[i].intersects(p))
				{
					done = false;
					visited[i] = true;
					p = a[i].b;
					break;
				}
			}
		}
		return p.x;
	}
	
	public static Point load(StringTokenizer st)
	{
		return new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
	}
	
	static class Segment implements Comparable<Segment>
	{
		Point a, b;
		public Segment(Point a, Point b)
		{
			if(a.y < b.y)
			{
				Point temp = a;
				a = b;
				b = temp;
			}
			this.a = a;
			this.b = b;
		}
		
		public boolean intersects(Point p)
		{
			if(!contains(a.x, p.x, b.x))
				return false;
			long A = a.y - b.y;
			long B = b.x - a.x;
			long C = -(a.x * A + a.y * B);
			return B > 0 ? A * p.x + C >= -B * p.y : A * p.x + C <= -B * p.y;
		}
		
		public static boolean contains(int a, int b, int c)
		{
			return a <= b && b <= c || c <= b && b <= a;
		}
		
		public int compareTo(Segment s)
		{
			return s.b.x - b.x;
		}
	}
	
	static class Point
	{
		int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public String toString()
		{
			return x + " " + y;
		}
	}
}