import java.util.*;
import java.io.*;

public class p11127
{
	static int[] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
//		for(String input = f.readLine(); input.charAt(0) != '0'; input = f.readLine())
//		{
//			st = new StringTokenizer(f.readLine());
//			st.nextToken();
//			String s = st.nextToken();
//			convert(s);
//		}
		convert("010101");
		System.out.println(check(Integer.parseInt("010101", 2)));
	}
	
	public static int backtrack(int index, int mask)
	{
		if(index == a.length)
			return 1;
		int len = lg(mask);
		return 0;
	}
	
	public static boolean check(long num)
	{
		int len = lg(num);
		System.out.println(num + " " + len);
		for(int size = 1; size * 3 <= a.length; size++)
			if(check(num, size))
				return false;
		
		return true;
	}
	
	public static boolean check(long num, int size)
	{
		int len = lg(num);
		long a = num >> len - size + 1;
		long b = (num >> len - 2 * size + 1) % (1 << size);
		long c = (num >> len - 3 * size + 1) % (1 << size);
		System.out.println(a + " " + b + " " + c);
		return equals(a, b, c);
	}
	
	public static int lg(long num)
	{
		return 63 - Long.numberOfLeadingZeros(num);
	}
	
	public static boolean equals(long a, long b, long c)
	{
		return a == b && b == c;
	}
	
	public static void convert(String s)
	{
		a = new int[s.length()];
		for(int i = 0; i < a.length; i++)
		{
			char c = s.charAt(i);
			if(c == '*')
				a[i] = -1;
			else
				a[i] = c - '0';
		}
	}
}