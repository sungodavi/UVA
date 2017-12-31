import java.util.*;
import java.io.*;

public class p11264
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int[] a = new int[Integer.parseInt(f.readLine())];
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			int sum = 0;
			int count = 1;
			for(int i = 0; i < a.length - 1; i++)
			{
				sum += a[i];
				if(sum < a[i + 1])
					count++;
				else
					sum -= a[i];
			}
			System.out.println(count);
		}
	}
}