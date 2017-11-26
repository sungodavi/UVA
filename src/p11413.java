import java.util.*;
import java.io.*;

public class p11413 
{
	static int[] a;
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String input = f.readLine();
		StringTokenizer st;
		while(input != null && !input.isEmpty())
		{
			st = new StringTokenizer(input);
			a = new int[Integer.parseInt(st.nextToken())];
			int amount = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			int sum = 0;
			int max = 0;
			for(int i = 0; i < a.length; i++)
			{
				int temp = Integer.parseInt(st.nextToken());
				a[i] = temp;
				sum += temp;
				if(temp > max)
					max = temp;
			}
			//System.out.println(run(3,5));
			System.out.println(bisect(amount, sum, max));
			input = f.readLine();
		}
	}
	
	public static boolean run(int amount, int size)
	{
		int count = 1;
		int bin = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(bin + a[i] > size)
			{
				if(count == amount)
					return false;
				count++;
				bin = a[i];
			}
			else
				bin += a[i];
			//System.out.println(bin + " " + count);
		}
		return true;
	}
	
	public static int bisect(int amount, int sum, int max)
	{
		int low = max;
		int high = sum;
		while(low <= high)
		{
			int mid = (low + high) / 2;
			//System.out.println(mid + " " + run(amount, mid));
			if(run(amount, mid))
				high = mid - 1;
			else
				low = mid + 1;
				
		}
		return low;
	}
}