import java.util.*;
import java.io.*;

public class p11902 
{
	static int[][] a;
	static boolean[] initial;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		StringTokenizer st;
		for(int k = 1; k <= cases; k++)
		{
			System.out.printf("Case %d:\n", k);
			int size = Integer.parseInt(f.readLine());
			a = new int[size][size];
			initial = new boolean[size];
			for(int r = 0; r < a.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c =0; c < a.length; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
			}
			dfs(0, -1, initial);
			printBorder(a.length);
			for(int r = 0; r < a.length; r++)
			{
				boolean[] flags = new boolean[size];
				dfs(0, r, flags);
				display(flags);
				printBorder(a.length);
			}
		}
	}
	
	public static void dfs(int r, int blocked, boolean[] flags)
	{
		if(r == blocked)
			return;
		flags[r] = true;
		for(int c = 0; c < a.length; c++)
			if(a[r][c] == 1 && !flags[c])
				dfs(c, blocked, flags);
	}
	
	public static void printBorder(int size)
	{
		System.out.print('+');
		for(int i = 1; i <= 2 * size - 1; i++)
			System.out.print('-');
		System.out.println('+');
	}
	
	public static void display(boolean[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print('|');
			System.out.print(initial[i] && !a[i] ? 'Y' : 'N');
		}
		System.out.println('|');
	}
}