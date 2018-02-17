import java.util.*;

public class RMQDP 
{
	public static void main(String[] args)
	{
		int[] a = {3, 1, 5, 3, 4, 7, 6, 1};
		System.out.println("Input table: " + Arrays.toString(a));
		System.out.println("Spare array");
		int[][] table = build(a);
		for(int[] temp : table)
			System.out.println(Arrays.toString(temp));
		for(int s = 0; s < a.length; s++)
			for(int e = s; e < a.length; e++)
				System.out.println(s + " " + e + " " + rmq(table, s, e));
	}
	
	public static int rmq(int[][] table, int s, int e)
	{
		int k = lg(e - s + 1);
		return Math.min(table[k][s], table[k][e - (1 << k) + 1]);
	}
	
	public static int lg(int num)
	{
		return (int)(Math.log(num) / Math.log(2));
	}
	
	public static int[][] build(int[] a)
	{
		int size = lg(a.length) + 1;
		int[][] dp = new int[size][a.length];
		System.arraycopy(a, 0, dp[0], 0, a.length);
		for(int i = 1; i < size; i++)
		{
			int f1 = 1 << (i - 1);
			for(int j = 0; j + f1 < a.length; j++)
				dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + f1]);
		}
		return dp;
	}
}
