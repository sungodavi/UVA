import java.util.*;
import java.io.*;

public class p10680 //UNSOLVED
{
	public static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		load();
		int max = 0;
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(long num = Long.parseLong(f.readLine()); num > 0; num = Long.parseLong(f.readLine()))
		{
			int p = 2;
			int result = 1;
			int e = 0;
			while(p <= num)
			{
				++e;
				p <<= 1;
			}
			int power2 = e;
			int i;
			for(i = 1; i < list.size(); i++)
			{
				p = list.get(i);
				int temp = p;
				if(p > num)
					break;
				e = 0;
				while(p <= num)
				{
					++e;
					p *= temp;
				}
				//System.out.println(p + " " + e);
				if(i == 2 && power2 != e)
					result = (result * (int)Math.pow(2, (power2 - e) % 4 + 4)) % 10;
				else
					result = (result * (int)Math.pow(temp % 10, e % 4 + 4)) % 10;
			}
			if(i <= 2 && power2 != 0)
				result = (result * (int)Math.pow(2, power2 % 4 + 4)) % 10;
			System.out.println(result);
		}
	}
	
	public static void load()
	{
		boolean[] a = new boolean[(int)1e6 + 1];
		boolean flag = true;
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				if(flag && i * i > a.length)
					flag = false;
				if(flag)
					for(int j = i * i; j < a.length; j += i)
						a[j] = true;
			}
		}
	}
}
