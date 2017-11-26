import java.util.*;
import java.io.*;

public class p507 {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		int cases = 1;
		while(times-- > 0)
		{
			int size = Integer.parseInt(f.readLine());
			if(size <= 0)
			{
				System.out.printf("Route %d has no nice parts\n", cases++);
				continue;
			}
				
			int[] a = new int[size - 1];
			for(int k = 0; k < a.length; k++)
				a[k] = Integer.parseInt(f.readLine());
			int[] result = solve(a);
			if(result == null)
				System.out.printf("Route %d has no nice parts\n", cases++);
			else
				System.out.printf("The nicest part of route %d is between stops %d and %d\n", cases++, result[0], result[1]);
		}
		
	}
	
	public static int[] solve(int[] a)
	{
		int max = 0;
		int start = 0;
		int tempStart = 0;
		int end = 0;
		int sum =0;
		for(int i = 0; i < a.length; i++)
		{
			sum += a[i];
			if(sum < 0)
			{
				sum = 0;
				tempStart = i + 1;
			}
			if(sum > max || (sum == max && (i - tempStart) > end - start))
			{
				max = sum;
				start = tempStart;
				end = i + 1;
			}
		}
		if(start >= end)
			return null;
		return new int[] {start + 1, end + 1};
	}
}