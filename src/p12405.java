import java.util.*;
import java.io.*;

public class p12405
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		int k = 1;
		while(times-- > 0)
		{
			f.readLine();
			char[] a = f.readLine().toCharArray();
			int count = 0;
			for(int i = 0; i < a.length; i++)
			{
				if(a[i] == '.')
				{
					count++;
					if(i == a.length - 1 || a[i] == '#')
					{
						++i;
					}
					else
					{
						i += 2;
					}
				}
			}
			System.out.printf("Case %d: %d\n", k++, count);
		}
	}
}