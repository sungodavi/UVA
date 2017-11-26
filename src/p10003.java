import java.util.*;
import java.io.*;

public class p10003 
{
	static int l;
	static int[] cuts;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(f.readLine());
		while(l > 0)
		{
			cuts = new int[Integer.parseInt(f.readLine()) + 2];
			cuts[cuts.length - 1] = l;
			StringTokenizer st = new StringTokenizer(f.readLine());
			for(int i = 1; i < cuts.length - 1; i++)
				cuts[i] = Integer.parseInt(st.nextToken());
			System.out.printf("The minimum cutting is %d.\n", solve());
			l = Integer.parseInt(f.readLine());
		}
	}
	
	public static int solve() // 0 25 50 75 100
	{
		int[][] a = new int[cuts.length][cuts.length];
		for(int c = 2; c < a.length; c++)
		{
			for(int d = 0; d + c < a.length; d++)
			{
				int best = Integer.MAX_VALUE;
				for(int k = 1; k < c; k++)
				{
					//System.out.println(d + " " + (c + d) + " " + k);
					//System.out.println(a[d][k] + " + " + a[d + k][c + d]);
					best = Math.min(best, a[d][k + d] + a[d + k][c + d]);
				}
				a[d][c + d] = best + cuts[c + d] - cuts[d];
			}
		}
		return a[0][a.length - 1];
	}
}