import java.util.*;
import java.io.*;

public class p11953 
{
	static char[][] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		for(int t = 1; t <= times; t++)
		{
			int size = Integer.parseInt(f.readLine());
			a = new char[size][size];
			for(int r = 0; r < a.length; r++)
				a[r] = f.readLine().toCharArray();
			
			int count = 0;
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++)
				{
					if(a[r][c] != '.')
					{
						boolean flag = a[r][c] == 'x';
						a[r][c] = '.';
						if(r > 0 && a[r - 1][c] != '.')
							flag |= fill(r - 1, c, -1, 0);
						else if(c > 0 && a[r][c - 1] != '.')
							flag |= fill(r, c - 1, 0, -1);
						else if(r < a.length - 1 && a[r + 1][c] != '.')
							flag |= fill(r + 1, c, 1, 0);
						else if(c < a.length - 1 && a[r][c + 1] != '.')
							flag |= fill(r, c + 1, 0, 1);
						if(flag)
							count++;
					}
				}
			out.printf("Case %d: %d\n", t, count);
		}
		out.close();
	}
	
	public static boolean fill(int x, int y, int dx, int dy)
	{
		boolean flag = false;
		for(; x >= 0 && x < a.length && y >= 0 && y < a[0].length && a[x][y] != '.'; x += dx, y += dy)
		{
			if(a[x][y] == 'x')
				flag = true;
			a[x][y] = '.';
		}
		return flag;
	}
}