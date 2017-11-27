import java.util.*;
import java.io.*;

public class Main //11517
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
		{
			int value = Integer.parseInt(f.readLine());
			int[] coins = new int[Integer.parseInt(f.readLine())];
			int sum = 0;
			for(int i = 0; i < coins.length; i++)
			{
				int num = Integer.parseInt(f.readLine());
				coins[i] = num;
				sum += num;
			}
			Arrays.sort(coins);
			int[] a = new int[sum + 1];
			for(int i = 1; i <= value; i++)
			{
				int best = Integer.MAX_VALUE;
				for(int c : coins)
				{
					if(c > i)
						break;
					best = Math.min(a[i - c], best);
				}
				if(best == Integer.MAX_VALUE)
					a[i] = best;
				else
					a[i] = best + 1;
			}
			if(a[value] >= 0)
				System.out.println(value + " " + a[value]);
			for(int i = value + 1; i < a.length; i++)
			{
				int best = Integer.MAX_VALUE;
				for(int c : coins)
				{
					if(c > i)
						break;
					best = Math.min(a[i - c], best);
				}
				if(best == Integer.MAX_VALUE)
					a[i] = best;
				else
				{
					System.out.println(i + " " + best);
				}
			}
		}
	}
}