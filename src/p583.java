import java.io.*;
import java.util.*;

public class p583 //UNSOLVED
{
	public static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		load((int)1e6);
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			StringBuilder result = new StringBuilder(input);
			result.append(" = ");
			long num = Integer.parseInt(input);
			if(num < 0)
			{
				result.append("-1 x ");
				num *= -1;
			}
			boolean flag = false;
			for(long p : list)
			{
				if(p * p > num)
					break;
				while(num % p == 0)
				{
					num /= p;
					if(flag)
						result.append(" x ");
					else
						flag = true;
					result.append(p);
				}
			}
			if(num != 1)
				result.append((flag ? " x " : "") + num);
			System.out.println(result);
		}
	}
	
	public static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
		boolean flag = true;
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				if(flag && i * i > size)
					flag = false;
				if(flag)
					for(int j = i * i; j < a.length; j += i)
						a[j] = true;
			}
		}
	}

}
