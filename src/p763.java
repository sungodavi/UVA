import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p763 
{
	static BigInteger[] fib = new BigInteger[150];
	public static void main(String[] args) throws IOException
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		load();
		BigInteger n1 = convert(f.readLine());
		BigInteger n2 = convert(f.readLine());
		while(true)
		{
			System.out.println(convert(n1.add(n2)));
			f.readLine();
			String s = f.readLine();
			if(s == null || s.isEmpty())
				break;
			System.out.println();
			n1 = convert(s);
			n2 = convert(f.readLine());
		}
	}
	public static BigInteger convert(String s)
	{
		BigInteger result = BigInteger.ZERO;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '1')
				result = result.add(fib[s.length() - i]);
		}
		return result;
	}
	
	public static String convert(BigInteger num)
	{
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for(int i = fib.length - 1; i > 0; i--)
		{
			if(num.compareTo(fib[i]) < 0)
			{
				if(flag)
					result.append(0);
			}
			else
			{
				flag = true;
				result.append(1);
				num = num.subtract(fib[i]);
			}
		}
		if(result.length() == 0)
			return "0";
		return result.toString();
	}
	public static void load()
	{
		fib[0] = BigInteger.ONE;
		fib[1] = BigInteger.ONE;
		for(int i = 2; i < fib.length; i++)
			fib[i] = fib[i - 1].add(fib[i - 2]);
	}
}
