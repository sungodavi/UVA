import java.util.*;
import java.io.*;

public class p11827
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int[] a = new int[st.countTokens()];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			int result = 0;
			for(int i = 0; i < a.length - 1; i++)
				for(int j = i + 1; j < a.length; j++)
					result = Math.max(result, gcd(a[i], a[j]));
			out.println(result);
		}
		out.close();
	}
	
	public static int gcd(int x, int y)
	{
		while(y > 0)
		{
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}
}