import java.util.*;
import java.io.*;

public class p10742 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		for(int size = Integer.parseInt(f.readLine()); size > 0; size = Integer.parseInt(f.readLine()))
		{
			ArrayList<Integer> list = sieve(size);
			//System.out.println(list);
			long total = 0;
			for(int i = 0; i < list.size() - 1; i++)
			{
				int j = search(list, size - list.get(i), i);
				if(j < i)
					break;
				total += j - i;
			}
			System.out.printf("Case %d: %d\n", ++caseNo, total);
		}
		out.close();
	}
	
	public static int search(ArrayList<Integer> list, int val, int l)
	{
		int h = list.size() - 1;
		while(l <= h)
		{
			int m = (l + h) >> 1;
			if(list.get(m) == val)
				return m;
			if(list.get(m) < val)
				l = m + 1;
			else
				h = m - 1;
		}
		return h;
	}
	
	public static ArrayList<Integer> sieve(int size)
	{
		boolean[] a = new boolean[size + 1];
		boolean flag = true;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= size; i++)
			if(!a[i])
			{
				list.add(i);
				if(flag && i * i > size)
					flag = false;
				if(flag)
					for(int j = i * i; j <= size; j += i)
						a[j] = true;
			}
		return list;
	}
}