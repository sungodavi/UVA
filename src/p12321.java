import java.util.*;
import java.io.*;
import java.awt.Point;

public class p12321 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			int l = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Point[] a = new Point[size];
			for(int i = 0; i < size; i++)
			{
				st = new StringTokenizer(f.readLine());
				int x = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				a[i] = new Point(x - r, x + r);
			}
			Arrays.sort(a, new Comparator<Point>() {
				public int compare(Point a, Point b)
				{
					if(a.x == b.x)
						return a.y - b.y;
					return a.x - b.x;
				}
			});
			
			int currRight = 0;
			int i = 0;
			boolean check = true;
			int count = 0;
			while(currRight < l && check)
			{
				check = false;
				int best = -1;
				int j;
				for(j = i; j < a.length && a[j].x <= currRight; j++)
				{
					check = true;
					if(best < 0 || a[best].y < a[j].y)
						best = j;
				}
				i = j;
				if(best >= 0)
				{
					++count;
					currRight = a[best].y;
				}
			}
			if(currRight < l)
				out.println(-1);
			else
				out.println(a.length - count);
		}
		out.close();
	}
}