import java.util.*;
import java.io.*;

public class p10616 
{
	static long[][][] table;
	static int m, d;
	static int[] a;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int queries = Integer.parseInt(st.nextToken());
		int count = 1;
		while(size > 0)
		{
			System.out.printf("SET %d:\n", count++);
			a = new int[size];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(f.readLine());
			
			for(int q = 1; q <= queries; q++)
			{
				st = new StringTokenizer(f.readLine());
				d = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				table = new long[m][d][a.length];
				for(long[][] temp1 : table)
					for(long[] temp : temp1)
						Arrays.fill(temp, -1);
				System.out.printf("QUERY %d: %d\n", q, recurse(0, 0, 0));
			}
			
			st = new StringTokenizer(f.readLine());
			size = Integer.parseInt(st.nextToken());
			queries = Integer.parseInt(st.nextToken());
		}
	}
	
	public static long recurse(int curr, int count, int index)
	{
		if(count == m)
		{
			return curr == 0 ? 1 : 0;
		}
		if(index >= a.length)
			return 0;
		if(table[count][curr][index] >= 0)
			return table[count][curr][index];
		long sum = 0;
		for(int i = index; i < a.length; i++)
			sum += recurse(mod(curr + a[i], d), count + 1, i + 1);
		return table[count][curr][index] = sum;
	}
	
	public static int mod(int num, int d)
	{
		num %= d;
		if(num < 0)
			return num + d;
		else
			return num;
	}
}