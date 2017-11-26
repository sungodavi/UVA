import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(f.readLine());
		while(cases-- > 0)
		{
			int[] a = new int[Integer.parseInt(f.readLine())];
			if(a.length == 0)
				System.out.println(0);
			else
			{
				for(int i = 0; i < a.length; i++)
					a[i] = Integer.parseInt(f.readLine());
				System.out.println(Arrays.toString(a));
				System.out.println(lis(a));
			}
		}
	}
	
	public static int lis(int[] a)
	{
		int[] ends = new int[a.length];
		int length = 1;
		ends[0] = a[0];
		for(int i = 1; i < a.length; i++)
		{
			if(a[i] < ends[0])
				ends[0] = a[i];
			else if(a[i] > ends[length - 1])
				ends[length++] = a[i];
			else
			{
				int k = search(ends, length, a[i]);
				ends[k] = a[i];
			}
			System.out.println(Arrays.toString(ends) + " " + length);
		}
		return length;
	}
	public static int search(int[] a, int length, int num)
	{
		int high = length - 1;
		int low = 0;
		while(low <= high)
		{
			int mid = (low + high) / 2;
			if(a[mid] < num)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}
}