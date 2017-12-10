import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class p10523 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			StringTokenizer st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			BigInteger a = new BigInteger(st.nextToken());
			BigInteger result = BigInteger.ZERO;
			for(int k = 1; k <= n; k++)
			{
				result = result.add(a.pow(k).multiply(BigInteger.valueOf(k)));
			}
			System.out.println(result);
		}
	}
}
