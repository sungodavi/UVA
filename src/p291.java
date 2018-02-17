import java.util.*;
import java.io.*;

public class p291 
{
	static int[][] a;
	static PrintWriter out;
	static int[] path;
	public static void main(String[] args) throws IOException 
	{
		String input = "0 1 1 0 1\n"
					+ "1 0 1 0 1\n"
					+ "1 1 0 1 1\n"
					+ "0 0 1 0 1\n"
					+ "1 1 1 1 0";
					
		Scanner scan = new Scanner(input);
		out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		a = new int[5][5];
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a[0].length; c++)
				a[r][c] = scan.nextInt();
		path = new int[9];
		dfs(0, 0);
		out.close();
	}
	
	public static void dfs(int r, int index)
	{
		path[index] = r;
		if(index == path.length - 1)
			out.println(convert(path));
		else
		{
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
				{
					a[r][c] = a[c][r] = 0;
					dfs(c, index + 1);
					a[r][c] = a[c][r] = 1;
				}
			}
		}
	}
	
	public static String convert(int[] a)
	{
		char[] result = new char[a.length];
		for(int i = 0; i < a.length; i++)
			result[i] = (char)(a[i] + '1');
		return new String(result);
	}
}