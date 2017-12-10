import java.util.*;
import java.io.*;

public class p11513 
{
	static int[][] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
		{
			a = new int[3][3];
			load(0, input);
			for(int i = 1; i < 3; i++)
				load(i, f.readLine());
		}
	}
	
	public static void load(int row, String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		for(int c =0; c < 3; c++)
			a[row][c] = Integer.parseInt(st.nextToken());
	}
}
