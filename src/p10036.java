import java.util.*;
import java.io.*;

public class p10036
{
	static int k;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			int[] a = new int[size];
			
			st = new StringTokenizer(f.readLine());
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(st.nextToken());
			
			boolean[][] dp = new boolean[size][k];
			dp[0][mod(a[0])] = true;
			//System.out.println(Arrays.toString(dp[0]));
			for(int i = 1; i < size; i++)
			{
				for(int j = 0; j < k; j++)
				{
					if(dp[i - 1][j])
					{
						dp[i][mod(j + a[i])] = true;
						dp[i][mod(j - a[i])] = true;
					}
				}
				//System.out.println(Arrays.toString(dp[i]));
			}
			if(dp[a.length - 1][0])
				System.out.println("Divisible");
			else
				System.out.println("Not divisible");
			
		}
				
	}
	
	public static int mod(int num)
	{
		return Math.floorMod(num, k);
	}
}