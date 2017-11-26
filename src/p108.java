import java.util.*;
import java.io.*;

public class p108 {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			int size = scan.nextInt();
			int[][] a = new int[size][size];
			int max = Integer.MIN_VALUE;
			for(int r =0; r < a.length; r++)
				for(int c =0; c < a[0].length; c++)
				{
					a[r][c] = scan.nextInt();
					max = Math.max(a[r][c], max);
					if(r > 0) 
						a[r][c] += a[r-1][c];
				}
			int best = solveBetter(a, max);
			System.out.println(best);
		}
	}
	
	public static int solveBetter(int[][] a, int max)
	{
		int best = max;
		for(int size = 1; size <= a.length; size++)
		{
			for(int r = 0; r < a.length - size + 1; r++)
			{
				int sum = 0;
				for(int c = 0; c < a.length; c++)
				{
					sum += a[r + size - 1][c];
					if(r > 0)
						sum -= a[r - 1][c];
					if(sum < 0)
						sum = 0;
					else
						best = Math.max(best, sum);
				}
			}
		}
		return best;
	}
}