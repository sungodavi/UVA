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
		
	}
	
	public static double dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
}