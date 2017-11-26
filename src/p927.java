import java.util.*;
import java.io.*;

public class p927 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			long[] c = new long[Integer.parseInt(st.nextToken()) + 1];
			for(int i =0; i < c.length; i++)
				c[i] = Long.parseLong(st.nextToken());
			//System.out.println(Arrays.toString(c));
			long d = Long.parseLong(f.readLine());
			long k = Long.parseLong(f.readLine());
			//System.out.println(find(k, d));
			System.out.println(eval(c, find(k, d)));
		}
	}
	
	public static long find(long k, long d)
	{
		long target = (long)Math.ceil((double)2 * k / d);
		long n = (long)Math.ceil(Math.sqrt(target));
		while(n * (n + 1) >= target)
			n--;
		return n + 1;
	}
	
	public static long eval(long[] c, long n)
	{
		long result = 0;
		for(int i = 0; i < c.length; i++)
		{
			if(c[i] == 0)
				continue;
			result += (long)Math.pow(n, i) * c[i];
		}
		return result;
	}
	
}