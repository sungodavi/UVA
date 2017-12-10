import java.io.*;
import java.util.*;

public class p583 //UNSOLVED
{
	public static ArrayList<Long> list = new ArrayList<Long>();
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		load();
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
	
	public static void load()
	{
		BitSet a = new BitSet();
		a.set(0, Integer.MAX_VALUE, true);
		System.out.println(a.size());
		boolean flag = true;
		for(int i = 2; i < a.size(); i++)
		{
			if(a.get(i))
			{
				list.add((long) i);
				if(!flag || i * i >= a.size())
					flag = false;
				if(flag)
					for(int j = i * i; j < a.size(); j *= i)
						a.clear(j);
			}
		}
		list.add((long) Integer.MAX_VALUE);
	}

}
