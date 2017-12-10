import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class p713 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			String a = new StringBuilder(st.nextToken()).reverse().toString();
			String b = new StringBuilder(st.nextToken()).reverse().toString();
			BigInteger n1 = new BigInteger(a);
			BigInteger n2 = new BigInteger(b);
			BigInteger result = new BigInteger(new StringBuilder(n1.add(n2).toString()).reverse().toString());
			System.out.println(result);			
		}
	}
}
