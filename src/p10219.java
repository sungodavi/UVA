import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p10219 
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			long n = scan.nextLong();
			long k = scan.nextLong();
			//System.out.println(combo(n, k).toString().length());
			System.out.println(solve(n, k));
		}
		
	}
	
	public static BigInteger combo(long n, long k)
	{
		if(n - k < k)
			k = n - k;
		BigInteger result = BigInteger.ONE;
		for(long i = n; i > n - k; i--)
			result = result.multiply(BigInteger.valueOf(i));
		for(long i = k; i > 1; i--)
			result = result.divide(BigInteger.valueOf(i));
		return result;
	}
	
	public static long solve(long n, long k)
	{
		if(n - k < k)
			k = n - k;
		double result = 0;
		for(long i = n; i > n - k; i--)
			result += Math.log10(i);
		for(long i = k; i > 1; i--)
			result -= Math.log10(i);
		return (long)result + 1;
	}
	
}
