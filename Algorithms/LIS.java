import java.util.*;
public class LIS 
{
	public static int binarySearch(int[] a, int[] tails, int l, int r, int key)
	{
		while (l <= r)
	    {
	        int m = (l + r) / 2;
	        if (a[tails[m]] <= key)
	            l = m + 1;
	        else
	            r = m - 1;
	    }
	 
	    return l;
	}
	public static int displaySequence(int[] a)
	{
		int[] tails = new int[a.length];
		int[] prevs = new int[a.length];
		Arrays.fill(prevs, -1);
		
		int length = 1;
		
		for(int i = 1; i < a.length; i++)
		{
			//System.out.println(i);
			if(a[i] < a[tails[0]])
				tails[0] = i;
			else if(a[i] > a[tails[length - 1]])
			{
				prevs[i] = tails[length - 1];
				tails[length] = i;
				length++;
			}
			else
			{
				int pos = binarySearch(a, tails, 0, length - 1, a[i]);
				prevs[i] = tails[pos - 1];
				tails[pos] = i;
			}
		}		
		for(int i = tails[length - 1]; i >= 0; i = prevs[i])
			System.out.println(a[i]);
		return length;
	}
	
	public static int find(int[] a)
	{
		int[] dp = new int[a.length];
		int max = dp[0] = 1;
		for(int i = 1; i < a.length; i++)
		{
			int best = 0;
			for(int j = 0; j < i; j++)
				if(a[j] < a[i] && dp[j] + 1 > best)
					best = dp[j] + 1;
			dp[i] = best;
			max = Math.max(best, max);
		}
		return max;
	}
	
	public static void main(String[] args)
	{
		int[] a = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
		System.out.println(find(a));
	}
}
