import java.util.*;
import java.io.*;

public class p1225 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int[] count = new int[10];
			int n = Integer.parseInt(f.readLine());
			for(int i = 1; i <= n; i++)
			{
				int temp = i;
				while(temp > 0)
				{
					count[temp % 10]++;
					temp /= 10;
				}
			}
			display(count);
		}
	}
	
	public static void display(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i]);
			if(i != a.length - 1)
				System.out.print(" ");
		}
		System.out.println();
	}
}
