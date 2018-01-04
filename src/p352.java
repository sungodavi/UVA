import java.util.*;
import java.io.*;

public class p352 
{
	public static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
	public static int[] dy = {1, -1, -1, 0, 1, -1, 0, 1};
	static int[][] a;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int size = Integer.parseInt(input);
			a = new int[size][size];
			for(int r = 0; r < a.length; r++)
			{
				String s = f.readLine();
				for(int c = 0; c < a.length; c++)
					a[r][c] = s.charAt(c) - '0';
			}
			int count = 0;
			for(int r = 0; r < a.length; r++)
				for(int c = 0; c < a.length; c++)
					if(a[r][c] == 1)
					{
						count++;
						fill(r, c);
					}
			out.printf("Image number %d contains %d war eagles.\n", ++caseNo, count);
		}
		out.close();
	}
	
	public static void fill(int r, int c)
	{
		if(!isValid(r, c))
			return;
		a[r][c] = 0;
		for(int i = 0; i < dx.length; i++)
			fill(r + dx[i], c + dy[i]);
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && a[r][c] == 1;
	}
}