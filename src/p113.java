import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p113
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int n = Integer.parseInt(input);
			BigInteger p = new BigInteger(f.readLine());
			System.out.println(solve(n, p));
		}
	}
	
	public static int solve(int n, BigInteger p)
	{
		int low = 1;
		int high = (int)1e9;
		while(low <= high)
		{
			int mid = low + high >> 1;
			BigInteger curr = BigInteger.valueOf(mid).pow(n);
			int c = curr.compareTo(p);
			if(c == 0)
				return mid;
			if(c > 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
}