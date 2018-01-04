import java.util.*;
import java.io.*;

public class p989 
{
	static int[][] a;
	static int[][] boxes;
	static int[] rows, columns;
	static int OK;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean temp = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(temp)
				System.out.println();
			else
				temp = true;
			size = Integer.parseInt(input);
			a = new int[size * size][size * size];
			boxes = new int[a.length][a.length];
			rows = new int[a.length];
			columns = new int[a.length];
			OK = (1 << a.length + 1) - 1;
			for(int r = 0; r < a.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < a.length; c++)
				{
					int val = Integer.parseInt(st.nextToken());
					if(val > 0)
					{
						int flag = 1 << val;
						rows[r] |= flag;
						columns[c] |= flag;
						boxes[r / size][c / size] |= flag;
					}
					a[r][c] = val;
				}
			}
			if(backtrack(0, 0))
				display();
			else
				System.out.println("NO SOLUTION");
			f.readLine();
		}
	}
	
	public static boolean backtrack(int r, int c)
	{
		if(r == a.length)
			return true;
		int nextR, nextC;
		if(c == a.length - 1)
		{
			nextR = r + 1;
			nextC = 0;
		}
		else
		{
			nextR = r;
			nextC = c + 1;
		}
		if(a[r][c] > 0)
			return backtrack(nextR, nextC);
		int canUse = OK & ~(rows[r] | columns[c] | boxes[r / size][c / size]);
		for(int i = 1; i <= a.length; i++)
		{
			int flag = 1 << i;
			if((canUse & flag) > 0)
			{
				a[r][c] = i;
				
				rows[r] |= flag;
				columns[c] |= flag;
				boxes[r / size][c / size] |= flag;
				if(backtrack(nextR, nextC))
					return true;
				rows[r] -= flag;
				columns[c] -= flag;
				boxes[r / size][c / size] -= flag;					
			}
		}
		a[r][c] = 0;
		return false;
	}
	public static void display()
	{
		for(int[] row : a)
		{
			System.out.print(row[0]);
			for(int c = 1; c < a.length; c++)
				System.out.print(" " + row[c]);
			System.out.println();
		}
	}
}