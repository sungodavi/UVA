import java.util.*;
import java.io.*;

public class p836 
{
	static final int INF = 1000;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		boolean flag = false;
		while(times-- > 0)
		{
			if(flag)
				out.println();
			else
				flag = true;
			f.readLine();
			String s = f.readLine();
			int[][] a = new int[s.length()][s.length()];
			for(int c = 0; c < a.length; c++)
				a[0][c] = s.charAt(c) == '1' ? 1 : -INF;
			for(int r = 1; r < a.length; r++)
			{
				s = f.readLine();
				for(int c = 0; c < a.length; c++)
					a[r][c] = a[r - 1][c] + (s.charAt(c) == '1' ? 1 : -INF);
			}
			int result = 0;
			for(int size = 1; size <= a.length; size++)
			{
				for(int r = size - 1; r < a.length; r++)
				{
					int max = 0;
					for(int c = 0; c < a.length; c++)
					{
						max += a[r][c] - (r >= size ? a[r - size][c] : 0);
						if(max < 0)
							max = 0;
						else
							result = Math.max(result, max);
					}
				}
			}
			out.println(result);
		}
		out.close();
	}
}