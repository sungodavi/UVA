import java.util.*;
import java.io.*;

public class p11053 
{
	static long a, b, n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			st = new StringTokenizer(input);
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			long h = b;
			long t = 0;
			while(h != t)
			{
				t = f(t);
				h = f(f(h));
			}
			h = f(t);
			int length = 1;
			while(t != h)
			{
				h = f(h);
				length++;
			}
			System.out.println(n - length);
		}
	}
	
	public static long f(long x)
	{
		return (((x * x) % n * a) % n + b) % n;
	}
}