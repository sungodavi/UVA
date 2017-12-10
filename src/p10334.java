import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p10334 
{
	static BigInteger[] fibs = new BigInteger[1005];
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		fibs[0] = fibs[1] = BigInteger.ONE;
		int curr = 1;
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int num = Integer.parseInt(input) + 1;
			while(curr < num)
			{
				curr++;
				fibs[curr] = fibs[curr - 1].add(fibs[curr - 2]);
			}
			System.out.println(fibs[num]);
		}
	}
}
