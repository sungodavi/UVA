import java.util.*;
import java.io.*;

public class p10139 //UNSOLVED
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			long n = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			if(check(n, a))
				System.out.printf("%d divides %d!\n", a, n);
			else
				System.out.printf("%d does not divide %d!\n", a, n);
		}
	}
	
	public static boolean check(long n, long a)
	{
		long f = 2;
		long e = 0;
		while((a & 1) == 0)
		{
			e++;
			a >>= 1;
		}
		if(e > 0 && count(n, f) < e)
			return false;
		for(f = 3; f * f <= a; f += 2)
		{
			System.out.println(f + " " + a + " " + " " + n);
			e = 0;
			while(a % f == 0)
			{
				++e;
				a /= f;
			}
			if(e > 0 && count(n, f) < e)
				return false;
		}
		return a == 1 || count(n, f) > 0;
	}
	
	public static long count(long n, long f)
	{
		long sum = 0;
		for(long i = f; i <= n; i *= f)
			sum += n / i;
		return sum;
	}
}
