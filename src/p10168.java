import java.util.*;
import java.io.*;

public class p10168
{
	static boolean[] a;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException
	{
		load((int)1e7);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			int num = Integer.parseInt(input);
			display(solve(num));
		}
	}
	
	public static int[] solve(int num)
	{
		if(num < 8)
			return null;
		int[] a;
		if(num % 2 == 0)
		{
			a = new int[] {2, 2, 0, 0};
			num -= 4;
		}
		else
		{
			a = new int[] {2, 3, 0, 0};
			num -= 5;
		}
		for(int p : list)
		{
			if(p >= num)
				break;
			if(!p10168.a[num - p])
			{
				a[2] = p;
				a[3] = num - p;
				return a;
			}
		}
		return null;
	}
	
	public static void display(int[] a)
	{
		if(a == null)
		{
			System.out.println("Impossible.");
			return;
		}
		boolean flag = false;
		for(int num : a)
		{
			if(flag)
				System.out.print(" ");
			else
				flag = true;
			System.out.print(num);
		}
		System.out.println();
	}
	
	public static void load(int size)
	{
		a = new boolean[size + 1];
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