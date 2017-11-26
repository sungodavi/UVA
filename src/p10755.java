import java.util.*;
import java.io.*;

public class p10755 
{
	static long[][][] a;
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		//System.setOut(new PrintStream("output.txt"));
		StringTokenizer st;
		//f.readLine();
		while(cases-- > 0)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			int z = scan.nextInt();
			a = new long[x][y][z];
			long best = Long.MIN_VALUE;
			for(int r = 0; r < x; r++)
				for(int c = 0; c < y; c++)
					for(int d = 0; d < z; d++)
					{
						a[r][c][d] = scan.nextLong();
						best = Math.max(best, a[r][c][d]);
						if(r > 0)
							a[r][c][d] += a[r-1][c][d];
						if(c > 0)
							a[r][c][d] += a[r][c - 1][d];
						if(r > 0 && c > 0)
							a[r][c][d] -= a[r-1][c-1][d];
					}
			//System.out.println(Arrays.deepToString(a));
			System.out.println(solve(x, y, z, best));
			if(cases > 0)
				System.out.println();
		}
	}
	
	public static long solve(int x, int y, int z, long best)
	{
		for(int depth = 1; depth <= x; depth++)
		{
			for(int r = 0; r < x - depth + 1; r++)
			{
				for(int width = 1; width <= y; width++)
				{
					for(int c = 0; c < y - width + 1; c++)
					{
						long sum = 0;
						for(int d = 0; d < z; d++)
						{
							sum += a[r + depth - 1][c + width - 1][d];
							if(r > 0)
								sum -= a[r - 1][c + width - 1][d];
							if(c > 0)
								sum -= a[r + depth - 1][c-1][d];
							if(r > 0 && c > 0)
								sum += a[r - 1][c - 1][d];
							//System.out.println(r + " " + c + " " + d + " " + depth + " " + width + " " + sum);
							if(sum < 0)
								sum = 0;
							else if(sum > best)
								best = sum;
						}
					}
				}
			}
		}
		return best;
	}
}