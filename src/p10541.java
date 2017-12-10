import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p10541 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		
		while(times-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			long sum = 0;
			for(int i = 1; i <= k; i++)
				sum += Integer.parseInt(st.nextToken());
			System.out.println(combo(n - sum + 1, k));
		}
	}
	
	static BigInteger combo(long n, long k)
	{
		if(k > n || n < 0 || k < 0)
			return BigInteger.ZERO;
		BigInteger result = BigInteger.ONE;
		if(n - k < k)
			k = n - k;
		for(long i = n; i > n - k; i--)
			result = result.multiply(BigInteger.valueOf(i));
		for(long i = k; i > 1; i--)
			result = result.divide(BigInteger.valueOf(i));
		return result;
	}
}
