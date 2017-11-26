import java.util.*;
import java.io.*;

public class p11350 
{
	static Fraction[] a = new Fraction[3];
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			a[0] = new Fraction(0,1);
			a[1] = new Fraction(1,1);
			a[2] = new Fraction(1,0);
			System.out.println(traverse(f.readLine()));
		}
	}
	
	static Fraction traverse(String s)
	{
		for(int i =0; i < s.length(); i++)
		{
			if(s.charAt(i) == 'L')
			{
				a[2] = a[1];
			}
			else
			{
				a[0] = a[1];
			}
			a[1] = new Fraction(a[0], a[2]);
		}
		return a[1];
	}
	
	static class Fraction
	{
		long n, d;
		public Fraction(int n, int d)
		{
			this.n = n;
			this.d = d;
		}
		
		public Fraction(Fraction left, Fraction right)
		{
			n = left.n + right.n;
			d = left.d + right.d;
		}
		
		public String toString()
		{
			return n + "/" + d;
		}
	}
}
