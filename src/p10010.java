import java.util.*;
import java.io.*;

public class p10010
{
	public static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
	public static int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
	static char[][] a;
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
			st = new StringTokenizer(f.readLine());
			a = new char[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			for(int r = 0; r < a.length; r++)
				a[r] = f.readLine().toLowerCase().toCharArray();
			int queries = Integer.parseInt(f.readLine());
			while(queries-- > 0)
			{
				String s = f.readLine().toLowerCase();
				outer:
				for(int r = 0; r < a.length; r++)
					for(int c = 0; c < a[0].length; c++)
						if(check(s, r, c))
						{
							out.printf("%d %d\n", r + 1, c + 1);
							break outer;
						}
			}
		}
		out.close();
	}
	
	public static boolean check(String s, int r, int c)
	{
		int count, x, y;
		if(s.charAt(0) != a[r][c])
			return false;
		for(int k = 0; k < dx.length; k++)
		{
			for(x = r, y = c, count = 0; count < s.length() 
					&& x >= 0 
					&& x < a.length 
					&& y >= 0 
					&& y < a[0].length 
					&& a[x][y] == s.charAt(count); x += dx[k], y += dy[k], count++);
			if(count == s.length())
				return true;
		}
		return false;
	}
}