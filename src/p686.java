import java.util.*;
import java.io.*;

public class p686
{
	static TreeSet<Integer> set = new TreeSet<Integer>();
	public static void main(String[] args) throws IOException
	{
		load(1 << 15);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int num = Integer.parseInt(f.readLine()); num > 0; num = Integer.parseInt(f.readLine()))
			out.println(count(num));
		out.close();
		
	}
	
	public static int count(int num)
	{
		int count = 0;
		for(int p : set)
		{
			if(p * 2 > num)
				break;
			if(set.contains(num - p))
				++count;
		}
		return count;
	}
	
	public static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				set.add(i);
				for(int j = i * i; j >= 0 && j < a.length; j += i)
					a[j] = true;
			}
		}	
	}
}