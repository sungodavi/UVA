import java.util.*;
import java.io.*;
import java.awt.Point;

public class p10203
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		f.readLine();
		for(int t = 0; t < times; t++)
		{
			if(t > 0)
				out.println();
			f.readLine();
			double dist = 0;
			for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
			{
				st = new StringTokenizer(input);
				Point a = load(st);
				Point b = load(st);
				dist += 2 * dist(a, b);
			}
			double time = dist / 20000;
			long hours = (long)time;
			long min = (long)Math.round((time - hours) * 60);
			if(min >= 60)
			{
				hours += min / 60;
				min %= 60;
			}
			out.printf("%d:%02d\n", hours, min);
		}
		out.close();
	}
	
	public static Point load(StringTokenizer st)
	{
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		return new Point(x, y);
	}
	
	public static double dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
}