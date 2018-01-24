import java.util.*;
import java.io.*;

public class p135
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		boolean flag = false;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			if(flag)
				System.out.println();
			else
				flag = true;
			display(solve(Integer.parseInt(input)));
		}
	}
	
	public static long[] solve(int k)
	{
		long[] a = new long[k * k - k + 1];
		a[0] = (1 << k) - 1;
		for(int r = 1; r < a.length; r++)
		{
			for(int c = 0; c < k; c++)
			{
				a[r] = recurse(a, 0, r, 0, k);
			}
		}
		for(long temp : a)
			System.out.println(Long.toBinaryString(temp));
		return a;
	}
	
	public static long recurse(long[] a, long curr, int r, int c, int k)
	{
		if(k == 0)
			return curr;
		for(int i = c; i < a.length; i++)
		{
			long flag = 1L << i;
			long mask = 0;
			for(int j = 0; j < r; j++)
			{
				if((a[j] & flag) != 0)
				{
					mask |= a[j];
				}
			}
			long OK = (1 << a.length) - 1;
			mask = (OK & ~mask);
			System.out.println(r + " " + i + " " + Long.toBinaryString(curr) + " " + Long.toBinaryString(mask));
			for(int j = c; j < a.length; j++)
			{
				long temp = 1L << j;
				if((mask & temp) != 0)
				{
					long result = recurse(a, curr | flag, r, j, k - 1);
					if(result > 0)
						return result;
				}
			}
		}
		return 0;
	}
	
	public static void display(long[] a)
	{
		for(long num : a)
		{
			StringBuilder s = new StringBuilder();
			boolean flag = false;
			for(int c = 1; num > 0; c++, num >>= 1)
			{
				if((num & 1) != 0)
				{
					if(flag)
						s.append(' ');
					else
						flag = true;
					s.append(c);
				}
			}
			System.out.println(s);
		}
	}
}