import java.util.*;
import java.io.*;

public class p10957 
{
	static int[] rows, columns;
	static int[][] boxes;
	static int[][] a;
	static final int OK = (1 << 10) - 1;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int caseNo = 0;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			System.out.printf("Case %d: ", ++caseNo);
			a = new int[9][9];
			rows = new int[9];
			columns = new int[9];
			boxes = new int[3][3];
			
			boolean illegal = false;
			st = new StringTokenizer(input);
			for(int c = 0; c < a.length; c++)
			{
				int val = Integer.parseInt(st.nextToken());
				if(val > 0)
				{
					int flag = 1 << val;
					if(!check(0, c, flag))
						illegal = true;
					rows[0] |= flag;
					columns[c] |= flag;
					boxes[0][c / 3] |= flag;
				}
				a[0][c] = val;
			}
			for(int r = 1; r < a.length; r++)
			{
				st = new StringTokenizer(f.readLine());
				for(int c = 0; c < a.length; c++)
				{
					int val = Integer.parseInt(st.nextToken());
					if(val > 0)
					{
						int flag = 1 << val;
						if(!check(r, c, flag))
							illegal = true;
						rows[r] |= flag;
						columns[c] |= flag;
						boxes[r / 3][c / 3] |= flag;
					}
					a[r][c] = val;
				}
			}
			f.readLine();
			
			if(illegal)
			{
				System.out.println("Illegal.");
				continue;
			}
			int count = backtrack(0, 0);
			if(count == 0)
				System.out.println("Impossible.");
			else if(count == 1)
				System.out.println("Unique.");
			else
				System.out.println("Ambiguous.");
			//f.readLine();
		}
	}
	
	public static int backtrack(int r, int c)
	{
		//System.out.println(r + " " + c);
		if(r == a.length)
			return 1;
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
		int count = 0;
		int canUse = OK & ~(rows[r] | columns[c] | boxes[r / 3][c / 3]);
		for(int i = 1; i <= a.length; i++)
		{
			int flag = 1 << i;
			if((canUse & flag) > 0)
			{
				a[r][c] = i;
				rows[r] |= flag;
				columns[c] |= flag;
				boxes[r / 3][c / 3] |= flag;
				count += backtrack(nextR, nextC);
				if(count > 1)
					return count;
				rows[r] -= flag;
				columns[c] -= flag;
				boxes[r / 3][c / 3] -= flag;					
			}
		}
		a[r][c] = 0;
		return count;
	}
	
	public static boolean check(int r, int c, int flag)
	{
		boolean row = (rows[r] & flag) == 0;
		boolean col = (columns[c] & flag) == 0;
		boolean box = (boxes[r / 3][c / 3] & flag) == 0;
		return row && col && box;
	}
}