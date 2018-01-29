import java.util.*;
import java.io.*;

public class p897
{
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException
	{
		load(10000000);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
			out.println(solve(num));
		out.close();
	}
	
	public static int solve(int num)
	{
		int limit = (int)Math.pow(10, (int)Math.log10(num) + 1);
		int index = search(num);
		for(int i = index; i < list.size() && list.get(i) < limit; i++)
			if(check(list.get(i)))
				return list.get(i);
		return 0;
	}
	
	public static boolean check(int num)
	{
		int[] a = convert(num);
		int size = fact(a.length);
		for(int i = 0; i < size; i++, next(a))
			if(Collections.binarySearch(list, convert(a)) < 0)
				return false;
		
		return true;
	}
	
	public static int fact(int num)
	{
		int result = 1;
		for(int i = 2; i <= num; i++)
			result *= i;
		return result;
	}
	
	public static void next(int[] a)
	{
		if(a.length == 1)
			return;
		int j, l;
		j = a.length - 2;
		l = a.length - 1;
		while(j > 0 && a[j] >= a[j + 1])
			j--;
		while(l > 0 && a[j] >= a[l])
			l--;
		swap(a, j, l);
		
		j++;
		l = a.length - 1;
		while(j < l)
		{
			swap(a, j, l);
			j++;
			l--;
		}
	}
	
	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static int[] convert(int num)
	{
		int size = (int)Math.log10(num) + 1;
		int[] a = new int[size];
		for(int i = 0; i < a.length; i++)
		{
			a[i] = num % 10;
			num /= 10;
		}
		Arrays.sort(a);
		return a;
	}
	
	public static int convert(int[] a)
	{
		int result = 0;
		int e = 1;
		for(int i = a.length - 1; i >= 0; i--, e *= 10)
			result += a[i] * e;
		return result;
	}
	
	public static void load(int size)
	{
		list = new ArrayList<Integer>();
		boolean[] a = new boolean[size];
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
	
	public static int search(int val)
	{
		int low = 0;
		int high = list.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(list.get(mid) <= val)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}
}