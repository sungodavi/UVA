import java.util.*;
import java.io.*;

public class p914
{
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException
	{
		load((int)1e6);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int low = search(Integer.parseInt(st.nextToken()), true);
			int high = search(Integer.parseInt(st.nextToken()), false);
			if(low >= high)
			{
				out.println("No jumping champion.");
				continue;
			}
			
		}
	}
	
	public static int search(int val, boolean left)
	{
		int low = 0;
		int high = list.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(list.get(mid) == val)
				return mid;
			if(list.get(mid) > val)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return left ? low : high;
	}
	
	static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
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