import java.util.*;
import java.io.*;

public class p10104
{
	static int x, y, d;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			euclid(a, b);
			System.out.printf("%d %d %d\n", x, y, d);
		}
	}
	
	public static void euclid(int a, int b)
	{
		if(b == 0)
		{
			d = a;
			x = 1;
			y = 0;
			return;
		}
		euclid(b, a % b);
		int x1 = y;
		int y1 = x - (a / b) * y;
		x = x1;
		y = y1;
	}
}