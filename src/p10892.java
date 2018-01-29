import java.util.*;
import java.io.*;

public class p10892
{
	public static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException
	{
		load((int)1e6);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
			out.printf("%d %d\n", num, count(num));
		out.close();
	}
	
	public static long count(int num)
	{
		long result = 1;
		int p = 2;
		int index = 0;
		while(p * p <= num)
		{
			int e = 0;
			while(num % p == 0)
			{
				e++;
				num /= p;
			}
			//System.out.println("Factor " + p + " " + e);
			result *= (2 * e + 1);
			p = list.get(++index);
		}
		if(num != 1)
			result *= 3;
		return (result + 1) / 2;
	}
	
	public static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
		list = new ArrayList<Integer>();
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