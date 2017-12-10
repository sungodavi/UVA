import java.util.*;
import java.io.*;

public class p11955 
{
	static long[][] c = new long[51][51];
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		for(int k = 1; k <= times; k++)
		{
			System.out.printf("Case %d: ", k);
			StringTokenizer st = new StringTokenizer(f.readLine(), "[()+^]+");
			String a = st.nextToken();
			String b = st.nextToken();
			int e = Integer.parseInt(st.nextToken());
			for(int i = e; i >= 0; i--)
			{
				int j = e - i;
				long c = combo(e, i);
				if(c != 1)
					System.out.print(c + "*");				
				if(i > 0)
				{
					System.out.print(a);
					if(i > 1)
						System.out.print("^" + i);
				}
				if(j > 0)
				{
					if(i > 0)
						System.out.print("*");
					System.out.print(b);
					if(j > 1)
						System.out.print("^" + j);
				}
				if(i > 0)
					System.out.print("+");
			}
			System.out.println();
		}
	}
	
	public static long combo(int n, int k)
	{
		if(n - k < k)
			k = n - k;
		if(k == 0 || n == k)
			return 1;
		if(c[n][k] > 0)
			return c[n][k];
		return c[n][k] = combo(n - 1, k) + combo(n - 1, k - 1);
	}
}
