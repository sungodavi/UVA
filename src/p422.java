import java.util.*;
import java.io.*;
import java.awt.Point;

public class p422
{
	static char[][] a;
	static int[] dx = {1, 1, 1, 0, 0, -1, -1};
	static int[] dy = {1, 0, -1, 1, -1, 1, -1};
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int size = Integer.parseInt(f.readLine());
		a = new char[size][];
		for(int r = 0; r < size; r++)
			a[r] = f.readLine().toCharArray();
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			Point[] result = find(input);
			if(result == null)
				out.println("Not found");
			else
				out.printf("%d,%d %d,%d\n", result[0].x + 1, result[0].y + 1, result[1].x + 1, result[1].y + 1);
		}
		out.close();
		
	}
	
	public static Point[] find(String s)
	{
		for(int r = 0; r < a.length; r++)
		{
			for(int c = 0; c < a[0].length; c++)
			{
				for(int k = 0; k < dx.length; k++)
				{
					if(check(s, r, c, dx[k], dy[k]))
					{
						Point a = new Point(r, c);
						Point b = new Point(r + dx[k] * (s.length() - 1), c + dy[k] * (s.length() - 1));
						return new Point[]{a, b};
					}
				}
			}
		}
		return null;
	}
	
	public static boolean check(String s, int r, int c, int dx, int dy)
	{
		int count;
		for(count = 0; count < s.length() 
				&& r >= 0 
				&& r < a.length 
				&& c >= 0 
				&& c < a[0].length 
				&& a[r][c] == s.charAt(count); r += dx, c += dy, count++);
		return count == s.length();
	}
}