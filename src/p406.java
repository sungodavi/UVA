import java.util.*;
import java.io.*;

public class p406
{
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException
	{
		load(1000);
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			int num = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int size = binarySearch(num) + 1;
			int mid = size >> 1;
			if(size % 2 == 0)
			{
				for(int i = mid - c + 1; i <= mid; i++)
					System.out.print(list.get(i) + " ");
				for(int i = mid + 1; i <= mid + c; i++)
				{
					if(i > mid + 1)
						System.out.print(" ");
					System.out.print(list.get(i));
				}
				System.out.println();
			}
			else
			{
				
			}
		}
	}
	
	public static void load(int size)
	{
		boolean[] a = new boolean[size + 1];
		for(int i = 2; i < a.length; i++)
		{
			if(!a[i])
			{
				list.add(i);
				for(int j = i * i; j < a.length; j++)
					a[j] = true;
			}
		}
	}
	
	public static int binarySearch(int val)
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
		return high;
	}
	
}