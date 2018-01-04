import java.util.*;
import java.io.*;

public class p11195 
{
	static int[] bad;
	static int OK;
	static int size;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int caseNo = 0;
		for(size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			OK = (1 << size) - 1;
			bad = new int[size];
			for(int r = 0; r < size; r++)
			{
				String s = f.readLine();
				for(int c = 0; c < size; c++)
				{
					if(s.charAt(c) == '*')
						bad[c] += 1 << r;
				}
			}
			System.out.printf("Case %d: %d\n", ++caseNo, backtrack(0, 0, 0, 0));
		}
	}
	
	public static int backtrack(int c, int ld, int rd, int rw)
	{
		if(rw == OK)
			return 1;
		if(c == size)
			return 0;
		int pos = OK & (~(ld | rd | rw));
		int sum = 0;
		while(pos > 0)
		{
			int p = pos & -pos;
			pos -= p;
			if((bad[c] & p) == 0)
			{
				sum += backtrack(c + 1, (ld | p) << 1, (rd | p) >> 1, rw | p);
			}
		}
		return sum;
	}
}
