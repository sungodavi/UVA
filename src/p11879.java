import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p11879 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
			System.out.println(new BigInteger(input).mod(BigInteger.valueOf(17)).equals(BigInteger.ZERO) ? 1 : 0);
	}
}