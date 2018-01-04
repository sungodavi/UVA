import java.util.*;
import java.io.*;

public class p957 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for(String input = f.readLine(); input != null && !input.isEmpty(); input = f.readLine())
		{
			int period = Integer.parseInt(input) - 1;
			int[] a = new int[Integer.parseInt(f.readLine())];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(f.readLine());
			
			int start = 0, end = 0;
			for(int i = 0; i < a.length; i++)
			{
				int j = binarySearch(a, a[i] + period);
				//System.out.println(a[i] + " " + a[j]);
				if(end - start < j - i)
				{
					end = j;
					start = i;
				}
			}
			f.readLine();
			out.printf("%d %d %d\n", end - start + 1, a[start], a[end]);
		}
		out.close();
	}
	
	public static int binarySearch(int[] a, int val)
	{
		int low = 0;
		int high = a.length - 1;
		while(low <= high)
		{
			int m = (low + high) >> 1;
			if(a[m] == val)
				return m;
			if(a[m] > val)
				high = m - 1;
			else
				low = m + 1;
		}
		return high;
	}
}