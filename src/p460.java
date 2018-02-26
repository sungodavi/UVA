import java.util.*;
import java.io.*;

public class p460 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			f.readLine();
			Rect r1 = new Rect(f.readLine());
			Rect r2 = new Rect(f.readLine());
			int x1 = Math.max(r1.x1, r2.x1);
			int y1 = Math.max(r1.y1, r2.y1);
			int x2 = Math.min(r1.x2, r2.x2);
			int y2 = Math.min(r1.y2, r2.y2);
			if(x1 < x2 && y1 < y2)
				out.printf("%d %d %d %d\n", x1, y1, x2, y2);
			else
				out.println("No Overlap");
		}
		out.close();
	}
	
	static class Rect
	{
		int x1, y1, x2, y2;		
		public Rect(String s)
		{
			StringTokenizer st = new StringTokenizer(s);
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
		}
		
		public static boolean intersects(int a, int b, int c)
		{
			return a <= b && b <= c;
		}
		
		public static boolean intersects(int a, int b, int c, int d)
		{
			return intersects(a, c, b) || intersects(a, d, b);
		}
		
		public boolean intersects(Rect r)
		{
			return intersects(x1, x2, r.x1, r.x2) && intersects(y1, y2, r.y1, r.y2);
		}
		
		public boolean inside(Rect r)
		{
			return r.x1 <= x1 && r.x2 >= x2 && r.y1 <= y1 && r.y2 >= y2;
		}
		
		public String toString()
		{
			return x1 + " " + y1 + " " + x2 + " " + y2;
		}
	}
}