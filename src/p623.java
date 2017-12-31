import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class p623 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			long n = scan.nextLong();
			System.out.println(n + "!");
			System.out.println(fact(n));
		}
	}
	
	public static BigInteger fact(long n)
	{
		BigInteger result = BigInteger.ONE;
		for(long i = 2; i <= n; i++)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

}
