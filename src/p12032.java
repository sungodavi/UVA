import java.util.*;
import java.io.*;

public class p12032 
{
	static int[] a;
	public static void main(String[] args) throws IOException 
	{
		//System.setOut(new PrintStream("output.txt"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int count = 1;
		int cases = Integer.parseInt(f.readLine());
		for(; cases > 0; cases--)
		{
			a = new int[Integer.parseInt(f.readLine()) + 1];
			st = new StringTokenizer(f.readLine());
			for(int i = 1; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			System.out.printf("Case %d: %d\n", count++, bisect());
		}
	}
	
	public static int bisect()
	{
		int high = a[a.length - 1];
		int low = a[1];
		while(low <= high)
		{
			int mid = (low + high) / 2;
			if(run(mid))
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}
	
	public static boolean run(int k)
	{
		for(int i = 0; i < a.length - 1; i++)
		{
			int diff = a[i + 1] - a[i];
			if(diff > k)
				return false;
			if(diff == k)
				k--;
		}
		return true;
	}
}