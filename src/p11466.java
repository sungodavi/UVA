import java.util.*;
import java.io.*;

public class p11466 
{
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		load();
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(long num = Long.parseLong(f.readLine()); num != 0; num = Long.parseLong(f.readLine()))
		{
			if(num < 0)
				num *= -1;
			long max = -1;
			long prev = -1;
			for(long p : list)
			{
				if(p * p > num)
					break;
				if(num % p == 0)
				{
					prev = max;
					max = p;
				}
				while(num % p == 0)
					num /= p;
			}
			if(num != 1)
			{
				prev = max;
				max = num;
			}
			if(prev == -1)
				System.out.println(-1);
			else
				System.out.println(max);
		}
	}
	
	public static void load()
	{
		boolean[] a = new boolean[(int)1e7];
		boolean flag = true;
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				if(flag && i * i >= a.length)
					flag = false;
				if(flag)
					for(int j = i * i; j < a.length; j += i)
						a[j] = true;
			}
		}
	}
}
