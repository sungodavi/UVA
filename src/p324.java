import java.util.*;
import java.io.*;

public class p324
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
		{
			int[] a = new int[digits(num)];
			a[0] = 1;
			for(int i = 2; i <= num; i++)
				multiply(a, i);
			
			int[] result = count(a);
			out.printf("%d! --\n", num);
			for(int i = 0; i < result.length; i++)
			{
				if(i == 5)
					out.println();
				out.printf("(%d) %4d    ", i, result[i]);
			}
			out.println();
		}
		out.close();
	}
	
	public static int[] count(int[] a)
	{
		int[] result = new int[10];
		for(int i = 0; i < a.length; i++)
			result[a[i]]++;
		return result;
	}
	
	public static void multiply(int[] a, int num)
	{
		int carry = 0;
		for(int i = 0; i < a.length; i++)
		{
			a[i] = a[i] * num + carry;
			carry = a[i] / 10;
			a[i] %= 10;
		}
	}
	
	public static int digits(int n)
	{
		double count = 0;
		for(int i = 2; i <= n; i++)
			count += Math.log10(i);
		return (int)count + 1;
	}
}