import java.util.*;
import java.io.*;

public class p11065 
{
	static long[] a;
	static int best, count;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int intersections = Integer.parseInt(st.nextToken());
			int roads = Integer.parseInt(st.nextToken());
			a = new long[intersections];
			for(int i = 0; i < a.length; i++)
				a[i] = 1L << i;
			count = best = 0;
			while(roads-- > 0)
			{
				st = new StringTokenizer(f.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				a[u] |= 1L << v;
				a[v] |= 1L << u;
			}
			//System.out.println(Arrays.toString(a));
			backtrack(0, 0, 0);
			System.out.println(count);
			System.out.println(best);
		}
	}
	
	public static void backtrack(int index, long used, int depth)
	{
		if(used == ((1L << a.length) - 1))
		{
			count++;
			best = Math.max(best, depth);
		}
		for(int i = index; i < a.length; i++)
		{
			if((used & (1L << i)) == 0)
			{
				backtrack(i + 1, used | a[i], depth + 1);
			}
		}
	}
}