import java.util.*;
import java.io.*;

public class p11152
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			double big = circle(circumCircle(a, b, c));
			double tri = herons(a, b, c);
			double in = circle(inCircle(a, b, c));
			out.printf("%.4f %.4f %.4f\n", big - tri, tri - in, in);
		}
		out.close();
	}
	
	public static double inCircle(long a, long b, long c)
	{
		return herons(a, b, c) / (a + b + c >> 1);
	}
	
	public static double circumCircle(long a, long b, long c)
	{
		return a * b * c / (4 * herons(a, b, c));
	}
	
	public static double herons(long a, long b, long c)
	{
		long s = a + b + c >> 1;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}
	
	public static double circle(double r)
	{
		return Math.PI * r * r;
	}
}