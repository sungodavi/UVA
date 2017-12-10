import java.util.*;
import java.io.*;

public class p543 
{
	static boolean[] a = new boolean[(int)1e6 + 1];
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		load();
		for(String input = f.readLine(); !input.equals("0"); input = f.readLine())
		{
			int num = Integer.parseInt(input);
			solve(num);
		}
	}
	
	public static void load()
	{
		boolean flag = true;
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				if(!flag || i * i > a.length)
					flag = false;
				if(flag)
					for(int j = i * i; j >= 0 && j < a.length; j += i)
						a[j] = true;
			}
		}
	}
	public static void solve(int num)
	{
		for(int p : list)
		{
			if(2 * p > num)
				break;
			if(!a[p] && !a[num - p])
			{
				System.out.printf("%d = %d + %d\n", num, p , num - p);
				return;
			}
		}
		System.out.println("Goldbach's conjecture is wrong.");
	}
}
