import java.util.*;
import java.io.*;

public class p10309
{
	static boolean[][] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		for(String name = f.readLine(); !name.equals("end"); name = f.readLine())
		{
			boolean[][] temp = new boolean[10][10];
			a = new boolean[10][10];
			for(int r = 0; r < a.length; r++)
			{
				String s = f.readLine();
				for(int c = 0; c < a.length; c++)
					temp[r][c] = s.charAt(c) == 'O' ? true : false;
			}
			int best = Integer.MAX_VALUE;
			int limit = 1 << 10;
			for(int i = 0; i < limit; i++)
			{
				for(int r = 0; r < a.length; r++)
					System.arraycopy(temp[r], 0, a[r], 0, a.length);
				int count = 0;
				int mask = i;
				for(int j = 0; j < a.length && mask > 0; mask >>= 1, j++)
				{
					if((mask & 1) != 0)
					{
						flip(0, j);
						count++;
					}
				}

				for(int r = 1; r < a.length; r++)
					for(int c = 0; c < a.length; c++)
						if(a[r - 1][c])
						{
							flip(r, c);
							count++;
						}
				boolean found = true;
				for(int c = 0; c < a.length; c++)
					if(a[a.length - 1][c])
					{
						found = false;
						break;
					}
				if(found)
					best = Math.min(best, count);

			}
			out.printf("%s %d\n", name, best == Integer.MAX_VALUE ? -1 : best);
		}
		out.close();
	}
	public static void display()
	{
		for(boolean[] row : a)
		{
			for(boolean b : row)
				if(b)
					System.out.print(1);
				else
					System.out.print(0);
			System.out.println();
		}
	}
	public static void flip(int r, int c)
	{
		a[r][c] = !a[r][c];
		if(r > 0)
			a[r - 1][c] = !a[r - 1][c];
		if(c > 0)
			a[r][c - 1] = !a[r][c - 1];
		if(r < a.length - 1)
			a[r + 1][c] = !a[r + 1][c];
		if(c < a.length - 1)
			a[r][c + 1] = !a[r][c + 1];
	}
}