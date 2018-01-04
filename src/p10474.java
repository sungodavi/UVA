import java.util.*;
import java.io.*;

public class p10474 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int caseNo = 0;
		
		for(String input = f.readLine(); !input.equals("0 0"); input = f.readLine())
		{
			out.printf("CASE# %d:\n", ++caseNo);
			st = new StringTokenizer(input);
			int[] a = new int[Integer.parseInt(st.nextToken())];
			int queries = Integer.parseInt(st.nextToken());
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(f.readLine());
			Arrays.sort(a);
			while(queries-- > 0)
			{
				int num = Integer.parseInt(f.readLine());
				int index = binarySearch(a, num);
				if(index < 0)
					out.printf("%d not found\n", num);
				else
					out.printf("%d found at %d\n", num, index + 1);
			}
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
			if(a[m] == val && (m == 0 || a[m - 1] != val))
				return m;
			if(a[m] < val)
				low = m + 1;
			else
				high = m - 1;
		}
		return -1;
	}
}