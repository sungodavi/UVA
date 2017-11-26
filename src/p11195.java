import java.util.*;
import java.io.*;

public class p11195 
{
	static boolean[] ld, rd, rows;
	static char[][] bad;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(f.readLine());
		int count = 1;
		while(size > 0)
		{
			bad = new char[size][size];
			ld = new boolean[2 * size - 1];
			rd = new boolean[2 * size - 1];
			rows = new boolean[size];
			for(int r = 0; r < bad.length; r++)
			{
				bad[r] = f.readLine().toCharArray();
			}
			System.out.printf("Case %d: %d\n", count++, recurse(0, 0));
			size = Integer.parseInt(f.readLine());
		}
	}
	
	public static int recurse(int c, int queenCount)
	{
		if(c == bad.length)
		{
			if(queenCount == bad.length)
				return 1;
			return 0;
		}
		int sum = 0;
		for(int r = 0; r < bad.length; r++)
		{
			int ldIndex = 2 * bad.length - 2 - r - c;
			int rdIndex = c - r + bad.length - 1;
			//System.out.println(ldIndex + " " + rdIndex);
			if(!rows[r] && !rd[rdIndex] && !ld[ldIndex] && bad[r][c] == '.')
			{
				ld[ldIndex] = true;
				rows[r] = true;
				rd[rdIndex]= true;
				sum += recurse(c + 1, queenCount + 1);
				ld[ldIndex] = false;
				rows[r] = false;
				rd[rdIndex]= false;
			}
		}
		return sum;
	}

}
